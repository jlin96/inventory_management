import './SideBarItems.css'
import { setTab } from '../../slices/warehouseSlice'
import { useDispatch } from 'react-redux'

export default function SideBarItems({itemName}) {
    // const tab = useSelector((state) => state.warehouses.currentTab);
    const dispatch = useDispatch();

    return (
        <>
            <div className="side-bar-items" onClick={() => dispatch(setTab(itemName))}>
                {itemName}
            </div>
        </>
    )
}