import logo from '../../assets/logo.png'

export default function SideBar() {
    return (
        <>
            <div className="side-bar-main">
                <header className="inventory-name">
                    <img src={logo} alt="logo" />
                </header>
            </div>
        </>
    )
}