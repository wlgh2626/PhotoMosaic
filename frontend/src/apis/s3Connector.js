import AWS from "aws-sdk";
import {ID} from "../auth/id.js";
import Path, { resolve } from "path";

const s3Region = "us-east-2";
const s3BucketName = "js-image-storage";
const s3AccessKey = ID.accesskey;
const s3SecretKey = ID.secretKey;

//The exact attribute name is important!
AWS.config.update({
    accessKeyId: s3AccessKey,
    secretAccessKey: s3SecretKey,
    region: s3Region
})
const s3 = new AWS.S3();

export default async function S3Connector(directory , originalImage , sampleList) {
    
    async function upload(image , imageKey){
        var params = ({
            Bucket: s3BucketName,
            Key: imageKey,
            Body: image,
        })
        
        return new Promise((resolve,reject)=>{
            s3.putObject( params , (err , data)=> {
                if(data){
                    console.log("Successfully uploaded");
                    resolve(true);
                } if(err) {
                    reject(false);
                }
            });  
        })     
    }

    async function uploadMultiple(files, prefix){
        var i = 0;
        const promises = files.map(async file =>{
            var imageKey = prefix + (i++) + Path.extname(file.name); 
            const isUploaded = await upload(file.file , imageKey);

            return isUploaded;
        })

        const results = await Promise.all(promises);
        return results;
    }

    if((originalImage) && (sampleList.length !== 0)){

        console.log("Started Uploading...")
        const originalUploaded = await upload(originalImage , directory+"/original"+ Path.extname(originalImage.name))
        const samplesUploaded = await uploadMultiple(sampleList , directory+"/sample");
        if((originalUploaded && samplesUploaded)){
            console.log("Finished All Uploads")
        } else {
            console.log("Uploading to S3 Failed...")
        }
        return Promise.resolve( (originalUploaded && samplesUploaded));
    } else {
        return Promise.reject(false);
    }

}