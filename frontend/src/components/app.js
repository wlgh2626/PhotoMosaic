import { useState } from "react";

import Display from "./mainDisplay/display";
import Footer from "./footer/footer";
import Samples from "./samples/samples";
import SelectWindow from "./selectWindow";
import ImageSelector from "./dnd/imageSelector";
import "./app.css";

import PhotoMosaic from "./photomosaic/photomosaic";
import Download from "./download/download";

export default function App() {
  const [displayImageURL, setDisplayURL] = useState(); //URL.createObjectURL
  const [isLoading, setIsLoading] = useState(false);
  const [photoMosaicURL, setPhotoMosaicURL] = useState(); //URL.createObjectURL
  const [originalImage, setOriginal] = useState();
  const [sampleList, setSamples] = useState([]);

  const photoMosaic = (
    <PhotoMosaic
      originalImage={originalImage}
      sampleList={sampleList}
      setPhotoMosaic={setPhotoMosaicURL}
      setDisplay={setDisplayURL}
      setIsLoading={setIsLoading}
    />
  );

  const originalDisplay = <Display displayImage={displayImageURL} />;
  const resultDisplay = (
    <Display displayImage={photoMosaicURL} isLoading={isLoading} />
  );
  const samples = <Samples fileList={sampleList} setFile={setSamples} />;
  const imageSelector = (
    <ImageSelector setOriginal={setOriginal} setDisplay={setDisplayURL} />
  );

  return (
    <div className="container">
      <header>
        <h1>Photo Mosaic</h1>
      </header>

      <div className="main">
        <SelectWindow
          photoMosaic={photoMosaic}
          samples={samples}
          originalDisplay={originalDisplay}
          resultDisplay={resultDisplay}
          imageSelector={imageSelector}
        />
      </div>

      <div className="options">
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
