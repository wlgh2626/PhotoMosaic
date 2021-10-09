import ReactDOM  from "react-dom";
import Header from "./header";
import Footer from "./footer"
import ImageSelecter from "./imageSelector";

import "./app.css"

export default function App() {
    return (
        <div className="container">
            <header><Header /></header>
            <main><ImageSelecter /></main>
            <div className="user-options">put options here</div>
            <div className="search-side">Example Search</div>
            <footer>
                <Footer />
            </footer>
        </div>
    )
}