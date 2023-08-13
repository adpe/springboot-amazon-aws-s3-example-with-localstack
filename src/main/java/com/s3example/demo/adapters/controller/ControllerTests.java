package com.s3example.demo.adapters.controller;


import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.s3example.demo.adapters.representation.BucketObjectRepresentation;
import com.s3example.demo.adapters.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/buckets/")
@RequiredArgsConstructor
public class ControllerTests {

    private final S3Service s3Service;

    @PostMapping(value = "/{bucketName}")
    public void createBucket(@PathVariable String bucketName, @RequestParam boolean publicBucket) {
        s3Service.createS3Bucket(bucketName, publicBucket);
    }

    @GetMapping
    public List<String> listBuckets() {
        return s3Service.listBuckets()
                .stream()
                .map(Bucket::getName)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{bucketName}")
    public void deleteBucket(@PathVariable String bucketName) {
        s3Service.deleteBucket(bucketName);
    }

    @PostMapping(value = "/{bucketName}/objects")
    public void createObject(@PathVariable String bucketName, @RequestBody BucketObjectRepresentation representaion, @RequestParam boolean publicObject) throws IOException {
        s3Service.putObject(bucketName, representaion, publicObject);
    }

    @GetMapping(value = "/{bucketName}/objects/{objectName}")
    public File downloadObject(@PathVariable String bucketName, @PathVariable String objectName) {
        s3Service.downloadObject(bucketName, objectName);
        return new File("./" + objectName);
    }

    @PatchMapping(value = "/{bucketSourceName}/objects/{objectName}/{bucketTargetName}")
    public void moveObject(@PathVariable String bucketSourceName, @PathVariable String objectName, @PathVariable String bucketTargetName) {
        s3Service.moveObject(bucketSourceName, objectName, bucketTargetName);
    }

    @GetMapping(value = "/{bucketName}/objects")
    public List<String> listObjects(@PathVariable String bucketName) {
        return s3Service.listObjects(bucketName).stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{bucketName}/objects/{objectName}")
    public void deleteObject(@PathVariable String bucketName, @PathVariable String objectName) {
        s3Service.deleteObject(bucketName, objectName);
    }

    @DeleteMapping(value = "/{bucketName}/objects")
    public void deleteObject(@PathVariable String bucketName, @RequestBody List<String> objects) {
        s3Service.deleteMultipleObjects(bucketName, objects);
    }
}
