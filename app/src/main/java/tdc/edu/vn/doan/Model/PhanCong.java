package tdc.edu.vn.doan.Model;

public class PhanCong {
    private int soPhieu;
    private String ngayXuatPhieu;
    private String noiXuatPhat;
    private int id_tuyen;
    private int id_xe;

    public PhanCong() {
    }
    public PhanCong(String ngayXuatPhieu, String noiXuatPhat, int id_tuyen, int id_xe) {
        this.ngayXuatPhieu = ngayXuatPhieu;
        this.noiXuatPhat = noiXuatPhat;
        this.id_tuyen = id_tuyen;
        this.id_xe = id_xe;
    }

    public PhanCong(int soPhieu, String ngayXuatPhieu, String noiXuatPhat, int id_tuyen, int id_xe) {
        this.soPhieu = soPhieu;
        this.ngayXuatPhieu = ngayXuatPhieu;
        this.noiXuatPhat = noiXuatPhat;
        this.id_tuyen = id_tuyen;
        this.id_xe = id_xe;
    }

    public int getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(int soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getNgayXuatPhieu() {
        return ngayXuatPhieu;
    }

    public void setNgayXuatPhieu(String ngayXuatPhieu) {
        this.ngayXuatPhieu = ngayXuatPhieu;
    }

    public String getNoiXuatPhat() {
        return noiXuatPhat;
    }

    public void setNoiXuatPhat(String noiXuatPhat) {
        this.noiXuatPhat = noiXuatPhat;
    }

    public int getId_tuyen() {
        return id_tuyen;
    }

    public void setId_tuyen(int id_tuyen) {
        this.id_tuyen = id_tuyen;
    }

    public int getId_xe() {
        return id_xe;
    }

    public void setId_xe(int id_xe) {
        this.id_xe = id_xe;
    }
}
