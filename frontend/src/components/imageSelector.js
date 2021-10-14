
import { useState } from "react";
import "./imageSelector.css"

export default function ImageSelecter( {selectedImage , setImage}) {
    function imageSelected(event){
        setImage(URL.createObjectURL(event.target.files[0]));
    }
    
    return (
        <div className="selector-group">
            <div>
                <label className="selector-label">
                    <input 
                        className="selector-button" 
                        type="file" 
                        onChange={imageSelected} 
                    />
                    Choose Image
                </label>
            </div>      
        </div>
    )
     
}