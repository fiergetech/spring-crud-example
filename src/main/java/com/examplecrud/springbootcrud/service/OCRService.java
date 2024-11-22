package com.examplecrud.springbootcrud.service;

import com.examplecrud.springbootcrud.model.OCRRequest;
import com.examplecrud.springbootcrud.model.OCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.RecognizeIndonesiaIDCardOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.RecognizeIndonesiaIDCardOCRRequest;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OCRService {

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    private static final String ENDPOINT = "ocr.tencentcloudapi.com";
    private static final String REGION = "ap-singapore";

    public OCRResponse recognizeIndonesiaIDCardOCR(OCRRequest ocrRequest) throws TencentCloudSDKException {
        OcrClient client = createOcrClient();
        RecognizeIndonesiaIDCardOCRRequest req = buildOcrRequest(ocrRequest);

        try {
            RecognizeIndonesiaIDCardOCRResponse resp = client.RecognizeIndonesiaIDCardOCR(req);
            return mapToOCRResponse(resp);
        } catch (TencentCloudSDKException e) {
            logError(e);
            return buildErrorResponse("500", "Failed to recognize ID Card: " + e.getMessage());
        }
    }

    private OcrClient createOcrClient() {
        Credential cred = new Credential(secretId, secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(ENDPOINT);

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        return new OcrClient(cred, REGION, clientProfile);
    }

    private RecognizeIndonesiaIDCardOCRRequest buildOcrRequest(OCRRequest ocrRequest) {
        RecognizeIndonesiaIDCardOCRRequest req = new RecognizeIndonesiaIDCardOCRRequest();
        req.setImageBase64(ocrRequest.getImageBase64());
        req.setImageUrl(ocrRequest.getImageUrl());
        req.setReturnHeadImage(ocrRequest.getReturnHeadImage());
        req.setScene(ocrRequest.getScene());
        return req;
    }

    private OCRResponse mapToOCRResponse(RecognizeIndonesiaIDCardOCRResponse resp) {
        OCRResponse response = new OCRResponse();
        response.setResponseCode("200");
        response.setResponseMessage("Success");

        OCRResponse.DataContent dataContent = new OCRResponse.DataContent();
        dataContent.setAgama(resp.getAgama());
        dataContent.setAlamat(resp.getAlamat());
        dataContent.setBerlakuHingga(resp.getBerlakuHingga());
        dataContent.setGolDarah(resp.getGolDarah());
        dataContent.setIssuedDate(resp.getIssuedDate());
        dataContent.setJenisKelamin(resp.getJenisKelamin());
        dataContent.setKecamatan(resp.getKecamatan());
        dataContent.setKelDesa(resp.getKelDesa());
        dataContent.setKewargaNegaraan(resp.getKewargaNegaraan());
        dataContent.setKota(resp.getKota());
        dataContent.setNik(resp.getNIK());
        dataContent.setNama(resp.getNama());
        dataContent.setPerkerjaan(resp.getPerkerjaan());
        dataContent.setPhoto(resp.getPhoto());
        dataContent.setProvinsi(resp.getProvinsi());
        dataContent.setRtRw(resp.getRTRW());
        dataContent.setRequestId(resp.getRequestId());
        dataContent.setStatusPerkawinan(resp.getStatusPerkawinan());
        dataContent.setTempatTglLahir(resp.getTempatTglLahir());
        dataContent.setWarnCardInfos(resp.getWarnCardInfos());

        response.setData(dataContent);
        return response;
    }

    private OCRResponse buildErrorResponse(String rc, String rm) {
        OCRResponse errorResponse = new OCRResponse();
        errorResponse.setResponseCode(rc);
        errorResponse.setResponseMessage(rm);
        errorResponse.setData(null);
        return errorResponse;
    }

    private void logError(TencentCloudSDKException e) {
        log.error("Error: {}", e.getMessage());
        log.error("Error details: {}", e.toString());
    }
}

