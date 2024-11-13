package com.examplecrud.springbootcrud.service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.GetFaceIdTokenIntlRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetFaceIdTokenIntlResponse;
import com.examplecrud.springbootcrud.model.FaceIdResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FaceIdService {

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    public FaceIdResponse getFaceIdToken() throws TencentCloudSDKException {
        Credential cred = new Credential(secretId, secretKey);
        FaceidClient client = new FaceidClient(cred, "ap-jakarta");

        GetFaceIdTokenIntlRequest req = new GetFaceIdTokenIntlRequest();
        GetFaceIdTokenIntlResponse resp = client.GetFaceIdTokenIntl(req);

        FaceIdResponse faceIdResponse = new FaceIdResponse();
        faceIdResponse.setSdkToken(resp.getSdkToken());
        faceIdResponse.setRequestId(resp.getRequestId());

        return faceIdResponse;
    }
}
