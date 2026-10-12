const express = require('express');
const cors = require('cors');

const app = express();
const PORT = process.env.PORT || 5001;

app.use(cors());
app.use(express.json());


let inventory = [
  { id: 'prod-101', name: 'MacBook Pro 16"', price: 2499, stock: 10 },
  { id: 'prod-102', name: 'Wireless Ergonomic Mouse', price: 49, stock: 25 },
  { id: 'prod-103', name: '4K USB-C Monitor', price: 450, stock: 5 },
];


app.get('/health', (req, res) => {
  res.json({ service: 'Product Microservice', status: 'UP', timestamp: new Date() });
});


app.get('/products', (req, res) => {
  console.log(`[Product Service] Fetching all products...`);
  res.json({ success: true, count: inventory.length, data: inventory });
});


app.get('/products/:id', (req, res) => {
  const productId = req.params.id;
  console.log(`[Product Service] Querying product details for ID: ${productId}`);
  const product = inventory.find((p) => p.id === productId);

  if (!product) {
    return res.status(404).json({ success: false, message: `Product with ID '${productId}' not found.` });
  }

  res.json({ success: true, data: product });
});


app.post('/products/:id/reduce-stock', (req, res) => {
  const productId = req.params.id;
  const { quantity } = req.body;

  console.log(`[Product Service] Stock reduction request for Product: ${productId}, Quantity: ${quantity}`);

  if (!quantity || quantity <= 0) {
    return res.status(400).json({ success: false, message: 'Invalid quantity requested.' });
  }

  const product = inventory.find((p) => p.id === productId);

  if (!product) {
    return res.status(404).json({ success: false, message: `Product '${productId}' not found.` });
  }

  if (product.stock < quantity) {
    return res.status(400).json({
      success: false,
      message: `Insufficient stock for '${product.name}'. Requested: ${quantity}, Available: ${product.stock}`,
    });
  }

  product.stock -= quantity;
  console.log(`[Product Service] Stock updated for '${product.name}'. Remaining stock: ${product.stock}`);

  res.json({
    success: true,
    message: `Stock successfully reduced by ${quantity}`,
    updatedProduct: product,
  });
});

app.listen(PORT, () => {
  console.log(`🚀 [Product Service] running on http://localhost:${PORT}`);
});
