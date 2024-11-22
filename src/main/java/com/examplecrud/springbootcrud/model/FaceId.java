package com.examplecrud.springbootcrud.model;

import lombok.Data;

@Data
public class FaceId {
    private Response response;

    // Getter dan Setter untuk Response
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    // Inner class untuk Response yang lebih spesifik
    public static class Response {
        private String bestFrame;
        private String description;
        private String extra;
        private String requestId;
        private String result;
        private int similarity;
        private String video;

        // Getter dan Setter untuk properti Response
        public String getBestFrame() {
            return bestFrame;
        }

        public void setBestFrame(String bestFrame) {
            this.bestFrame = bestFrame;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public int getSimilarity() {
            return similarity;
        }

        public void setSimilarity(int similarity) {
            this.similarity = similarity;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }
    }
}
