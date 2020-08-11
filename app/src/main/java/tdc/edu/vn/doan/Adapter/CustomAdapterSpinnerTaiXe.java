package tdc.edu.vn.doan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.doan.Database.DataXe;
import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.R;

public class CustomAdapterSpinnerTaiXe extends BaseAdapter {
    Context context;
    ArrayList<TaiXe> list;
    LayoutInflater inflter;

    public CustomAdapterSpinnerTaiXe(Context applicationContext, ArrayList<TaiXe> list) {
        this.context = applicationContext;
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_tai_xe, null);
        TextView info_maTX = (TextView) view.findViewById(R.id.info_maTX);
        TextView info_tenTX = (TextView) view.findViewById(R.id.info_tenTX);
        info_maTX.setText("Mã:" + list.get(i).getMaTX());
        info_tenTX.setText("Tên:" + list.get(i).getTenTX());
        return view;
    }
}
