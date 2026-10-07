const express = require('express');
const cors = require('cors');

const app = express();
const PORT = process.env.PORT || 5000;

const PRODUCT_SERVICE_URL = process.env.PRODUCT_SERVICE_URL || 'http://localhost:5001';
const ORDER_SERVICE_URL = process.env.ORDER_SERVICE_URL || 'http://localhost:5002';

app.use(cors());
app.use(express.json());

// Request logger middleware
app.use((req, res, next) => {
  console.log(`[API Gateway] 🌐 Incoming Request: ${req.method} ${req.originalUrl}`);
  next();
});

// Health check
app.get('/health', async (req, res) => {
  try {
    const [productHealth, orderHealth] = await Promise.all([
      fetch(`${PRODUCT_SERVICE_URL}/health`).then((r) => r.json()).catch(() => ({ status: 'DOWN' })),
      fetch(`${ORDER_SERVICE_URL}/health`).then((r) => r.json()).catch(() => ({ status: 'DOWN' })),
    ]);

    res.json({
      gateway: 'UP',
      services: {
        productService: productHealth,
        orderService: orderHealth,
      },
    });
  } catch (err) {
    res.status(500).json({ gateway: 'UP', error: err.message });
  }
});

// Proxy function helper
async function proxyRequest(targetUrl, req, res) {
  try {
    const options = {
      method: req.method,
      headers: { 'Content-Type': 'application/json' },
    };

    if (['POST', 'PUT', 'PATCH'].includes(req.method) && Object.keys(req.body).length > 0) {
      options.body = JSON.stringify(req.body);
    }

    const response = await fetch(targetUrl, options);
    const data = await response.json();
    res.status(response.status).json(data);
  } catch (error) {
    console.error(`[API Gateway] ❌ Error proxying request to ${targetUrl}:`, error.message);
    res.status(502).json({
      success: false,
      message: 'Bad Gateway: Downstream service unavailable.',
      error: error.message,
    });
  }
}

// Routes: Forward /api/products requests to Product Microservice
app.use('/api/products', (req, res) => {
  // req.url contains subpath after /api/products e.g. "/" or "/prod-101"
  const targetUrl = `${PRODUCT_SERVICE_URL}/products${req.url === '/' ? '' : req.url}`;
  proxyRequest(targetUrl, req, res);
});

// Routes: Forward /api/orders requests to Order Microservice
app.use('/api/orders', (req, res) => {
  const targetUrl = `${ORDER_SERVICE_URL}/orders${req.url === '/' ? '' : req.url}`;
  proxyRequest(targetUrl, req, res);
});

app.listen(PORT, () => {
  console.log(`🌐 [API Gateway] running on http://localhost:${PORT}`);
  console.log(`   └─ Product Service proxying -> ${PRODUCT_SERVICE_URL}`);
  console.log(`   └─ Order Service proxying   -> ${ORDER_SERVICE_URL}\n`);
});
