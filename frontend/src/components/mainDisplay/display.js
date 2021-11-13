import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";
import "./display.css";
import loading from "../../icons/loading.gif";

export default function Display( {displayImage, isLoading} ){
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
            {
                isLoading && (
                    <div className="loading-animation-container">
                        <img className="loading-animation" src={loading}/>
                    </div>
            )}
        </div>
    )
}