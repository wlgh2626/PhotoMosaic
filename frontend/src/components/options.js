import React from "react";
import { Component } from "react/cjs/react.production.min";
import download from "./icons/download.png"
import "./options.css"
export default class Options extends Component{
    render () {
        return (
            <div className="user-options">
                <img className="option-icons" src={download} alt="options" /> 
            </div>
        )
    }
}