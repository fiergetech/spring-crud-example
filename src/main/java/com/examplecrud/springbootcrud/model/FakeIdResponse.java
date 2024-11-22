package com.examplecrud.springbootcrud.model;

import lombok.Data;

import java.util.List;

@Data
public class FakeIdResponse {
    private String responseCode;
    private String responseMessage;
    private DataContent data;

    @Data
    public static class DataContent {
        private List<AttackRiskDetail> attackRiskDetailList;
        private String attackRiskLevel;
        private String requestId;
    }

    @Data
    public static class AttackRiskDetail {
        private String type;
    }
}

