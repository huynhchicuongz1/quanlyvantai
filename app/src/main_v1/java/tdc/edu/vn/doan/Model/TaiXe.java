package tdc.edu.vn.doan.Model;

import java.util.Arrays;

public class TaiXe {
    private  int id;
    private String maTX;
    private String tenTX;
    private String ngaySinh;
    private String diaChi;
    private byte[] imgSignature;

    public TaiXe(int id, String maTX, String tenTX, String ngaySinh, String diaChi, byte[] imgSignature) {
        this.id = id;
        this.maTX = maTX;
        this.tenTX = tenTX;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.imgSignature = imgSignature;
    }

    public byte[] getImgSignature() {
        return imgSignature;
    }

    public void setImgSignature(byte[] imgSignature) {
        this.imgSignature = imgSignature;
    }

    public TaiXe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTX() {
        return maTX;
    }

    public void setMaTX(String maTX) {
        this.maTX = maTX;
    }

    public String getTenTX() {
        return tenTX;
    }

    public void setTenTX(String tenTX) {
        this.tenTX = tenTX;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "TaiXe{" +
                "id=" + id +
                ", maTX='" + maTX + '\'' +
                ", tenTX='" + tenTX + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", imgSignature=" + Arrays.toString(imgSignature) +
                '}';
    }
}
