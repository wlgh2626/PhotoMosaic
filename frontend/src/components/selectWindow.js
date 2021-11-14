import ImageSelector from "./dnd/imageSelector";
import Display from "./mainDisplay/display";
import "./selectWindow.css";

export default function SelectWindow({
  displayImageURL,
  isLoading,
  setOriginal,
  setDisplayURL,
}) {
  return (
    <div className="display-selector">
      <ImageSelector setOriginal={setOriginal} setDisplay={setDisplayURL} />
      <Display displayImage={displayImageURL} isLoading={isLoading} />
      <div className="options-container">
        <div className="choose">
          <button>Choose Photo</button>
        </div>
        <div className="previous">
          <button>Previous</button>
        </div>
        <div className="next">
          <button>Next</button>
        </div>
      </div>
    </div>
  );
}
