import {useState} from "react"

import Footer from "./footer"
import ImageSelector from "./imageSelector";
import Samples from "./searcher";
import Display from "./display";
import "./app.css"

import PhotoMosaic from "./photomosaic";
import Download from "./download";

export default function App() {
    const [displayImage , setDisplay] = useState();
    const [photoMosaic , setPhotoMosaic] = useState();
    const [originalImage, setOriginal] = useState();
    const [sampleList, setSamples] = useState([]);

    return (
        <div className="container">
            <header><h1>Photo Mosaic</h1></header>

            <div className="display-selector">
                <ImageSelector setOriginal={setOriginal} setDisplay={setDisplay}/>
                <Display displayImage={displayImage} />
            </div>

            <div className="options">
                <PhotoMosaic setPhotoMosaic={setPhotoMosaic} setDisplay={setDisplay} originalImage={originalImage} sampleList={sampleList}/>
                <Download image={photoMosaic} />
            </div>

            <Samples fileList={sampleList} setFile={setSamples}/>
            <footer><Footer /></footer>
        </div>
    )
}