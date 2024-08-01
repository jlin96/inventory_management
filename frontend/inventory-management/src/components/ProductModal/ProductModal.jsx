import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';

import './ProductModal.css'

import { useRef } from 'react';

import {createProduct} from '../../slices/warehouseSlice'
import { useDispatch } from 'react-redux';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function ProductModal({open, handleClose}) {
    const nameRef = useRef('');
    const descriptionRef = useRef('');
    const stockRef = useRef(null);
    const warehouseRef = useRef(null);

    const dispatch = useDispatch();
    
    function handleSubmit(e) {
        e.preventDefault();
        
        const name = nameRef.current.value;
        const description = descriptionRef.current.value;
        const stock = stockRef.current.value;
        const warehouse = warehouseRef.current.value;


        let productBody = {
            "name": name,
            "description": description,
            "stockAmount": Number(stock),
            "warehouse": {
                "id": warehouse
            }
        }
        dispatch(createProduct(productBody)).then(() => handleClose());
        // dispatch(createWarehouse(warehouseBody)).then(() => handleClose());
    }


    return (
        <div>
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <Box sx={style}>
                <form action="" className="product-form" onSubmit={handleSubmit}>
                    <div className='product-form-container'>
                        <span className='product-form-title'>Create Product </span>
                    </div>
                    <div className="product-form-inputs">
                        <div>
                            <label className="product-form-lable" htmlFor="product-name">Name: </label>
                            <input className="product-form-input" type="text" placeholder='Name' ref={nameRef}/>
                        </div>
                        <div>
                            <label className="product-form-lable" htmlFor="product-name">Description: </label>
                            <input className="product-form-input" type="text" placeholder='Description' ref={descriptionRef}/>
                        </div>
                        <div>
                            <label className="product-form-lable" htmlFor="product-name">Stock: </label>
                            <input className="product-form-input" type="text" placeholder='Stock' ref={stockRef}/>
                        </div>
                        <div>
                            <label className="product-form-lable" htmlFor="product-name">Warehouse: </label>
                            <input className="product-form-input" type="text" placeholder='Warehouse' ref={warehouseRef}/>
                        </div>
                        </div>
                        <button className="product-form-button" type='submit'>Submit</button>
                </form>
            </Box>
        </Modal>
        </div>
    );
}
