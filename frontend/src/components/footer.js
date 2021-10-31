import React from "react";
import github from "../icons/github.png";
import "./footer.css"
export default function Footer(){
    return (
        <div>
            <span>Jiho Shin</span>   
            <span>
                <a href="https://github.com/wlgh2626/PhotoMosaic"> 
                    <img className="icons" src={github} alt="github-icon"/> 
                </a>
            </span>
        </div>
    )
}