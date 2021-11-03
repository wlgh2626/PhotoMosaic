import download from "../../icons/download.png"
import React from 'react'

import "./download.css"
export default function Download( {targetImageURL} ){
    return (
        <span className="download-option">
            <a href={targetImageURL} download>
                <img className="download-icon" src={download} alt="download" />
            </a>
        </span>  
    )
}