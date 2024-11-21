package com.examplecrud.springbootcrud.model;

public class OCRRequest {
    private String imageBase64;
    private String imageUrl;
    private Boolean returnHeadImage;
    private String scene;

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getReturnHeadImage() {
        return returnHeadImage;
    }

    public void setReturnHeadImage(Boolean returnHeadImage) {
        this.returnHeadImage = returnHeadImage;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
