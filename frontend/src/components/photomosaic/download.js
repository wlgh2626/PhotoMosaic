import download from "../../icons/download.png"
import React from 'react'
export default function Download( {image} ){
    return (
        <span className="download">
            <img style={{position:"absoluate",width: "30px",bottom:"0px"}} src={download} alt="download-icon" />
        </span>  
    )
}