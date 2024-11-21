package com.examplecrud.springbootcrud.controller;

import com.examplecrud.springbootcrud.model.OCRRequest;
import com.examplecrud.springbootcrud.model.OCRResponse;
import com.examplecrud.springbootcrud.service.OCRService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OCRController {

    private final OCRService ocrService;

    public OCRController(OCRService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping("/ocr")
    public ResponseEntity<OCRResponse> recognizeIDCard(@RequestBody OCRRequest ocrRequest) {
        try {
            OCRResponse response = ocrService.recognizeIndonesiaIDCardOCR(ocrRequest);
            return ResponseEntity.ok(response);
        } catch (TencentCloudSDKException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
