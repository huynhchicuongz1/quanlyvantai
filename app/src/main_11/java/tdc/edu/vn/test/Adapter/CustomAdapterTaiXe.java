package tdc.edu.vn.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.test.GiaoDien.MainActivityDetailsTaiXe;
import tdc.edu.vn.test.GiaoDien.MainActivity_TaiXe;
import tdc.edu.vn.test.Model.TaiXe;
import tdc.edu.vn.test.R;

public class CustomAdapterTaiXe extends ArrayAdapter {

    private Context context;
    private int resource;
    private ArrayList<TaiXe> arrTaiXe = new ArrayList<>();
    public CustomAdapterTaiXe(Context context, int resource, ArrayList<TaiXe> arrTaiXe) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.arrTaiXe = arrTaiXe;
    }

    @Override
    public int getCount() {
        return arrTaiXe.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        TextView tvMa = convertView.findViewById(R.id.tvMaTX);
        TextView tvTenTX = convertView.findViewById(R.id.tvTenTX);
        ImageView img =  convertView.findViewById(R.id.iv_icon);

        final TaiXe taiXe = arrTaiXe.get(position);
        tvMa.setText(taiXe.getMaTX().toString());
        tvTenTX.setText(taiXe.getTenTX().toString());
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivityDetailsTaiXe.class);
                Bundle bundle = new Bundle();
                bundle.putString("maTX",taiXe.getMaTX().toString());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
