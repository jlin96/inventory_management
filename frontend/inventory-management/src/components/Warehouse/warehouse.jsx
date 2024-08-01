import UploadIcon from '@mui/icons-material/Upload';
import WarehouseModal from '../WarehouseModal/WarehouseModal'
import WarehouseTable from './WarehouseTable'
import './Warehouse.css';
import { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {fetchWarehouses} from '../../slices/warehouseSlice'


export default function Warehouse () {
    const [open, setOpen] = useState(false);
    const [deleted, setDeleted] = useState(false);
    const [edited, setEdited] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const handleDeleted = () => setDeleted(!deleted);
    const handleEdited = () => setEdited(!edited);
    const dispatch = useDispatch();
    const warehouses = useSelector((state) => state.warehouse.warehouses);
    
    useEffect(() => {
        dispatch(fetchWarehouses());

    }, [ dispatch, open, deleted, edited]);


    return (
        <>
                <div className="warehouse-title-container">
                    <div className="warehouse-words">
                        <span className="warehouse-title">
                            Warehouse
                        </span>
                        <span className="warehouse-description">
                            Dashboard / Warehouses
                        </span>
                    </div>
                    <button className='warehouse-upload-button' onClick={handleOpen}>
                        <UploadIcon />
                        <div>
                            Add Warehouse
                        </div>
                    </button>
                </div>
                <WarehouseModal open={open} handleClose={handleClose}/>
                <div>
                    <WarehouseTable warehouses={warehouses} handleDeleted={handleDeleted} handleEdited={handleEdited}/>
                </div>
        </>
    )
}