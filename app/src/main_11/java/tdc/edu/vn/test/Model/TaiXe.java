package tdc.edu.vn.test.Model;

public class TaiXe {
    private String maTX,tenTX,ngaySinh,diaChi;

    public TaiXe(String maTX, String tenTX, String ngaySinh, String diaChi) {
        this.maTX = maTX;
        this.tenTX = tenTX;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }
    public TaiXe() {
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
}
