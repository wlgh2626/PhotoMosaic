import { useState } from "react";

import Footer from "./footer/footer";
import ImageSelector from "./dnd/imageSelector";
import Samples from "./samples/samples";
import Display from "./mainDisplay/display";
import "./app.css";

import PhotoMosaic from "./photomosaic/photomosaic";
import Download from "./download/download";

export default function App() {
  const [displayImageURL, setDisplayURL] = useState(); //URL.createObjectURL
  const [isLoading, setIsLoading] = useState(false);
  const [photoMosaicURL, setPhotoMosaicURL] = useState(); //URL.createObjectURL
  const [originalImage, setOriginal] = useState();
  const [sampleList, setSamples] = useState([]);

  return (
    <div className="container">
      <header>
        <h1>Photo Mosaic</h1>
      </header>

      <div className="display-selector">
        <ImageSelector setOriginal={setOriginal} setDisplay={setDisplayURL} />
        <Display displayImage={displayImageURL} isLoading={isLoading} />
      </div>

      <div className="options">
        <PhotoMosaic
          originalImage={originalImage}
          sampleList={sampleList}
          setPhotoMosaic={setPhotoMosaicURL}
          setDisplay={setDisplayURL}
          setIsLoading={setIsLoading}
        />
        <Download targetImageURL={photoMosaicURL} />
      </div>

      <Samples fileList={sampleList} setFile={setSamples} />
      <footer>
        <Footer />
      </footer>
    </div>
  );
}
