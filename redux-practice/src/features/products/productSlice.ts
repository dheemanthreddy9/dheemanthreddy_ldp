import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from "@reduxjs/toolkit";

interface Product {
  id: number;
  name: string;
  price: number;
  quantity: number;
}

interface ProductState {
  items: Product[];
}

const initialState: ProductState = {
  items: [
    { id: 1, name: "Wireless Headphones", price: 2500, quantity: 1 },
    { id: 2, name: "Smart Watch", price: 4999, quantity: 1 },
    { id: 3, name: "Gaming Mouse", price: 1200, quantity: 1 },
    { id: 4, name: "Mechanical Keyboard", price: 3500, quantity: 1 },
  ],
};

const productSlice = createSlice({
  name: "products",

  initialState,

  reducers: {
    addProduct: (state, action: PayloadAction<Product>) => {
      state.items.push(action.payload);
    },

    removeProduct: (state, action: PayloadAction<number>) => {
      state.items = state.items.filter(
        (product) => product.id !== action.payload
      );
    },

    increaseQuantity: (state, action: PayloadAction<number>) => {
      const product = state.items.find(
        (product) => product.id === action.payload
      );

      if (product) {
        product.quantity += 1;
      }
    },

    decreaseQuantity: (state, action: PayloadAction<number>) => {
      const product = state.items.find(
        (product) => product.id === action.payload
      );

      if (product && product.quantity > 1) {
        product.quantity -= 1;
      }
    },
  },
});

export const {
  addProduct,
  removeProduct,
  increaseQuantity,
  decreaseQuantity,
} = productSlice.actions;

export default productSlice.reducer;