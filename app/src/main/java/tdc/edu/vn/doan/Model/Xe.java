package tdc.edu.vn.doan.Model;

public class Xe {
    private  int id;
    private String maXe;
    private String tenXe;
    private String tenTX;
    private String namSX;
    private int idTaiXe;

    public Xe() {
    }
    public Xe(int id, String maXe, String tenXe, String namSX, int idTaiXe) {
        this.id = id;
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.namSX = namSX;
        this.idTaiXe = idTaiXe;
    }

    public Xe(int id, String maXe, String tenXe, String tenTX, String namSX, int idTaiXe) {
        this.id = id;
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.tenTX = tenTX;
        this.namSX = namSX;
        this.idTaiXe = idTaiXe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getTenTX() {
        return tenTX;
    }

    public void setTenTX(String tenTX) {
        this.tenTX = tenTX;
    }

    public String getNamSX() {
        return namSX;
    }

    public void setNamSX(String namSX) {
        this.namSX = namSX;
    }

    public int getIdTaiXe() {
        return idTaiXe;
    }

    public void setIdTaiXe(int idTaiXe) {
        this.idTaiXe = idTaiXe;
    }
}
