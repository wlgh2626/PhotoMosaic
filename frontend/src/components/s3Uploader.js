import {useState} from "react"
import AWS from "aws-sdk";
import {ID} from "./id.js"

const region = "us-east-2";
const bucketName = "js-image-storage";
const accessKey = ID.accesskey;
const secretKey = ID.secretKey;

AWS.config.update({
    accessKeyId: accessKey,
    secretAccessKey: secretKey,
    region: region
})

var s3 = new AWS.S3();

async function send(originalImage , sampleList){
    const imageKey = "uniqueimageid.txt";
    const params = ({
        Bucket: bucketName,
        Key: imageKey,
        Body: "Hello",
    })
    s3.putObject( params , function(err , data) {
        if (err) {
            console.log("Error", err);
        } if (data) {
            console.log("Upload Success", data.Location);
        }
    });
}

export default function UploadImages( {originalImage , sampleList} ){;
    return (
        <div>
            <button onClick={()=> send(originalImage,sampleList)}>upload</button>
        </div>
    );
}