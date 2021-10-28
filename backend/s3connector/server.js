import express from "express"
import cors from "cors"
import { S3ConnectionURL } from './s3Connector.js'

const portNumber = process.env.SERVER_PORT
const app = express();
app.use(cors());

app.get('/requestS3Url', async (req, res) => {
  const data = await S3ConnectionURL(req.query.key);
  console.log(data);
  res.send(data);
})

app.listen(portNumber, () => console.log("listening on port " + portNumber) );