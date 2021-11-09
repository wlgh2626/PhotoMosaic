import express from "express"
import https from "https"
import cors from "cors"
import fs from "fs"
import { S3ConnectionURL } from './s3Connector.js'

const portNumber = process.env.SERVER_PORT
const app = express();

app.use(cors({origin: true}));
app.get('/requestS3Url', async (req, res) => {
  const data = await S3ConnectionURL(req.query.key);
  console.log(data);
  res.send(data);
})

app.get('/ping', async (req, res) => {
  res.send("pong");
})

const sslServer = https.createServer({
  key: fs.readFileSync("./cert/key.pem"),
  cert: fs.readFileSync("./cert/cert.pem")
},app)

sslServer.listen(portNumber, () => console.log("listening on port " + portNumber) );