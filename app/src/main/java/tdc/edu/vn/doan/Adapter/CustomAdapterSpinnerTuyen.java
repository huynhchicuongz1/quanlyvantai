package tdc.edu.vn.doan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.Model.Tuyen;
import tdc.edu.vn.doan.R;

public class CustomAdapterSpinnerTuyen extends BaseAdapter {
    Context context;
    ArrayList<Tuyen> list;
    LayoutInflater inflter;

    public CustomAdapterSpinnerTuyen(Context applicationContext, ArrayList<Tuyen> list) {
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
        view = inflter.inflate(R.layout.spinner_list_tuyen, null);
        TextView info_tenTX = (TextView) view.findViewById(R.id.info_ten);
        info_tenTX.setText("" + list.get(i).getTenTuyen());
        return view;
    }
}
