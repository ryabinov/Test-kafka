package com.example.miniotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinioTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioTestApplication.class, args);
    }

}
//Console: http://172.17.0.2:9001 http://127.0.0.1:9001
//        docker run -p 9000:9000 -p 9001:9001 --name minio1 -v C:\my_programs\data:/data -e "MINIO_ROOT_USER=admin" -e "MINIO_ROOT_PASSWORD=admin" quay.io/minio/minio server /data --console-address ":9001"