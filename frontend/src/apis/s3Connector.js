import AWS from "aws-sdk";
import {ID} from "../auth/id.js";
import Path from "path";

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

export default class S3Connector {
    constructor(directory , originalImage , sampleList){
        this.directory = directory;
        this.originalImage = originalImage;
        this.sampleList = sampleList;
    }

    async upload(image , imageKey){
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

    async uploadToS3(){
        if((this.originalImage) && (this.sampleList.length != 0)){
            this.upload(this.originalImage , this.directory+"/original"+ Path.extname(this.originalImage.name))
    
            for(var i = 0 ; i < this.sampleList.length ; i ++){
                var imageKey = this.directory + "/sample" + i + Path.extname(this.sampleList[i].name);  
                this.upload( this.sampleList[i].file , imageKey)
            }  
        } else {
            console.log("Missing images!");
        }
    }
}