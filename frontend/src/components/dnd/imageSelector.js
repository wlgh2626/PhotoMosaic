import { useCallback, useState } from "react";
import { useDropzone } from "react-dropzone";
import "./imageSelector.css";

function isFileImage(file) {
  const acceptedImageTypes = ["image/gif", "image/jpeg", "image/png"];
  return file && acceptedImageTypes.includes(file["type"]);
}

export default function ImageSelecter({ setOriginal, setDisplay }) {
  const [isOpened, setIsOpened] = useState(false);

  const onDrop = useCallback((acceptedFiles) => {
    if (isFileImage(acceptedFiles[0])) {
      setDisplay(URL.createObjectURL(acceptedFiles[0]));
      setOriginal(acceptedFiles[0]);
      setIsOpened(true);
    }
  }, []);

  const { getRootProps, getInputProps } = useDropzone({
    noClick: true,
    onDrop,
  });

  return (
    <div className="main-selector" {...getRootProps()}>
      <input {...getInputProps()} />
      {
        //If there is an image open, dont show the Drag image guide
        !isOpened && (
          <div className="selector-style">
            Step 1 of 3:
            <br />
            Choose a photo
            <br />
            or drag a photo
          </div>
        )
      }
    </div>
  );
}
