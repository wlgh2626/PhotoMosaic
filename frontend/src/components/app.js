import {useState} from "react"
import {ID} from "./id.js"
import Footer from "./footer"
import ImageSelecter from "./imageSelector";
import Samples from "./searcher";
import Options from "./options"
import S3 from "aws-sdk/clients/s3";
import "./app.css"
import S3Uploader from "./s3Uploader";



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
                        <S3Uploader 
                            photoMosaic={photoMosaic} 
                            setPhotoMosaic={setPhotoMosaic} 
                            originalImage={originalImage} 
                            sampleList={sampleList}
                        />
                </div>
            </main>
            <Options/>
            <Samples fileList={sampleList} setFile={setSamples}/>
            <footer><Footer /></footer>
        </div>
    )
}