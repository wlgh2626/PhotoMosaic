import download from "../../icons/download.png"
import React from 'react'

import "./download.css"
export default function Download( {image} ){
    function downloadImage(){
        console.log("Downloading...")
    }

    return (
        <span className="download-option">
            <img className="download-icon" src={download} alt="download" onClick={downloadImage}/>
        </span>  
    )
}