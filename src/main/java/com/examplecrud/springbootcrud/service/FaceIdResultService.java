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
        FaceIdResultResponse response = new FaceIdResultResponse();
        try {
            try{
                validateSdkTokenRequest(sdkTokenRequest);
            }catch(Exception e){
                log.error("Failed to fetch Face ID result: {}", e.getMessage(), e);

                response.setResponseCode("500");
                response.setResponseMessage("Failed to fetch Face ID result: "+e.getMessage());
            }

            log.info("Fetching Face ID result for SDK token: {}", sdkTokenRequest.getSdkToken());
            Credential cred = createCredential();
            FaceidClient client = new FaceidClient(cred, "ap-jakarta");

            GetFaceIdResultIntlRequest req = createFaceIdResultRequest(sdkTokenRequest);
            GetFaceIdResultIntlResponse resp = client.GetFaceIdResultIntl(req);

            response = mapResponse(resp);

            log.info("Successfully fetched Face ID result. Request ID: {}", resp.getRequestId());
        } catch (TencentCloudSDKException e) {
            log.error("Failed to fetch Face ID result: {}", e.getMessage(), e);

            response.setResponseCode("500");
            response.setResponseMessage("Failed to fetch Face ID result: "+e.getMessage());
        }

        return response;
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
        FaceIdResultResponse.DataContent dataContent = new FaceIdResultResponse.DataContent();

        dataContent.setRequestId(resp.getRequestId());
        dataContent.setDescription(resp.getDescription());
        dataContent.setResult(resp.getResult());
        dataContent.setSimilarity(resp.getSimilarity());
        dataContent.setBestFrame(resp.getBestFrame());
        dataContent.setExtra(resp.getExtra());
        dataContent.setVideo(resp.getVideo());

        response.setResponseCode("200");
        response.setResponseMessage("Success");

        response.setData(dataContent);

        return response;
    }
}
