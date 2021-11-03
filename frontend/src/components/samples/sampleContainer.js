import xbutton from "../../icons/xbutton.png"

export default function SampleContainer( {fileList, setFile} ){
    function removeItem(fileToRemove){
        const newList = fileList.filter((file) => 
            file !== fileToRemove
        )
        setFile(newList);
    }

    const images = fileList.map((file) => (
        <span className="sample">
          <img src={xbutton} alt="x-button here" className="sample-option" onClick={() => removeItem(file)}/> 
          <img src={URL.createObjectURL(file)} alt="preview here" className="sample-preview"/>
        </span> 
    ))

    return(
        <div className="sample-container">
            {images}
        </div> 
    )
}