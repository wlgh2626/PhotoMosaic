import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";
import "./display.css";
import loading from "../../icons/loading.gif";

export default function Display({ displayImage, isLoading }) {
  return (
    <div className="main-display">
      <TransformWrapper minScale={0.1} initialScale={1} limitToBounds={false}>
        <TransformComponent>
          <img className="display-image" src={displayImage} alt="" />
        </TransformComponent>
      </TransformWrapper>

      {isLoading && (
        <div className="loading-animation-container">
          <img className="loading-animation" src={loading} />
        </div>
      )}
    </div>
  );
}
