import { useState } from "react";

import Footer from "./footer/footer";
import Samples from "./samples/samples";
import SelectWindow from "./selectWindow";
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

      <div className="main">
        <SelectWindow
          displayImageURL={displayImageURL}
          isLoading={isLoading}
          setOriginal={setOriginal}
          setDisplayURL={setDisplayURL}
        />
      </div>
      <Samples fileList={sampleList} setFile={setSamples} />

      <div className="options">
        <PhotoMosaic
          originalImage={originalImage}
          sampleList={sampleList}
          setPhotoMosaic={setPhotoMosaicURL}
          setDisplay={setDisplayURL}
          setIsLoading={setIsLoading}
        />
        <Download targetImageURL={photoMosaicURL} />
        <div className="options-item">
          <button className="tutorial-button">Tutorial</button>
        </div>
        <div className="options-item">
          <a href="https://github.com/wlgh2626/PhotoMosaic">
            <button className="source-button">Source</button>
          </a>
        </div>
      </div>

      <footer>
        <Footer />
      </footer>
    </div>
  );
}
