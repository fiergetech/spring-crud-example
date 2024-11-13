package com.examplecrud.springbootcrud.controller;

import com.examplecrud.springbootcrud.model.FaceIdResultResponse;
import com.examplecrud.springbootcrud.model.SdkTokenRequest;
import com.examplecrud.springbootcrud.service.FaceIdResultService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaceIdResultController {

    private final FaceIdResultService faceIdResultService;

    public FaceIdResultController(FaceIdResultService faceIdResultService) {
        this.faceIdResultService = faceIdResultService;
    }

    // Endpoint untuk menerima sdkToken dan mendapatkan hasil FaceId
    @PostMapping("/getFaceIdResult")
    public ResponseEntity<FaceIdResultResponse> getFaceIdResult(@RequestBody SdkTokenRequest sdkTokenRequest) {
        try {
            // Panggil service untuk mendapatkan hasil FaceId
            FaceIdResultResponse response = faceIdResultService.getFaceIdResult(sdkTokenRequest);
            return ResponseEntity.ok(response);
        } catch (TencentCloudSDKException e) {
            // Jika ada error, kirimkan status error dan pesan exception
            return ResponseEntity.status(500).body(null);
        }
    }
}
