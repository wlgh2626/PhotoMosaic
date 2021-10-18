import S3 from "../apis/s3Connector"
import BackEnd from "../apis/backEndConnector"
 
async function photomosaic(originalImage , sampleList){
    const directory = (new Date()).getTime().toString(36);
    const backEnd = new BackEnd();
    const s3 = new S3(directory , originalImage , sampleList); 

    s3.uploadToS3();
    backEnd.signalAndWait();
}

export default function Retrieve( {photoMosaic, setPhotoMosaic , originalImage , sampleList} ){
    return (
        <div>
            <button onClick={()=> photomosaic(originalImage,sampleList)}>upload</button>
        </div>
    );
}