package com.examplecrud.springbootcrud.model;

import lombok.Data;

@Data
public class FaceIdResultResponse {

    private String bestFrame;
    private String description;
    private String extra;
    private String requestId;
    private String result;
    private Float similarity;
    private String video;

}
