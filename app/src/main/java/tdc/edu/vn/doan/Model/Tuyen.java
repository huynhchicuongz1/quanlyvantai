package tdc.edu.vn.doan.Model;

public class Tuyen {
    private  int id;
    private String maTuyen;
    private String tenTuyen;
    private  int gia;

    public Tuyen(int id, String maTuyen, String tenTuyen, int gia) {
        this.id = id;
        this.maTuyen = maTuyen;
        this.tenTuyen = tenTuyen;
        this.gia = gia;
    }

    public Tuyen() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
    }

    public String getTenTuyen() {
        return tenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        this.tenTuyen = tenTuyen;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}

