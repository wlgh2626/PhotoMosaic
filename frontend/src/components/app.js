import { useState } from "react";

import Display from "./mainDisplay/display";
import Footer from "./footer/footer";
import Samples from "./samples/samples";
import SelectWindow from "./selectWindow/selectWindow";
import ImageSelector from "./dnd/imageSelector";
import PhotoMosaic from "./photomosaic/photomosaic";
import Download from "./download/download";
import FileChooser from "../fileChooser";
import Showcase from "./showcase";

import "./app.css";


export default function App() {
  const [originalImage, setOriginal] = useState();
  const [originalImageURL, setOriginalURL] = useState();
  const [isLoading, setIsLoading] = useState(false);
  const [photoMosaicURL, setPhotoMosaicURL] = useState();
  const [sampleList, setSamples] = useState([]);

  const photoMosaic = (
    <PhotoMosaic
      originalImage={originalImage}
      sampleList={sampleList}
      setPhotoMosaic={setPhotoMosaicURL}
      setIsLoading={setIsLoading}
    />
  );

  const originalFileChooser = <FileChooser setFile={setOriginal} setFileUrl={setOriginalURL} />;
  const originalDisplay = <Display displayImage={originalImageURL} />;
  const resultDisplay = <Display displayImage={photoMosaicURL} isLoading={isLoading} />;
  const samplesFileChooser = <FileChooser setFile={setSamples} />;
  const samples = <Samples fileList={sampleList} setFile={setSamples} />;
  const imageSelector = (
    <ImageSelector Image={originalImage} setImage={setOriginal} setDisplay={setOriginalURL} />
  );
  const download = <Download targetImageURL={photoMosaicURL} />;

  return (
    <div className="container">
      <header>
        <h1>Photo Mosaic</h1>
      </header>

      <div className="main">
        <SelectWindow
          photoMosaic={photoMosaic}
          samples={samples}
          samplesFileChooser={samplesFileChooser}
          originalDisplay={originalDisplay}
          originalFileChooser={originalFileChooser}
          resultDisplay={resultDisplay}
          imageSelector={imageSelector}
          download={download}
        />
      </div>

      <div className="show">
        <Showcase />
      </div>

      <footer>
        <Footer />
      </footer>
    </div>
  );
}
