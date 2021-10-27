import {useState} from "react"

import Footer from "./footer"
import ImageSelector from "./dndDisplay/imageSelector";
import Samples from "./samples/samples";
import Display from "./dndDisplay/display";
import "./app.css"

import PhotoMosaic from "./photomosaic/photomosaic";
import Download from "./photomosaic/download";

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
                <PhotoMosaic originalImage={originalImage} sampleList={sampleList} setPhotoMosaic={setPhotoMosaic} setDisplay={setDisplay}/>
                <Download image={photoMosaic} />
            </div>

            <Samples fileList={sampleList} setFile={setSamples}/>
            <footer><Footer /></footer>
        </div>
    )
}