import './SideBar.css';
import SideBarItems from '../SideBarItems/SideBarItems';

export default function SideBar() {
    //can be extracted into enum when more menu items
    let menuItems = ["Warehouses", "Products"]

    let mappedItems = menuItems.map((name, idx) => {
        return (
            <SideBarItems key={idx} itemName={name}/>
        )
    })

    return (
        <>
            <div className="side-bar-column">
                <header className="side-bar-header">
                    <div className='name-container'>
                        <div className='word'>Jesse's</div>
                        <div className='word'>Management</div>
                    </div>
                </header>
                <div className="side-bar-menu">
                    {mappedItems}
                </div>
            </div>
        </>
    )
}