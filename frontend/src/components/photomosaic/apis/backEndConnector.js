
const port = process.env.PHOTO_MOSAIC_PORT
const url = process.env.PHOTO_MOSAIC_URL;
const targetURL = new URL(url + ":" + port);

const params = {
    mode: "cors",
    method: "get",
    headers: {
        "Access-Control-Allow-Origin": targetURL.origin
    }
};

export default async function BackEnd( directory ) {

    return new Promise((resolve,reject) =>{
        fetch( targetURL.href + "photomosaic?s3=" + directory, params).then(res=> {
            if(res.ok){
                console.log("successful mosaic return");
                res.text().then(body=>{
                    console.log(body);
                    resolve(body);
                })
            } else {
                console.log("unsuccessful mosaic return");   
                reject("Failed to Retrieve");
            }
        })
    })
}