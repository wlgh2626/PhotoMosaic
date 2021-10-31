import AWS from "aws-sdk";
import dotenv from "dotenv";

dotenv.config();

const s3BucketName = process.env.S3_BUCKET_NAME;
const s3Region = process.env.S3_REGION;
const s3AccessKey = process.env.S3_ACCESS;
const s3SecretKey = process.env.S3_SECRET;

//The exact attribute name is important!
AWS.config.update({
    accessKeyId: s3AccessKey,
    secretAccessKey: s3SecretKey,
    region: s3Region
})
const s3 = new AWS.S3();

export async function S3ConnectionURL(key) {
    var params = ({
        Bucket: s3BucketName,
        Key: key,
        Expires: 60
    })

    const s3URL = await s3.getSignedUrlPromise('putObject' , params)
    return s3URL;
}