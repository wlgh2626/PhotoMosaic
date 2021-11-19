import { useCallback, useState } from "react";
import { useDropzone } from "react-dropzone";
import "./imageSelector.css";

function isFileImage(file) {
  const acceptedImageTypes = ["image/gif", "image/jpeg", "image/png"];
  return file && acceptedImageTypes.includes(file["type"]);
}

export default function ImageSelecter({ Image, setImage, setDisplay }) {
  const onDrop = useCallback((acceptedFiles) => {
    if (isFileImage(acceptedFiles[0])) {
      setDisplay(URL.createObjectURL(acceptedFiles[0]));
      setImage(acceptedFiles[0]);
    }
  }, []);

  const { getRootProps, getInputProps } = useDropzone({
    noClick: true,
    onDrop,
  });

  return (
    <div className="main-selector" {...getRootProps()}>
      <input {...getInputProps()} />
      {!Image && (
        <div className="selector-style">
          <label className="step">Step 1 of 3</label>
          <label className="instruction">Choose or Drag Photo</label>
        </div>
      )}
    </div>
  );
}
