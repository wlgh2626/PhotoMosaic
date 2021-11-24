import DuckPhoto from "../photos/Ducksaic.png";
import HalfPhoto from "../photos/Halfsaic.jpg";
import "./showcase.css";

export default function Showcase(){
    return(
    <div className="show-case">
        <div className="show-case-header"><h2>What is Photo Mosaic</h2></div>
        <div className="show-case-image"></div>
        <div className="show-case-desc"></div>
    </div>
  )
}

/**
    <div className="show-case-header"><h2>What is Photo Mosaic</h2></div>
    <div className="show-case-image">Photos to Insert</div>
    <div className="show-case-desc">Photo Mosaicing is transforming an image, where each pixels are replaced with another image</div>
 */