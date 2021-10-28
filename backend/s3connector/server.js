import express from "express"
import { S3ConnectionURL } from './s3Connector.js'

const app = express();

app.use(express.static('front'));

app.get('/requestS3Url', async (req, res) => {
  const data = await S3ConnectionURL(req.query.key);
  console.log(data);
  res.send({data});
})

app.listen(8080, () => console.log("listening on port 8080"));