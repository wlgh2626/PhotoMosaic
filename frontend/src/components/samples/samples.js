import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import SampleContainer from "./sampleContainer"
import dnd from "../../icons/dnd.png"
import "./samples.css"

export default function Samples( {fileList, setFile }) {
    const onDrop = useCallback(acceptedFiles => {
      setFile( fileList => [...fileList , ...acceptedFiles]); 
    }, [])

    const {getRootProps, getInputProps} = useDropzone({noClick:true , onDrop})
    
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