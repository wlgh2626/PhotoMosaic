import download from "./icons/download.png"
import React from 'react'
export default function Download( {image} ){
    return (
        <span>
            <img className="option-icons" src={download} alt="options" />
        </span>  
    )
}