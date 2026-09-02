import { useDispatch, useSelector } from "react-redux";

import type { RootState, AppDispatch } from "../../app/store";

import {
  addProduct,
  removeProduct,
  increaseQuantity,
  decreaseQuantity,
} from "./productSlice";

const ProductList = () => {
  const products = useSelector(
    (state: RootState) => state.products.items
  );

  const dispatch = useDispatch<AppDispatch>();

  const sampleCatalog = [
    { name: "Bluetooth Speaker", price: 1800 },
    { name: "USB-C Hub", price: 1500 },
    { name: "Ergonomic Chair", price: 8999 },
    { name: "4K Monitor", price: 18500 },
  ];

  const handleAddProduct = () => {
    const randomItem =
      sampleCatalog[Math.floor(Math.random() * sampleCatalog.length)];
    const newProduct = {
      id: Date.now(),
      name: randomItem.name,
      price: randomItem.price,
      quantity: 1,
    };

    dispatch(addProduct(newProduct));
  };

  return (
    <div>
      <h1>Product Store</h1>

      <button onClick={handleAddProduct}>
        Add Product
      </button>

      {products.length === 0 ? (
        <p>No products available.</p>
      ) : (
        products.map((product) => (
          <div key={product.id}>
            <h3>{product.name}</h3>

            <p>Price: ₹{product.price}</p>

            <p>Quantity: {product.quantity}</p>

            <button
              onClick={() =>
                dispatch(increaseQuantity(product.id))
              }
            >
              +
            </button>

            <button
              onClick={() =>
                dispatch(decreaseQuantity(product.id))
              }
            >
              -
            </button>

            <button
              onClick={() =>
                dispatch(removeProduct(product.id))
              }
            >
              Remove
            </button>
          </div>
        ))
      )}
    </div>
  );
};

export default ProductList;