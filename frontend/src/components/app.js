import {useState} from "react"
import Footer from "./footer"
import ImageSelecter from "./imageSelector";
import Samples from "./searcher";
import Options from "./options"
import "./app.css"
import PhotoMosaic from "./photomosaic";

export default function App() {
    const [photoMosaic , setPhotoMosaic] = useState();
    const [originalImage, setOriginal] = useState();
    const [sampleList, setSamples] = useState([]);
    return (
        <div className="container">
            <header><h1>Photo Mosaic</h1></header>
            <main>
                <ImageSelecter selectedImage={originalImage} setImage={setOriginal}/>
                <div>    
                        <PhotoMosaic
                            setPhotoMosaic={setPhotoMosaic} 
                            originalImage={originalImage} 
                            sampleList={sampleList}
                        />
                        <img className="result" src={photoMosaic} alt="image-here" />
                </div>
                
            </main>
            <Options/>
            <Samples fileList={sampleList} setFile={setSamples}/>
            <footer><Footer /></footer>
        </div>
    )
}