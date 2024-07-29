import {createSlice}from "@reduxjs/toolkit";

const initialState = {value: {}};

//slice is an all in one, action and reducer
const productsSlice = createSlice({
    //state name
    name: 'products',
    //initial value
    initialState,
    reducers: { 
    //Each one is the reducer with initial state, action ()
    receiveAllProducts: (state) => {
        //call api to get all products and set it equal to  and return new state
    },
    receiveProduct: (state, action) => {
        //call api to create new product, then when the object is returned get it's id and set it in the state
        //action would be the product info
        // return state.value[action.product.id] = action.product;
    },
    removeProduct:(state, action) => {
        //call api to remove product from product list then remove it from our list based off the product id
        //action is the product id
        // delete state[action.productId];
        // return state
    },
    //async go in here
    extraReducers: {},
  },
})

export const {receiveAllProducts, receiveProduct, removeProduct} = productsSlice.actions;
export default productsSlice.reducer;
