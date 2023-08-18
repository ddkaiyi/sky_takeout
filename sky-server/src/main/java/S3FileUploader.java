import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.File;

public class S3FileUploader {

    public static void main(String[] args) {
        String accessKeyId = "AKIAZG5ZU7666MI7JRHE";
        String secretAccessKey = "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";
        String bucketName = "kkaiyidatabase";
        String objectKey = "a.txt"; // 设置 S3 存储桶中的对象键
        byte[] fileContent = "Hello, AWS S3!".getBytes();
//        String localFilePath = "/Users/kaiyidai/Downloads/1、黑马程序员Java项目《苍穹外卖》企业级开发实战/资料/day01/后端初始工程/sky-take-out/sky-server/src/main/java/a.txt"; // 本地文件路径

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey)))
                .withRegion(Regions.US_EAST_2)  // 设置适当的 AWS 区域
                .build();

        try {
//            File file = new File(localFilePath);
            PutObjectRequest request = new PutObjectRequest(bucketName, objectKey, new ByteArrayInputStream(fileContent), null);
            s3Client.putObject(request);
            System.out.println("File uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
