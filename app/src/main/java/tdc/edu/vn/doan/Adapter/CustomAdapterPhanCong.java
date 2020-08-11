package tdc.edu.vn.doan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.PhanCong;
import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.Model.Tuyen;
import tdc.edu.vn.doan.Model.Xe;
import tdc.edu.vn.doan.R;


public class CustomAdapterPhanCong extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<PhanCong> arrPhanCong = new ArrayList<>();
    private ArrayList<Xe> arrXe = new ArrayList<>();
    private ArrayList<Tuyen> arrTuyen = new ArrayList<>();
    private ArrayList<Tinh> arrTinh = new ArrayList<>();
    public CustomAdapterPhanCong(Context context, int resource, ArrayList<PhanCong> arrPhanCong,ArrayList<Xe> arrXe ,ArrayList<Tuyen> arrTuyen , ArrayList<Tinh> arrTinh) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.arrPhanCong = arrPhanCong;
        this.arrXe = arrXe;
        this.arrTuyen = arrTuyen;
        this.arrTinh = arrTinh;
    }
    public CustomAdapterPhanCong(Context context, int resource, ArrayList<PhanCong> arrPhanCong) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.arrPhanCong = arrPhanCong;
    }

    @Override
    public int getCount() {
        return arrPhanCong.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView sophieu = convertView.findViewById(R.id.edit_soPhieu);
        TextView tvTen = convertView.findViewById(R.id.edit_ngayXP);
        TextView tvTenXe = convertView.findViewById(R.id.edit_tenXe);
        TextView tvTenTuyen = convertView.findViewById(R.id.edit_tenTuyen);
        TextView edit_tenTinh = convertView.findViewById(R.id.edit_tenTinh);

        final PhanCong phanCong = arrPhanCong.get(position);
        sophieu.setText("Số phiếu : "+ phanCong.getSoPhieu());
        tvTen.setText("Ngày Xuất Phiếu : "+ phanCong.getNgayXuatPhieu());
        for(int i = 0; i < arrXe.size(); i++){
            if(arrXe.get(i).getId() == phanCong.getId_xe()){
                tvTenXe.setText("Tên Tài Xế: "+ arrXe.get(i).getTenXe());
                break;
            }
        }
        for(int i = 0; i < arrTuyen.size(); i++){
            if(arrTuyen.get(i).getId() == phanCong.getId_tuyen()){
                tvTenTuyen.setText("Tuyến: "+ arrTuyen.get(i).getTenTuyen());
                break;
            }
        }
        for(int i = 0; i < arrTinh.size(); i++){
            if(arrTinh.get(i).getMaTinh().equals(phanCong.getNoiXuatPhat())){
                edit_tenTinh.setText("Nơi Xuất Phát: "+ arrTinh.get(i).getTenTinh());
                break;
            }
        }
        return convertView;
    }
}
