import {useState} from "react";
import AWS from "aws-sdk";
import {ID} from "./id.js";
import Path from "path";

const photoMosaicURL = "";
const s3Region = "us-east-2";
const s3BucketName = "js-image-storage";
const s3AccessKey = ID.accesskey;
const s3SecretKey = ID.secretKey;

/*
 * Uploads an Image for Photo Mosaic to s3  (originalImage)
 * Uploads samples to construct Photo Mosaic to s3(sampleList)
*/
AWS.config.update({
    accessKeyId: s3AccessKey,
    secretAccessKey: s3SecretKey,
    region: s3Region
})

var s3 = new AWS.S3();

async function uploadImageS3(image , imageKey){
    var params = ({
        Bucket: s3BucketName,
        Key: imageKey,
        Body: image,
    })

    s3.putObject( params , function(err , data) {
        if (err) {
            console.log("Error", err);
        } if (data) {
            console.log("Upload Success");
        }
    });  
}

async function upload(originalImage , sampleList){
    const directory = (new Date()).getTime().toString(36);

    if((originalImage) && (sampleList.length != 0)){
        uploadImageS3(originalImage , directory+"/original"+ Path.extname(originalImage.name))

        for(var i = 0 ; i < sampleList.length ; i ++){
            var imageKey = directory + "/sample" + i + Path.extname(sampleList[i].name);  
            uploadImageS3( sampleList[i].file , imageKey)
        }
    } else {
        console.log("Missing images!");
    }
}

function requestPhotoMosaic(){

}

export default function UploadImages( {photoMosaic, setPhotoMosaic , originalImage , sampleList} ){;
    return (
        <div>
            <button onClick={()=> upload(originalImage,sampleList)}>upload</button>
        </div>
    );
}