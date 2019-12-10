package com.pujitha.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.*;
import java.util.List;

public class ListS3Buckets {

    public static void main(String[] arg) {
        String bucketName = "result-jsons";
        String keyName = "basicsimulation-20191210205629914/js/stats.json";
        ListS3Buckets listS3Buckets = new ListS3Buckets();
        listS3Buckets.listBuckets();
        BufferedReader reader = new BufferedReader(new InputStreamReader(listS3Buckets.getObject(bucketName, keyName)));
        String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private void listBuckets() {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        List<Bucket> buckets = s3.listBuckets();
        System.out.println("Yoour Buckets are : ");
        for (Bucket b : buckets ) {
            System.out.println("* " + b.getName());
        }
    }

    private InputStream getObject(String bucketName, String keyName) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        S3Object ojbect = s3.getObject(new GetObjectRequest(bucketName, keyName));
        InputStream objectInputStream = ojbect.getObjectContent();
        return objectInputStream;

    }

}
