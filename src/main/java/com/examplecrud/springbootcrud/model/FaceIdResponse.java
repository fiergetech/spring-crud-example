package com.examplecrud.springbootcrud.model;

public class FaceIdResponse {
    private String sdkToken;
    private String requestId;

    // Getter dan Setter
    public String getSdkToken() {
        return sdkToken;
    }

    public void setSdkToken(String sdkToken) {
        this.sdkToken = sdkToken;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
