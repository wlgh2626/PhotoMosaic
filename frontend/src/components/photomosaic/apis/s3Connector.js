import Path from "path";

const s3ProviderUrl = (process.env.REACT_APP_S3_CONNECTOR_URL) + ":" + (process.env.REACT_APP_S3_CONNECTOR_PORT);

export default async function S3Connector(directory , originalImage , sampleList) {
    async function upload(image , imageKey){
        //receive a signed S3 URL from the server
        const url = await fetch(s3ProviderUrl + "/requestS3Url?key=" + imageKey).then(res => {
            return res.text()
        });

        const params = ({
            method: "PUT",
            body: image
        })

        return new Promise((resolve,reject)=>{
            fetch(url , params ).then(res=>{
                if (res.ok){
                    console.log("Succesfully uploaded ")
                    resolve(true)
                } else {
                    console.log("Upload failed ")
                    reject(false)
                }
            })
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

        return Promise.resolve( (originalUploaded && samplesUploaded));
    } else {
        return Promise.reject(false);
    }

}