package com.examplecrud.springbootcrud.controller;

import com.examplecrud.springbootcrud.model.FaceIdResponse;
import com.examplecrud.springbootcrud.service.FaceIdService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaceIdController {

    private final FaceIdService faceIdService;

    public FaceIdController(FaceIdService faceIdService) {
        this.faceIdService = faceIdService;
    }

    @GetMapping("/getFaceIdToken")
    public ResponseEntity<FaceIdResponse> getFaceIdToken() {
        try {
            FaceIdResponse response = faceIdService.getFaceIdToken();
            return ResponseEntity.ok(response);
        } catch (TencentCloudSDKException e) {
            return ResponseEntity.status(500).body(null); 
        }
    }
}
