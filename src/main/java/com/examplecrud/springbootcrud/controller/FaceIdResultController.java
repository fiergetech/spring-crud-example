package com.examplecrud.springbootcrud.controller;

import com.examplecrud.springbootcrud.model.FaceIdResultResponse;
import com.examplecrud.springbootcrud.model.SdkTokenRequest;
import com.examplecrud.springbootcrud.service.FaceIdResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FaceIdResultController {

    private final FaceIdResultService faceIdResultService;

    @PostMapping("/face-id/results")
    public ResponseEntity<FaceIdResultResponse> getFaceIdResult(@RequestBody SdkTokenRequest sdkTokenRequest) {
        log.info("Received request to get Face ID result for SDK token: {}", sdkTokenRequest);
        FaceIdResultResponse response = faceIdResultService.getFaceIdResult(sdkTokenRequest);
        return ResponseEntity.ok(response);
    }
}
