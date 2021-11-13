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
        <button className="selector-button">Choose Image</button>
      </div>
    </div>
  );
}
