
const targetURL = "http://ec2-3-16-232-112.us-east-2.compute.amazonaws.com:8080/";
const params = {
    mode: 'no-cors',
    headers: {
      'Access-Control-Allow-Origin':'*'
    }
};

export default class BackEnd {
    constructor(directory){
        this.directory = directory;
    }

    async signalAndWait(){
        fetch(targetURL + "/ping", params).then(res=> {
            if(res.ok){
               return true; 
            } else {
               return false;     
            }
        })
    }
}