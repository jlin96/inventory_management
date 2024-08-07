import './Product.css'
import UploadIcon from '@mui/icons-material/Upload';
import ProductTable from './ProductTable';
import ProductModal from '../ProductModal/ProductModal';
import {fetchProducts} from '../../slices/warehouseSlice'
import { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';

export default function Product () {
    const [open, setOpen] = useState(false);
    const [deleted, setDeleted] = useState(false);
    const [edited, setEdited] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const handleDeleted = () => setDeleted(!deleted);
    const handleEdited = () => setEdited(!edited);
    const dispatch = useDispatch();
    const products = useSelector((state) => state.warehouse.products);
    
    useEffect(() => {
        dispatch(fetchProducts());

    }, [ dispatch, open, deleted, edited]);


    return (
        <>
                <div className="product-title-container">
                    <div className="product-words">
                        <span className="product-title">
                            Products
                        </span>
                        <span className="product-description">
                            Dashboard / Products
                        </span>
                    </div>
                    <button className='product-upload-button' onClick={handleOpen}>
                        <UploadIcon />
                        <div>
                            Add Product
                        </div>
                    </button>
                </div>
                <ProductModal open={open} handleClose={handleClose}/>
                <div>
                    <ProductTable products={products} handleDeleted={handleDeleted} handleEdited={handleEdited}/>
                </div>
        </>
    )
}