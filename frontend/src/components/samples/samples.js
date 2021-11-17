import { useCallback } from "react";
import { useDropzone } from "react-dropzone";
import dnd from "../../icons/dnd.png";
import xbutton from "../../icons/xbutton.png";
import "./samples.css";

function isFileImage(file) {
  const acceptedImageTypes = ["image/gif", "image/jpeg", "image/png"];
  return file && acceptedImageTypes.includes(file["type"]);
}

/**
 * Holds and displays list of file images to be used.
 * Must be of type image/*
 * Uses dnd from react-dropzone
 *
 * @public
 * @param {string} { files[] , setFiles }
 * @example Samples( bananaFileList , setBananaFileList)
 * @returns Drag and drop container for images
 */
export default function Samples({ fileList, setFile }) {
  const onDrop = useCallback((acceptedFiles) => {
    acceptedFiles.map((file) => {
      if (isFileImage(file)) {
        setFile((fileList) => [...fileList, file]);
      }
    });
  }, []);

  const { getRootProps, getInputProps } = useDropzone({
    noClick: true,
    onDrop,
  });

  function removeItem(fileToRemove) {
    const newList = fileList.filter((file) => file !== fileToRemove);
    setFile(newList);
  }

  const samples = fileList.map((file) => (
    <span className="sample">
      <img
        src={xbutton}
        alt="x-button-here"
        className="sample-option"
        onClick={() => removeItem(file)}
      />
      <img src={URL.createObjectURL(file)} alt="preview here" className="sample-preview" />
    </span>
  ));

  return (
    <div className="samples">
      <div className="dnd-container" {...getRootProps()}>
        <input {...getInputProps()} />
        {samples}
      </div>
      <div className="selector-style">
        <label className="step">Step 2 of 3</label>
        <label className="instruction">Choose or Drag Photos to Sample</label>
      </div>
    </div>
  );
}
