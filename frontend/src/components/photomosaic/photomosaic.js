import S3Connector from "./apis/s3Connector";
import BackEnd from "./apis/backEndConnector";

export default function Retrieve({ originalImage, sampleList, setPhotoMosaic, setIsLoading }) {
  async function photomosaic(originalImage, sampleList, setPhotoMosaic, setIsLoading) {
    setIsLoading(true);
    const directory = new Date().getTime().toString(36);
    const isUploaded = await S3Connector(directory, originalImage, sampleList);
    if (isUploaded) {
      console.log("Upload sucess! Now signaling server");
      const imageURL = await BackEnd(directory);
      setPhotoMosaic(imageURL);
      setIsLoading(false);
    }
  }

  return (
    <button
      disabled={!originalImage || !sampleList}
      onClick={() => photomosaic(originalImage, sampleList, setPhotoMosaic, setIsLoading)}
    >
      PhotoMosaic
    </button>
  );
}
