package com.examplecrud.springbootcrud.model;

import lombok.Data;

@Data
public class OCRResponse {

    private String responseCode;
    private String responseMessage;
    private DataContent data;

    @Data
    public static class DataContent {
        private String agama;
        private String alamat;
        private String berlakuHingga;
        private String golDarah;
        private String issuedDate;
        private String jenisKelamin;
        private String kecamatan;
        private String kelDesa;
        private String kewargaNegaraan;
        private String kota;
        private String nik;
        private String nama;
        private String perkerjaan;
        private String photo;
        private String provinsi;
        private String rtRw;
        private String requestId;
        private String statusPerkawinan;
        private String tempatTglLahir;
        private Long[] warnCardInfos;
    }
}

