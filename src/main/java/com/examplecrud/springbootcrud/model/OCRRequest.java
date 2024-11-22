package com.examplecrud.springbootcrud.model;

import lombok.Data;

@Data
public class OCRRequest {
    private String imageBase64;
    private String imageUrl;
    private Boolean returnHeadImage;
    private String scene;

    public void setScene(String scene) {
        this.scene = scene != null ? scene.trim() : null;
    }
}
