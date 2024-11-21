package com.examplecrud.springbootcrud.service;

import com.examplecrud.springbootcrud.model.FaceIdResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.GetFaceIdTokenIntlRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetFaceIdTokenIntlResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FaceIdService {

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    private Credential createCredential() {
        return new Credential(secretId, secretKey);
    }

    public FaceIdResponse getFaceIdToken() {
        try {
            log.info("Generating Face ID token...");
            Credential cred = createCredential();
            FaceidClient client = new FaceidClient(cred, "ap-jakarta");

            GetFaceIdTokenIntlRequest req = new GetFaceIdTokenIntlRequest();
            GetFaceIdTokenIntlResponse resp = client.GetFaceIdTokenIntl(req);

            FaceIdResponse faceIdResponse = new FaceIdResponse();
            faceIdResponse.setSdkToken(resp.getSdkToken());
            faceIdResponse.setRequestId(resp.getRequestId());

            log.info("Successfully generated Face ID token. Request ID: {}", resp.getRequestId());
            return faceIdResponse;
        } catch (TencentCloudSDKException e) {
            log.error("Failed to generate Face ID token: {}", e.getMessage(), e);
            throw new RuntimeException("Error while generating Face ID token", e);
        }
    }
}
