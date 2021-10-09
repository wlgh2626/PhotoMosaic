import React from "react";
import { Component } from "react/cjs/react.production.min";
import "./imageSelector.css"

export default class ImageSelecter extends Component {
    constructor(){
        super();
        this.state = {selectedImage : null};
        this.imageSelected = this.imageSelected.bind(this)
    }

    imageSelected(event) {
        this.setState(
            {
                selectedImage : URL.createObjectURL(event.target.files[0])
            }
        )
    }

    render() {
        return (
            <div className="selector-group">
                <div>
                    <label className="selector-label">
                        <input className="selector-button" type="file" onChange={this.imageSelected} />
                        Choose Image
                    </label>
                </div>
                <div>
                    <img className="image-display" src ={this.state.selectedImage} />
                </div>
                
            </div>
        )
    }  
}