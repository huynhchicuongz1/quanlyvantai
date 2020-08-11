package tdc.edu.vn.doan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.doan.Database.DataTaiXe;
import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.Model.Xe;
import tdc.edu.vn.doan.R;


public class CustomAdapterXe extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Xe> arrXe = new ArrayList<>();
    private ArrayList<TaiXe> arrTX = new ArrayList<>();
    public CustomAdapterXe(Context context, int resource, ArrayList<Xe> arrXe ,ArrayList<TaiXe> arrTX ) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.arrXe = arrXe;
        this.arrTX = arrTX;
    }

    @Override
    public int getCount() {
        return arrXe.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView tvMa = convertView.findViewById(R.id.edit_maXe);
        TextView tvTen = convertView.findViewById(R.id.edit_tenXe);
        TextView tvnam = convertView.findViewById(R.id.edit_namSX);
        TextView tvtenTX = convertView.findViewById(R.id.edit_tenTaiXe);

        final Xe xe = arrXe.get(position);
        tvMa.setText("Mã Xe : "+xe.getMaXe().toString());
        tvTen.setText("Tên Xe : "+xe.getTenXe().toString());
        tvnam.setText("Năm Sản Xuất Xe: "+xe.getNamSX().toString());
        for(int i = 0; i < arrTX.size(); i++){
            if(arrTX.get(i).getId() == xe.getIdTaiXe()){
                tvtenTX.setText("Tên Tài Xế: "+ arrTX.get(i).getTenTX());
                break;
            }
        }
        return convertView;
    }
}
