import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import SampleContainer from "./sampleContainer"
import "./samples.css"

class imageFile {
  constructor(file){
    this.file = file;
    this.name = file.name;
    this.preview = URL.createObjectURL(file);
  }
}
export default function Samples( {fileList, setFile }) {
    function addFiles(files){
      var newImages = files.map((file) =>
        new imageFile(file) 
      )
      setFile( fileList => [...fileList , ...newImages]); 
    }
   
    const onDrop = useCallback(acceptedFiles => {
      addFiles(acceptedFiles)
    }, [])

    const {getRootProps, getInputProps} = useDropzone({noClick:true , onDrop})
    
    return (
      <div className="dnd-container" {...getRootProps()}>
        <input {...getInputProps()} />
          <SampleContainer fileList={fileList} setFile={setFile} /> 
      </div>
    )
  }

