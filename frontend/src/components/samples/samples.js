import {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import dnd from "../../icons/dnd.png"
import xbutton from "../../icons/xbutton.png"
import "./samples.css"

function isFileImage(file) {
  const acceptedImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
  return file && acceptedImageTypes.includes(file['type'])
}

/**
 * Holds and displays list of file images to be used.
 * Must be of type image/*
 * Uses drag and drop from react-dropzone
 * 
 * @public
 * @param {string} { files[] , setFiles }
 * @example Samples( bananaFileList , setBananaFileList)
 * @returns Drag and drop container for images
 */
export default function Samples( {fileList, setFile }) {
    const onDrop = useCallback(acceptedFiles => {
      acceptedFiles.map( file =>{
        if(isFileImage(file)){
          setFile( fileList => [...fileList , file]); 
        }
      })
    }, [])

    const {getRootProps, getInputProps} = useDropzone({noClick:true, onDrop })

    function removeItem(fileToRemove){
      const newList = fileList.filter((file) => 
          file !== fileToRemove
      )
      setFile(newList);
    }

    const samples = fileList.map((file) => (
      <span className="sample">
        <img src={xbutton} alt="x-button-here" className="sample-option" onClick={() => removeItem(file)}/> 
        <img src={URL.createObjectURL(file)} alt="preview here" className="sample-preview"/>
      </span> 
    ))

    return (
      <div className="dnd-container" {...getRootProps()}>
        <input {...getInputProps()} />
          {samples}
          { 
            //Show instructions to drag in images, if the dbd-container is empty
            (fileList.length === 0) && (
              <div className="sample-dnd-style">
                Step 2: Drag Samples <img style={{padding: "10px"}} src={dnd} />
              </div>
          )} 
      </div>
    )
  }