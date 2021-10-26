import S3Connector from "../apis/s3Connector"
import BackEnd from "../apis/backEndConnector"
 
export default function Retrieve( {setPhotoMosaic , setDisplay, originalImage , sampleList} ){

    async function photomosaic(setPhotoMosaic ,originalImage , sampleList){
        const directory = (new Date()).getTime().toString(36);

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
        <span>
            <button onClick={()=> photomosaic(setPhotoMosaic, originalImage, sampleList)}>
                upload
            </button>
        </span>    
    );
}