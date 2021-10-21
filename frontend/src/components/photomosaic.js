import S3 from "../apis/s3Connector"
import BackEnd from "../apis/backEndConnector"
 
async function photomosaic(setPhotoMosaic ,originalImage , sampleList){
    const directory = (new Date()).getTime().toString(36);
    
    const s3 = new S3(directory , originalImage , sampleList); 
    await s3.uploadToS3();

    const backEnd = new BackEnd(directory);
    setPhotoMosaic(await backEnd.getResultURL());
}

export default function Retrieve( {setPhotoMosaic , originalImage , sampleList} ){
    return (
        <div>
            <button onClick={()=> 
            photomosaic(setPhotoMosaic ,originalImage,sampleList)}>
            upload
            </button>
        </div>
    );
}