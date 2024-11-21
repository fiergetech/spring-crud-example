package com.examplecrud.springbootcrud.service;

import com.examplecrud.springbootcrud.model.FaceIdResultResponse;
import com.examplecrud.springbootcrud.model.SdkTokenRequest;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.GetFaceIdResultIntlRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetFaceIdResultIntlResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class FaceIdResultService {

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    private Credential createCredential() {
        return new Credential(secretId, secretKey);
    }

    public FaceIdResultResponse getFaceIdResult(SdkTokenRequest sdkTokenRequest) {
        try {
            validateSdkTokenRequest(sdkTokenRequest);

            log.info("Fetching Face ID result for SDK token: {}", sdkTokenRequest.getSdkToken());
            Credential cred = createCredential();
            FaceidClient client = new FaceidClient(cred, "ap-jakarta");

            GetFaceIdResultIntlRequest req = createFaceIdResultRequest(sdkTokenRequest);
            GetFaceIdResultIntlResponse resp = client.GetFaceIdResultIntl(req);

            FaceIdResultResponse response = mapResponse(resp);

            log.info("Successfully fetched Face ID result. Request ID: {}", resp.getRequestId());
            return response;
        } catch (TencentCloudSDKException e) {
            log.error("Failed to fetch Face ID result: {}", e.getMessage(), e);
            throw new RuntimeException("Error while fetching Face ID result", e);
        }
    }

    private void validateSdkTokenRequest(SdkTokenRequest sdkTokenRequest) {
        if (sdkTokenRequest == null || !StringUtils.hasText(sdkTokenRequest.getSdkToken())) {
            throw new IllegalArgumentException("Invalid SDK token request. SDK token must not be null or empty.");
        }
    }

    private GetFaceIdResultIntlRequest createFaceIdResultRequest(SdkTokenRequest sdkTokenRequest) {
        GetFaceIdResultIntlRequest req = new GetFaceIdResultIntlRequest();
        req.setSdkToken(sdkTokenRequest.getSdkToken());
        return req;
    }

    private FaceIdResultResponse mapResponse(GetFaceIdResultIntlResponse resp) {
        FaceIdResultResponse response = new FaceIdResultResponse();

        response.setRequestId(resp.getRequestId());
        response.setDescription(resp.getDescription());
        response.setResult(resp.getResult());
        response.setSimilarity(resp.getSimilarity());
        response.setBestFrame(resp.getBestFrame());
        response.setExtra(resp.getExtra());
        response.setVideo(resp.getVideo());

        return response;
    }
}
