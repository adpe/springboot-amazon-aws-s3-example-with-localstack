# Amazon AWS SDK S3 Example 

Spring Boot project that interacts with Amazon S3 buckets over the official Java Amazon AWS SDK.
(Optional is possible to interact with a local AWS setup using LocalStack (pay attention to the used Docker image!).

It's using following stuff:
- Java, version 17
- Maven
- Spring Boot, version 3
- Amazon SDK for Java, version 2

Before you run the project, please consider changing the properties in `resources/application.properties`.

This command could be helpful to run the application:
```shell
./mvnw spring-boot:run
```

If you want to make use of LocalStack just run this command:
```shell
docker-compose up -d
```
(Please check out the default environment variables.)

After you can interact with the application using Swagger UI http://localhost:8080/swagger-ui/index.html. A basic overview of all buckets can be viewed with help of http://localhost:8080/buckets.

This project is based on the Medium article https://mmarcosab.medium.com/how-about-s3-bucket-and-localstack-b0816bab452a and relating GitHub project https://github.com/mmarcosab/s3-example.
