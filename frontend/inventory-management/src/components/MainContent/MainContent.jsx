import './MainContent.css'
import Product from '../Product/Product'
import Warehouse from '../Warehouse/Warehouse'
import Home from '../Home/Home'
import { useSelector } from 'react-redux'

export default function MainContent() {
    const currentTab = useSelector((state) => state.warehouse.currentTab);

    function showTab(currentTab) {
        if(currentTab === "Products") {
            return <Product />
        } else if(currentTab === "Warehouses") {
            return <Warehouse />
        } else {
            return <Home />
        }
    }
    // const currentComponent = currentTab === "Products" ? <Product /> : <Warehouse />
    
    return (
        <>
            <div className="main-content-display">
                {showTab(currentTab)}
            </div>
        </>
    )
}