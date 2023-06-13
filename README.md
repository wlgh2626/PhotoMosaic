# PhotoMosaic
![alt text](https://js-image-storage.s3.us-east-2.amazonaws.com/SampleResults/duck.jpg)
![alt text](https://js-image-storage.s3.us-east-2.amazonaws.com/SampleResults/DucksaicResized.png)

# What is Photo Mosaic?
Photo mosaic is building an image, thorugh the use of other images

# Running and Deploying the Application
  **Pre-Requisite**  
  Maven 3+  
  Java 11+    
  Spring 5+  
  Node.js 16+  
  
  **Running Java Spring Application in CLI**
  ```
    cd photomosaic
    mvn spring-boot:run
  ```
  **Running Node.js S3 server** 
 ```
    cd backend/s3connector
    npm start
  ```
  
  **Running Node.js frontend**
  ```
    cd frontend
    npm start
  ```  
  
# How to Use the Website
 Step 1: Drag an image you would like to transform.    
 Step 2: Drag images you would like to use to build the transformed with.     
 Step 3: Press "Photomosaic" button and wait. **Note:** It may take up to a minute to generate image, as it is resource intensive.
