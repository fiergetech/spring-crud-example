package com.examplecrud.springbootcrud.controller;

import com.examplecrud.springbootcrud.model.OcrRequest;
import com.examplecrud.springbootcrud.model.OcrResponse;
import com.examplecrud.springbootcrud.service.OcrService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OcrController {

    private final OcrService ocrService;

    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping("/ocr")
    public ResponseEntity<OcrResponse> recognizeIDCard(@RequestBody OcrRequest ocrRequest) {
        try {
            OcrResponse response = ocrService.recognizeIndonesiaIDCardOCR(ocrRequest);
            return ResponseEntity.ok(response);
        } catch (TencentCloudSDKException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
