package com.examplecrud.springbootcrud.model;

import lombok.Data;

@Data
public class FaceIdResultResponse {
    private String responseCode;
    private String responseMessage;
    private FaceIdResultResponse.DataContent data;
    @Data
    public static class DataContent {
        private String bestFrame;
        private String description;
        private String extra;
        private String requestId;
        private String result;
        private Float similarity;
        private String video;
    }

}
