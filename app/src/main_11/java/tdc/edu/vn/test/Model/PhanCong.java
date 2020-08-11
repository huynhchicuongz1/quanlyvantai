package tdc.edu.vn.test.Model;

public class PhanCong {
    private String soPhieu;
    private String ngayXuatPhieu;
    private String ngayXuatPhat;
    private String tuyen;
    private String xe;

    public PhanCong() {
    }

    public PhanCong(String soPhieu, String ngayXuatPhieu, String ngayXuatPhat, String tuyen, String xe) {
        this.soPhieu = soPhieu;
        this.ngayXuatPhieu = ngayXuatPhieu;
        this.ngayXuatPhat = ngayXuatPhat;
        this.tuyen = tuyen;
        this.xe = xe;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getNgayXuatPhieu() {
        return ngayXuatPhieu;
    }

    public void setNgayXuatPhieu(String ngayXuatPhieu) {
        this.ngayXuatPhieu = ngayXuatPhieu;
    }

    public String getNgayXuatPhat() {
        return ngayXuatPhat;
    }

    public void setNgayXuatPhat(String ngayXuatPhat) {
        this.ngayXuatPhat = ngayXuatPhat;
    }

    public String getTuyen() {
        return tuyen;
    }

    public void setTuyen(String tuyen) {
        this.tuyen = tuyen;
    }

    public String getXe() {
        return xe;
    }

    public void setXe(String xe) {
        this.xe = xe;
    }
}
