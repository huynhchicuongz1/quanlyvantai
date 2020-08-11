package tdc.edu.vn.doan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.Model.Tuyen;
import tdc.edu.vn.doan.R;


public class CustomAdapterTuyen extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Tuyen> arrTuyen = new ArrayList<>();
    public CustomAdapterTuyen(Context context, int resource, ArrayList<Tuyen> arrTuyen) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.arrTuyen = arrTuyen;
    }

    @Override
    public int getCount() {
        return arrTuyen.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView tvMa = convertView.findViewById(R.id.edit_maTuyen);
        TextView tvTen = convertView.findViewById(R.id.edit_tenTuyen);
        TextView tvGia = convertView.findViewById(R.id.edit_giaTuyen);

        final Tuyen tuyen = arrTuyen.get(position);
        tvMa.setText("Mã Tuyến : "+tuyen.getMaTuyen().toString());
        tvTen.setText("Tên Tuyến : "+tuyen.getTenTuyen().toString());
        tvGia.setText("Giá: "+tuyen.getGia());
        return convertView;
    }
}
