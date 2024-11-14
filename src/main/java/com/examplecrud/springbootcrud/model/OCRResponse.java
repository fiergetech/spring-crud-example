package com.examplecrud.springbootcrud.model;

public class OCRResponse {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public static class Response {
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

        // Getter and Setter for all fields
        public String getAgama() {
            return agama;
        }

        public void setAgama(String agama) {
            this.agama = agama;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getBerlakuHingga() {
            return berlakuHingga;
        }

        public void setBerlakuHingga(String berlakuHingga) {
            this.berlakuHingga = berlakuHingga;
        }

        public String getGolDarah() {
            return golDarah;
        }

        public void setGolDarah(String golDarah) {
            this.golDarah = golDarah;
        }

        public String getIssuedDate() {
            return issuedDate;
        }

        public void setIssuedDate(String issuedDate) {
            this.issuedDate = issuedDate;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }

        public void setJenisKelamin(String jenisKelamin) {
            this.jenisKelamin = jenisKelamin;
        }

        public String getKecamatan() {
            return kecamatan;
        }

        public void setKecamatan(String kecamatan) {
            this.kecamatan = kecamatan;
        }

        public String getKelDesa() {
            return kelDesa;
        }

        public void setKelDesa(String kelDesa) {
            this.kelDesa = kelDesa;
        }

        public String getKewargaNegaraan() {
            return kewargaNegaraan;
        }

        public void setKewargaNegaraan(String kewargaNegaraan) {
            this.kewargaNegaraan = kewargaNegaraan;
        }

        public String getKota() {
            return kota;
        }

        public void setKota(String kota) {
            this.kota = kota;
        }

        public String getNIK() {
            return nik;
        }

        public void setNIK(String nik) {
            this.nik = nik;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getPerkerjaan() {
            return perkerjaan;
        }

        public void setPerkerjaan(String perkerjaan) {
            this.perkerjaan = perkerjaan;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getProvinsi() {
            return provinsi;
        }

        public void setProvinsi(String provinsi) {
            this.provinsi = provinsi;
        }

        public String getRTRW() {
            return rtRw;
        }

        public void setRTRW(String rtRw) {
            this.rtRw = rtRw;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getStatusPerkawinan() {
            return statusPerkawinan;
        }

        public void setStatusPerkawinan(String statusPerkawinan) {
            this.statusPerkawinan = statusPerkawinan;
        }

        public String getTempatTglLahir() {
            return tempatTglLahir;
        }

        public void setTempatTglLahir(String tempatTglLahir) {
            this.tempatTglLahir = tempatTglLahir;
        }

        public Long[] getWarnCardInfos() {
            return warnCardInfos;
        }

        public void setWarnCardInfos(Long[] warnCardInfos) {
            this.warnCardInfos = warnCardInfos;
        }
    }
}
