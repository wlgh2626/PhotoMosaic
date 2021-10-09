import React from "react";
import { Component } from "react/cjs/react.production.min";
import github from "./icons/github.png";
import "./footer.css"
export default class Footer extends Component {
    render(){
        return (
            <div>
                <span>Jiho Shin</span>   
                <span>
                    <a href="https://github.com/wlgh2626/PhotoMosaic"> 
                        <img className="icons" src={github}/> 
                    </a>
                </span>
            </div>
        )
    }
}