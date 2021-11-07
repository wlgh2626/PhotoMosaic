
import React, {useCallback, useState} from 'react'
import {useDropzone} from 'react-dropzone'
import "./imageSelector.css"
import dnd from "../../icons/dnd.png"

export default function ImageSelecter( {setOriginal, setDisplay} ) {
    const [isOpened , setIsOpened] = useState(false);

    const onDrop = useCallback(acceptedFile => {
        setDisplay(URL.createObjectURL(acceptedFile[0]));
        setOriginal(acceptedFile[0]);
        setIsOpened(true)
    }, [])
  
    const {getRootProps, getInputProps} = useDropzone({noClick:true , onDrop})

    return (
        <div className="main-selector" {...getRootProps()}>
            <input {...getInputProps()} />
            {
                !isOpened && ( 
                    <div className="selector-style">
                        Step 1: Drag Image
                        <img style={{padding: "10px"}} src={dnd} />
                    </div>
            )}
        </div>
    )

}