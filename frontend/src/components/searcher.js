import React, {useState , useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import xbutton from "./icons/xbutton.png"
import "./searcher.css"

class imageFile {
  constructor(url){
    this.preview = url;
  }
}
export default function Searcher( {fileList, setFile }) {
    function addFiles(files){
      var newImages = files.map((file) =>
        new imageFile(URL.createObjectURL(file)) 
      )
      setFile( fileList => [...fileList , ...newImages]); 
    }

    function removeItem(fileToRemove){
      const newList = fileList.filter((file) => 
        file.preview !== fileToRemove.preview
      )
      setFile(newList);
    }
   
    const onDrop = useCallback(acceptedFiles => {
      addFiles(acceptedFiles)
    }, [])

    const {getRootProps, getInputProps} = useDropzone({noClick:true , onDrop})
    
    const images = fileList.map((file) => (
      <span className="sample">
        <img src={xbutton} alt="x-button here" className="sample-option" onClick={() => removeItem(file)}/> 
        <img src={file.preview} alt="preview here" className="sample-preview"/>
      </span> 
    ))

    return (
        <div className="dnd-container" {...getRootProps()}>
          <input {...getInputProps()} />
          <div className="sample-container">
            {images}
          </div> 
        </div>
      )
  }

