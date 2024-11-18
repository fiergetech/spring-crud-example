package com.examplecrud.springbootcrud.service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.RecognizeIndonesiaIDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.RecognizeIndonesiaIDCardOCRResponse;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.profile.ClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.examplecrud.springbootcrud.model.OcrRequest;
import com.examplecrud.springbootcrud.model.OcrResponse;

@Service
public class OcrService {

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    public OcrResponse recognizeIndonesiaIDCardOCR(OcrRequest ocrRequest) throws TencentCloudSDKException {
        Credential cred = new Credential(secretId, secretKey);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("ocr.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        OcrClient client = new OcrClient(cred, "ap-singapore", clientProfile);

        RecognizeIndonesiaIDCardOCRRequest req = new RecognizeIndonesiaIDCardOCRRequest();

        req.setImageBase64(ocrRequest.getImageBase64());
        req.setImageUrl(ocrRequest.getImageUrl());
        req.setReturnHeadImage(ocrRequest.getReturnHeadImage());
        req.setScene(ocrRequest.getScene());

        try {
            RecognizeIndonesiaIDCardOCRResponse resp = client.RecognizeIndonesiaIDCardOCR(req);

            OcrResponse response = new OcrResponse();
            OcrResponse.Response responseContent = new OcrResponse.Response();
            
            responseContent.setAgama(resp.getAgama());
            responseContent.setAlamat(resp.getAlamat());
            responseContent.setBerlakuHingga(resp.getBerlakuHingga());
            responseContent.setGolDarah(resp.getGolDarah());
            responseContent.setIssuedDate(resp.getIssuedDate());
            responseContent.setJenisKelamin(resp.getJenisKelamin());
            responseContent.setKecamatan(resp.getKecamatan());
            responseContent.setKelDesa(resp.getKelDesa());
            responseContent.setKewargaNegaraan(resp.getKewargaNegaraan());
            responseContent.setKota(resp.getKota());
            responseContent.setNIK(resp.getNIK());
            responseContent.setNama(resp.getNama());
            responseContent.setPerkerjaan(resp.getPerkerjaan());
            responseContent.setPhoto(resp.getPhoto());
            responseContent.setProvinsi(resp.getProvinsi());
            responseContent.setRTRW(resp.getRTRW());
            responseContent.setRequestId(resp.getRequestId());
            responseContent.setStatusPerkawinan(resp.getStatusPerkawinan());
            responseContent.setTempatTglLahir(resp.getTempatTglLahir());
            responseContent.setWarnCardInfos(resp.getWarnCardInfos());

            response.setResponse(responseContent);
            
            return response;

        } catch (TencentCloudSDKException e) {
            System.err.println("Error : " + e.getMessage());
            System.err.println("Error details: " + e.toString());
            throw new TencentCloudSDKException("Failed to recognize ID Card: " + e.getMessage());
        }
    }
}
