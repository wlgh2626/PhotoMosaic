import { useState } from "react/cjs/react.development";
import "./selectWindow.css";

export default function SelectWindow({
  photoMosaic,
  samples,
  originalDisplay,
  resultDisplay,
  imageSelector,
  download,
}) {
  const maxWindow = 3;
  const [currentWindow, setWindow] = useState(1);

  const options = (
    <div className="options-container">
      <div className="previous">
        <button disabled={currentWindow <= 1} onClick={() => setWindow(currentWindow - 1)}>
          Previous
        </button>
      </div>
      <div className="next">
        <button disabled={currentWindow >= maxWindow} onClick={() => setWindow(currentWindow + 1)}>
          Next
        </button>
      </div>

      {currentWindow != maxWindow && (
        <div className="choose">
          <button>Choose Photo</button>
        </div>
      )}
      {currentWindow == 2 && <div className="photomosaic">{photoMosaic}</div>}
      {currentWindow == 3 && <div className="choose">{download}</div>}
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
