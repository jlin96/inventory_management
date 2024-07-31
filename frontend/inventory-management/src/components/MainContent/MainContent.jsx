import './MainContent.css'
import Product from '../Product/Product'
import Warehouse from '../Warehouse/Warehouse'
import { useSelector } from 'react-redux'

export default function MainContent() {
    const currentTab = useSelector((state) => state.warehouse.currentTab);

    const currentComponent = currentTab === "Products" ? <Product /> : <Warehouse />
    
    return (
        <>
            <div className="main-content-display">
                {currentComponent}
            </div>
        </>
    )
}