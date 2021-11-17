import { useState } from "react/cjs/react.development";
import "./selectWindow.css";

export default function SelectWindow({
  photoMosaic,
  samples,
  originalDisplay,
  resultDisplay,
  imageSelector,
}) {
  const maxWindow = 3;
  const [currentWindow, setWindow] = useState(1);
  function nextWindow() {
    if (currentWindow < maxWindow) {
      setWindow(currentWindow + 1);
    }
  }
  function prevWindow() {
    if (currentWindow - 1 > 0) {
      setWindow(currentWindow - 1);
    }
  }

  const options = (
    <div className="options-container">
      <div className="choose">
        <button>Choose Photo</button>
      </div>
      <div className="previous">
        <button onClick={() => prevWindow()}>Previous</button>
      </div>
      <div className="next">
        <button onClick={() => nextWindow()}>Next</button>
      </div>
      {currentWindow == 2 && <div className="photomosaic">{photoMosaic}</div>}
    </div>
  );

  return (
    <div className="select-window">
      {currentWindow == 1 && (
        <div className="display-selector">
          {imageSelector}
          {originalDisplay}
          {options}
        </div>
      )}

      {currentWindow == 2 && (
        <div className="sample-selector">
          {samples}
          {options}
        </div>
      )}

      {currentWindow == 3 && (
        <div className="result-view">
          {resultDisplay}
          {options}
        </div>
      )}
    </div>
  );
}
