package tdc.edu.vn.doan.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.R;


public class CustomAdapterTinh extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Tinh> arrTinh = new ArrayList<>();
    public CustomAdapterTinh(Context context, int resource, ArrayList<Tinh> arrTinh) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.arrTinh = arrTinh;
    }

    @Override
    public int getCount() {
        return arrTinh.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView tvMa = convertView.findViewById(R.id.edit_maTinh);
        TextView tvTen = convertView.findViewById(R.id.edit_tenTinh);

        final Tinh tinh = arrTinh.get(position);
        tvMa.setText("Mã Tỉnh : "+tinh.getMaTinh().toString());
        tvTen.setText("Tên Tỉnh : "+tinh.getTenTinh().toString());
        return convertView;
    }
}
