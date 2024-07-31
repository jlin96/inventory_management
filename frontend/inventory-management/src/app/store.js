import {configureStore} from '@reduxjs/toolkit'
import warehouseReducer from '../slices/warehouseSlice'

export const store = configureStore({
    reducer: {
        warehouse: warehouseReducer,
    }
})