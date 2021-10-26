import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";
import "./display.css"
export default function Display( {displayImage} ){
    return (
        <div className="main-display">
            <TransformWrapper 
                className="display-container" 
                initialScale={1}
                maxPositionX={1}
                maxPositionY={1}
            >
                <TransformComponent>
                    <img className="display-image" src={displayImage} />
                </TransformComponent>
            </TransformWrapper>
        </div>
    )
}