import { useState } from "react/cjs/react.development";

const port = "8081";
const url = "http://ec2-3-16-232-112.us-east-2.compute.amazonaws.com";
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
        console.log("dr is:" + directory);
        fetch( targetURL.href + "photomosaic?s3=" + directory, params).then(res=> {
            if(res.ok){
                console.log("successful return");
                res.text().then(body=>{
                    console.log(body);
                    resolve(body);
                })
            } else {
                console.log("unsuccessful return");   
                reject("Failed to Retrieve");
            }
        })
    })
}