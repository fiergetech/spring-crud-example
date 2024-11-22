package com.examplecrud.springbootcrud.service;

import com.examplecrud.springbootcrud.model.FaceIdResultResponse;
import com.examplecrud.springbootcrud.model.FakeIdRequest;
import com.examplecrud.springbootcrud.model.FakeIdResponse;
import com.examplecrud.springbootcrud.model.SdkTokenRequest;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DetectFakeAiService {

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    private Credential createCredential() {
        return new Credential(secretId, secretKey);
    }

    public FakeIdResponse detectFakeAi(FakeIdRequest fakeIdRequest) {
        FakeIdResponse response = new FakeIdResponse();
        try {
            log.info("Detecting Fake ID...");
            Credential cred = createCredential();
            FaceidClient client = new FaceidClient(cred, "ap-singapore");

            DetectAIFakeFacesRequest req = createFaceIdResultRequest(fakeIdRequest);
            DetectAIFakeFacesResponse resp = client.DetectAIFakeFaces(req);

            response = mapResponse(resp);

            log.info("Successfully fetched Face ID result. Request ID: {}", resp.getRequestId());
        } catch (TencentCloudSDKException e) {
            log.error("Failed to fetch Face ID result: {}", e.getMessage(), e);

            response.setResponseCode("500");
            response.setResponseMessage("Failed to fetch Face ID result: " + e.getMessage());
        }

        return response;
    }

    private DetectAIFakeFacesRequest createFaceIdResultRequest(FakeIdRequest fakeIdRequest) {
        DetectAIFakeFacesRequest req = new DetectAIFakeFacesRequest();
        req.setFaceInputType(fakeIdRequest.getFaceInputType());
        req.setFaceInput(fakeIdRequest.getFaceInput());
        return req;
    }

    private FakeIdResponse mapResponse(DetectAIFakeFacesResponse resp) {
        FakeIdResponse response = new FakeIdResponse();
        FakeIdResponse.DataContent dataContent = new FakeIdResponse.DataContent();

        // Set simple fields
        dataContent.setRequestId(resp.getRequestId());
        dataContent.setAttackRiskLevel(resp.getAttackRiskLevel());

        // Map AttackRiskDetailList
        if (resp.getAttackRiskDetailList() != null) {
            List<FakeIdResponse.AttackRiskDetail> attList = Arrays.stream(resp.getAttackRiskDetailList())
                    .map(detail -> {
                        FakeIdResponse.AttackRiskDetail mappedDetail = new FakeIdResponse.AttackRiskDetail();
                        mappedDetail.setType(detail.getType()); // Map "Type" field
                        return mappedDetail;
                    })
                    .collect(Collectors.toList());
            dataContent.setAttackRiskDetailList(attList);
        } else {
            dataContent.setAttackRiskDetailList(Collections.emptyList()); // Default ke list kosong
        }


        // Set response metadata
        response.setResponseCode("200");
        response.setResponseMessage("Success");
        response.setData(dataContent);

        return response;
    }


}
