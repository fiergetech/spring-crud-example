package com.examplecrud.springbootcrud.controller;

import com.examplecrud.springbootcrud.model.FaceIdResponse;
import com.examplecrud.springbootcrud.service.FaceIdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FaceIdController {

    private final FaceIdService faceIdService;

    @GetMapping("/face-id/token")
    public ResponseEntity<FaceIdResponse> getFaceIdToken() {
        log.info("Received request to get Face ID token");
        FaceIdResponse response = faceIdService.getFaceIdToken();
        return ResponseEntity.ok(response);
    }
}
