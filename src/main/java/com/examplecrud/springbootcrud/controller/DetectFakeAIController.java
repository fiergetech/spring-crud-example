package com.examplecrud.springbootcrud.controller;

import com.examplecrud.springbootcrud.model.*;
import com.examplecrud.springbootcrud.service.DetectFakeAiService;
import com.examplecrud.springbootcrud.service.FaceIdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DetectFakeAIController {

    private final DetectFakeAiService detectFakeAiService;

    @PostMapping("/detect-ai")
    public ResponseEntity<FakeIdResponse> detectFakeId(@RequestBody FakeIdRequest fakeIdRequest) {
        log.info("Received request to detect Fake ID...");
        FakeIdResponse response = detectFakeAiService.detectFakeAi(fakeIdRequest);
        return ResponseEntity.ok(response);
    }
}
