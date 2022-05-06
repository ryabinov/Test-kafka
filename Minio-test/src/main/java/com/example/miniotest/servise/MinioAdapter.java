package com.example.miniotest.servise;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.messages.Bucket;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class MinioAdapter {


    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    String defaultBucketName;

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

@Autowired
public MinioAdapter(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    public void uploadFile(String name, byte[] content) {
        File file = new File("/tmp/" + name);
        file.canWrite();
        file.canRead();
        try {
            FileOutputStream iofs = new FileOutputStream(file);
            iofs.write(content);
//            minioClient.putObject(defaultBucketName, defaultBaseFolder + name, file.getAbsolutePath());
            minioClient.
                    putObject(PutObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .region(file.getAbsolutePath())
                    .object(defaultBaseFolder + name)
                    .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public byte[] getFile(String key) {
        try {
//            InputStream obj = minioClient.getObject(defaultBucketName, defaultBaseFolder + "/" + key);
            InputStream obj = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(defaultBaseFolder + "/" + key)
                    .build());

            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
