package tdc.edu.vn.doan.Model;

public class Tinh {
    private  int id;
    private String maTinh;
    private String tenTinh;

    public Tinh() {
    }

    public Tinh(int id, String maTinh, String tenTinh) {
        this.id = id;
        this.maTinh = maTinh;
        this.tenTinh = tenTinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
}
