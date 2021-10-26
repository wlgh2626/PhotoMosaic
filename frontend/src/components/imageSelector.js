
import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import "./imageSelector.css"

export default function ImageSelecter( {setOriginal, setDisplay}) {
    
    const onDrop = useCallback(acceptedFile => {
        setDisplay(URL.createObjectURL(acceptedFile[0]));
        setOriginal(URL.createObjectURL(acceptedFile[0]));
    }, [])
  
    const {getRootProps, getInputProps} = useDropzone({noClick:true , onDrop})

    return (
        <div className="main-selector" {...getRootProps()}>
            <input {...getInputProps()} />
        </div>
    )

}