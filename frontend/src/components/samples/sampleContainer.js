import xbutton from "../../icons/xbutton.png"

export default function SampleContainer( {fileList, setFile} ){
    function removeItem(fileToRemove){
        const newList = fileList.filter((file) => 
          file.preview !== fileToRemove.preview
        )
        setFile(newList);
    }

    const images = fileList.map((file) => (
        <span className="sample">
          <img src={xbutton} alt="x-button here" className="sample-option" onClick={() => removeItem(file)}/> 
          <img src={file.preview} alt="preview here" className="sample-preview"/>
        </span> 
    ))

    return(
        <div className="sample-container">
            {images}
        </div> 
    )
}