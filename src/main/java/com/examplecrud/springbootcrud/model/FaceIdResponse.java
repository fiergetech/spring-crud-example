package com.examplecrud.springbootcrud.model;

import lombok.Data;

@Data
public class FaceIdResponse {
    private String responseCode;
    private String responseMessage;
    private FaceIdResponse.DataContent data;

    @Data
    public static class DataContent {
        private String sdkToken;
        private String requestId;
    }

}
