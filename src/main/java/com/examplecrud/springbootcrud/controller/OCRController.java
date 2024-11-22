package com.examplecrud.springbootcrud.controller;

import com.examplecrud.springbootcrud.model.OCRRequest;
import com.examplecrud.springbootcrud.model.OCRResponse;
import com.examplecrud.springbootcrud.service.OCRService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ocr")
public class OCRController {

    private final OCRService ocrService;

    @PostMapping
    public ResponseEntity<OCRResponse> recognizeIDCard(@RequestBody OCRRequest ocrRequest) {
        try {
            OCRResponse response = ocrService.recognizeIndonesiaIDCardOCR(ocrRequest);
            return ResponseEntity.ok(response);
        } catch (TencentCloudSDKException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

