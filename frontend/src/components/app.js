import {useState} from "react"

import Footer from "./footer"
import ImageSelector from "./dndDisplay/imageSelector";
import Samples from "./samples/samples";
import Display from "./dndDisplay/display";
import "./app.css"

import PhotoMosaic from "./photomosaic/photomosaic";
import Download from "./photomosaic/download";

export default function App() {
    const [displayImageURL , setDisplayURL] = useState(); //URL.createObjectURL
    const [photoMosaicURL , setPhotoMosaicURL] = useState(); //URL.createObjectURL
    const [originalImage, setOriginal] = useState();
    const [sampleList, setSamples] = useState([]);

    return (
        <div className="container">
            <header>
                <h1>Photo Mosaic</h1>
            </header>

            <div className="display-selector">
                <ImageSelector setOriginal={setOriginal} setDisplay={setDisplayURL}/>
                <Display displayImage={displayImageURL} />
            </div>

            <div className="options">
                <PhotoMosaic originalImage={originalImage} sampleList={sampleList} setPhotoMosaic={setPhotoMosaicURL} setDisplay={setDisplayURL}/>
                <Download targetImageURL={photoMosaicURL} />
            </div>

            <Samples fileList={sampleList} setFile={setSamples}/>
            <footer><Footer /></footer>
        </div>
    )
}