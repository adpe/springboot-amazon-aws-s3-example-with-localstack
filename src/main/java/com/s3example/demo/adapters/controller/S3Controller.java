package com.s3example.demo.adapters.controller;


import com.s3example.demo.adapters.representation.S3BucketObjectRepresentation;
import com.s3example.demo.adapters.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/buckets")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/{bucketName}")
    public void createBucket(@PathVariable String bucketName) {
        s3Service.createBucket(bucketName);
    }

    @GetMapping
    public List<String> listBuckets() {
        return s3Service.listBuckets()
                .stream()
                .map(Bucket::name)
                .collect(toList());
    }

    @DeleteMapping("/{bucketName}")
    public void deleteBucket(@PathVariable String bucketName) {
        s3Service.deleteBucket(bucketName);
    }

    @PostMapping("/{bucketName}/objects")
    public void putObject(@PathVariable String bucketName, @RequestBody S3BucketObjectRepresentation s3BucketObjectRepresentation) {
        s3Service.putObject(bucketName, s3BucketObjectRepresentation);
    }

    @GetMapping("/{bucketName}/objects/{objectName}")
    public File getObject(@PathVariable String bucketName, @PathVariable String objectName) {
        s3Service.getObject(bucketName, objectName);
        return new File("./" + objectName);
    }

    @PatchMapping("/{sourceBucketName}/objects/{objectName}/{targetBucketName}")
    public void moveObject(@PathVariable String sourceBucketName, @PathVariable String objectName, @PathVariable String targetBucketName) {
        s3Service.moveObject(sourceBucketName, objectName, targetBucketName);
    }

    @GetMapping("/{bucketName}/objects")
    public List<String> listObjects(@PathVariable String bucketName) {
        return s3Service.listObjects(bucketName)
                .stream()
                .map(S3Object::key)
                .collect(toList());
    }

    @DeleteMapping("/{bucketName}/objects/{objectName}")
    public void deleteObject(@PathVariable String bucketName, @PathVariable String objectName) {
        s3Service.deleteObject(bucketName, objectName);
    }

    @DeleteMapping("/{bucketName}/objects")
    public void deleteObjects(@PathVariable String bucketName, @RequestBody List<String> objects) {
        s3Service.deleteObjects(bucketName, objects);
    }
}
