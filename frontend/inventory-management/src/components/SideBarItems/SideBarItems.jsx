import './SideBarItems.css'
export default function SideBarItems({itemName}) {
    //onClick here should set the itemName in the global state
    //this will be used to determine what is shown on main content

    return (
        <>
            <div className="side-bar-items">
                {itemName}
            </div>
        </>
    )
}