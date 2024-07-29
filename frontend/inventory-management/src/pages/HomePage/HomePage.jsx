import SideBar from "../../components/SideBar/SideBar";
import MainContent from "../../components/MainContent/MainContent";
import './HomePage.css';

export default function HomePage() {
    return (
        <>
            <div className="main-screen">
                <div className="side-bar">
                    <SideBar className="side-bar"/>
                </div>
                <div className="main-content">
                    <MainContent className="main-content"/>
                </div>
            </div>
        </>
    )
}