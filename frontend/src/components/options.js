import React from "react";
import download from "./icons/download.png"
import "./options.css"
export default function Options(){
    return (
        <div className="user-options">
            <img className="option-icons" src={download} alt="options" /> 
        </div>
    )
}