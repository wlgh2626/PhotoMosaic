import S3Connector from "./apis/s3Connector"
import BackEnd from "./apis/backEndConnector"
 
export default function Retrieve( {originalImage , sampleList , setPhotoMosaic , setDisplay } ){

    async function photomosaic(originalImage, sampleList , setPhotoMosaic){
        const directory = (new Date()).getTime().toString(36);

        console.log(originalImage);
        console.log(directory);
        const isUploaded = await S3Connector( directory , originalImage , sampleList);
        if(isUploaded){
            console.log("Upload sucess! Now signaling server")
            const imageURL = await BackEnd(directory);
            setPhotoMosaic(imageURL);
            setDisplay(imageURL);
        }
    }

    return (
        <span className="photomosaic">
            <button onClick={()=> photomosaic(originalImage, sampleList , setPhotoMosaic)}>
                Photo Mosaic
            </button>
        </span>    
    );
}