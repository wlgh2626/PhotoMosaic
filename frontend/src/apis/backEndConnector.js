
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

export default class BackEnd {
    constructor(directory){
        this.directory = directory;
    }

    async getResultURL(){
        fetch( targetURL.href + "photomosaic?s3=" + this.directory, params).then(res=> {
            if(res.ok){
                console.log("successful return");
                res.text().then(body=>{
                    console.log(body);
                    return body;
                })
            } else {
                console.log("unsuccessful return");
                return false;     
            }
        })
    }
}