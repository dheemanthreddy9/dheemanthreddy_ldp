const express = require('express');
const cors = require('cors');

const app = express();
const PORT = process.env.PORT || 5002;
const PRODUCT_SERVICE_URL = process.env.PRODUCT_SERVICE_URL || 'http://localhost:5001';

app.use(cors());
app.use(express.json());


let orders = [];


app.get('/health', (req, res) => {
  res.json({ service: 'Order Microservice', status: 'UP', timestamp: new Date() });
});


app.get('/orders', (req, res) => {
  console.log(`[Order Service] Retrieving order history...`);
  res.json({ success: true, count: orders.length, data: orders });
});


app.get('/orders/:id', (req, res) => {
  const orderId = req.params.id;
  const order = orders.find((o) => o.id === orderId);

  if (!order) {
    return res.status(404).json({ success: false, message: `Order '${orderId}' not found.` });
  }

  res.json({ success: true, data: order });
});


app.post('/orders', async (req, res) => {
  const { productId, quantity, customerName } = req.body;

  console.log(`\n[Order Service] Received new order placement request:`);
  console.log(`  Customer: ${customerName} | Product: ${productId} | Quantity: ${quantity}`);

  if (!productId || !quantity || !customerName) {
    return res.status(400).json({ success: false, message: 'Missing required fields (productId, quantity, customerName)' });
  }

  try {
    // STEP 1: Inter-Service Communication -> Fetch product details from Product Microservice
    console.log(`[Order Service] ➡️ Inter-Service Call: Fetching details from Product Service at ${PRODUCT_SERVICE_URL}/products/${productId}...`);
    const productResponse = await fetch(`${PRODUCT_SERVICE_URL}/products/${productId}`);
    const productResult = await productResponse.json();

    if (!productResponse.ok || !productResult.success) {
      console.log(`[Order Service] ❌ Failed to fetch product details: ${productResult.message}`);
      return res.status(productResponse.status).json({ success: false, message: productResult.message });
    }

    const product = productResult.data;

    // Check stock availability locally before attempting transaction
    if (product.stock < quantity) {
      console.log(`[Order Service] ⚠️ Stock check failed. Available: ${product.stock}, Requested: ${quantity}`);
      return res.status(400).json({
        success: false,
        message: `Cannot place order. Only ${product.stock} units of '${product.name}' in stock.`,
      });
    }


    console.log(`[Order Service] ➡️ Inter-Service Call: Requesting stock reduction from Product Service...`);
    const reduceStockResponse = await fetch(`${PRODUCT_SERVICE_URL}/products/${productId}/reduce-stock`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ quantity }),
    });

    const reduceStockResult = await reduceStockResponse.json();

    if (!reduceStockResponse.ok || !reduceStockResult.success) {
      console.log(`[Order Service] ❌ Stock reduction failed: ${reduceStockResult.message}`);
      return res.status(reduceStockResponse.status).json({ success: false, message: reduceStockResult.message });
    }

    // STEP 3: Create and store order in Order Microservice DB
    const totalAmount = product.price * quantity;
    const newOrder = {
      id: `ord-${Date.now()}`,
      customerName,
      productId: product.id,
      productName: product.name,
      quantity,
      unitPrice: product.price,
      totalAmount,
      status: 'CONFIRMED',
      createdAt: new Date().toISOString(),
    };

    orders.push(newOrder);
    console.log(`[Order Service] ✅ Order created successfully! Order ID: ${newOrder.id}\n`);

    res.status(201).json({
      success: true,
      message: 'Order created successfully',
      order: newOrder,
    });
  } catch (error) {
    console.error(`[Order Service] 🚨 Error processing order:`, error.message);
    res.status(500).json({
      success: false,
      message: 'Internal server error while communicating with downstream microservices.',
      error: error.message,
    });
  }
});

app.listen(PORT, () => {
  console.log(`🚀 [Order Service] running on http://localhost:${PORT}`);
});
