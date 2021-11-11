import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import SampleContainer from "./sampleContainer"
import dnd from "../../icons/dnd.png"
import "./samples.css"

function isFileImage(file) {
  const acceptedImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
  return file && acceptedImageTypes.includes(file['type'])
}

export default function Samples( {fileList, setFile }) {
    const onDrop = useCallback(acceptedFiles => {
      acceptedFiles.map( file =>{
        if(isFileImage(file)){
          setFile( fileList => [...fileList , file]); 
        }
      })
    }, [])

    const {getRootProps, getInputProps} = useDropzone({
      accept: "image/*",
      noClick:true, 
      onDrop})
    
    return (
      <div className="dnd-container" {...getRootProps()}>
        <input {...getInputProps()} />
          <SampleContainer fileList={fileList} setFile={setFile} /> 
          {
            (fileList.length === 0) && (
              <div className="sample-dnd-style">
                Step 2: Drag Samples
                <img style={{padding: "10px"}} src={dnd} />
              </div>
          )} 
      </div>
    )
  }