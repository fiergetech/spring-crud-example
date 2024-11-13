package com.examplecrud.springbootcrud.service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.GetFaceIdResultIntlRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetFaceIdResultIntlResponse;
import com.examplecrud.springbootcrud.model.FaceIdResultResponse;
import com.examplecrud.springbootcrud.model.SdkTokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FaceIdResultService {

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    public FaceIdResultResponse getFaceIdResult(SdkTokenRequest sdkTokenRequest) throws TencentCloudSDKException {
        // Menggunakan credential untuk autentikasi
        Credential cred = new Credential(secretId, secretKey);
        // Menggunakan FaceidClient dari Tencent Cloud SDK
        FaceidClient client = new FaceidClient(cred, "ap-jakarta");

        // Membuat permintaan untuk menggunakan sdkToken
        GetFaceIdResultIntlRequest req = new GetFaceIdResultIntlRequest();
        req.setSdkToken(sdkTokenRequest.getSdkToken());

        // Mengirimkan request dan mendapatkan response
        GetFaceIdResultIntlResponse resp = client.GetFaceIdResultIntl(req);

        // Menyusun response sesuai format yang diinginkan
        FaceIdResultResponse response = new FaceIdResultResponse();
        FaceIdResultResponse.Response responseContent = new FaceIdResultResponse.Response();
        
        responseContent.setRequestId(resp.getRequestId());
        responseContent.setDescription(resp.getDescription());
        responseContent.setResult(resp.getResult());
        responseContent.setSimilarity(resp.getSimilarity());
        responseContent.setBestFrame(resp.getBestFrame());
        responseContent.setExtra(resp.getExtra());
        responseContent.setVideo(resp.getVideo());
        
        response.setResponse(responseContent);

        return response;
    }
}
