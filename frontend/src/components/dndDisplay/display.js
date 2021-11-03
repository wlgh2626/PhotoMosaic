import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";
import "./display.css"

export default function Display( {displayImage} ){
    return (
        <div className="main-display">
            <TransformWrapper 
                className="display-container" 
                initialScale={1}
                maxPositionX={100}
                maxPositionY={100}
                limitToBounds={true}
            >
                <TransformComponent>
                    <img className="display-image" src={displayImage} alt=""/>
                </TransformComponent>
            </TransformWrapper>
        </div>
    )
}