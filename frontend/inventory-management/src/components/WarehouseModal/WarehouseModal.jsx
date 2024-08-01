import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';

import './WarehouseModal.css'

import { useRef } from 'react';

import {createWarehouse} from '../../slices/warehouseSlice'
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

export default function WarehouseModal({open, handleClose}) {
    const nameRef = useRef('');
    const addressRef = useRef('');


    const dispatch = useDispatch();
    
    function handleSubmit(e) {
        e.preventDefault();
        
        const name = nameRef.current.value;
        const address = addressRef.current.value;


        let warehouseBody = {
            "name": name,
            "address": address,
        }
        dispatch(createWarehouse(warehouseBody)).then(() => handleClose());
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
                    <form action="" className="warehouse-form" onSubmit={handleSubmit}>
                        <div className='warehouse-form-container'>
                            <span className='warehouse-form-title'>Create Warehouse </span>
                        </div>
                        <div className="warehouse-form-inputs">
                            <div>
                                <label className="warehouse-form-lable" htmlFor="warehouse-name">Name: </label>
                                <input className="warehouse-form-input" type="text" placeholder='Name' ref={nameRef}/>
                            </div>
                            <div>
                                <label className="warehouse-form-lable" htmlFor="warehouse-name">Address: </label>
                                <input className="warehouse-form-input" type="text" placeholder='Address' ref={addressRef}/>
                            </div>
                        </div>
                        <button className="warehouse-form-button" type='submit'>Submit</button>
                    </form>
                </Box>
            </Modal>
        </div>
    );
}
