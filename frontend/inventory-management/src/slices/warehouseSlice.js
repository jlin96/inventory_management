import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

//need seperate reducers
const initialState = {
    warehouses : {},
    products: {},
    currentTab: "",
}

//accepts type, payloadCreater (callback function that should return a promise)
export const createWarehouse = createAsyncThunk('warehouses/createWarehouse', async () => {
    return await fetch('http://localhost:8080/warehouses', {
        method: "POST",
        headers: {'Content-Type':'application/json',
        },
        body: JSON.stringify({
            "name": "Home",
            "address": "Main Street"
        })
    }).then(product => product.json())
    //doesnt work cause it is expecting a promise
    // .then(product => { 
    //     console.log("just got back")
    //     console.log(product)
    // })
    
})

export const createProduct = createAsyncThunk('warehouses/createProduct', async (product) => {
    return await fetch('http://localhost:8080/products', {
        method: "POST",
        headers: {'Content-Type':'application/json',
        },
        body: JSON.stringify(product)
    }).then(product => product.json())
})

export const fetchProducts = createAsyncThunk('warehouses/fetchProducts', async () => {
    return await fetch('http://localhost:8080/products', {
        method: "GET",
        headers: {'Content-Type':'application/json',
        },
    }).then(products => products.json())
})

//why cant i pass 2 params?
export const editProduct = createAsyncThunk('warehouses/editProduct', async (product) => {
    return await fetch(`http://localhost:8080/products/${product.id}`, {
        method: "PUT",
        headers: {
            'Content-Type':'application/json',
        },
        body: JSON.stringify(product)
    }).then(product => product.json())
})

export const deleteProduct = createAsyncThunk('warehouses/deleteProducts', async (id) => {
    return await fetch(`http://localhost:8080/products/${id}`, {
        method: "DELETE",
        headers: {'Content-Type':'application/json',
        },
    }).then(products => products.json())
})


export const warehouseSlice = createSlice({
    name: 'warehouses',
    initialState,
    reducers: {
        setTab: (state, action) => {
            state.currentTab = action.payload;
        }
    },
    extraReducers(builder) {
        builder
            .addCase(createWarehouse.fulfilled, (state, action) => {
                return {
                    ...state,
                    warehouses: {
                        ...state.warehouses,
                        [action.payload.id] : action.payload
                    }
                }
                //immutability
                // state.warehouses[action.payload.id] = action.payload
                // return state.warehouses;
            })
            .addCase(createProduct.fulfilled, (state, action) => {
                return {
                    ...state,
                    products: {
                        ...state.products,
                        [action.payload.id] : action.payload
                    }
                }
            })
            .addCase(fetchProducts.fulfilled, (state, action) => {
                return {
                    ...state,
                    products: action.payload
                }
            })
            .addCase(editProduct.fulfilled, (state, action) => {
                return {
                    ...state,
                    products: {
                        ...state.products,
                        [action.payload.id] : action.payload
                    }
                }
            })
            .addCase(deleteProduct.fulfilled, (state, action) => {
                state.products = state.products.filter((product) => product.id !== action.payload.id);
                return state;
            })
    }
})

export const { setTab } = warehouseSlice.actions;

export default warehouseSlice.reducer;