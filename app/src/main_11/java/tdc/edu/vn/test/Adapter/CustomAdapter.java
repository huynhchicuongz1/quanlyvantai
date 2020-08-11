package tdc.edu.vn.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.test.GiaoDien.MainActivity;
import tdc.edu.vn.test.Model.PhanCong;
import tdc.edu.vn.test.R;

public class CustomAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private ArrayList<PhanCong> arrPhanCong = new ArrayList<>();

    public CustomAdapter(Context context, int resource, ArrayList<PhanCong> arrPhanCong) {
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

        TextView tvSoPhieu = convertView.findViewById(R.id.tv_soPhieu);
        TextView tvNgayTao = convertView.findViewById(R.id.tv_ngayTao);
        TextView tvNgayXuatPhat = convertView.findViewById(R.id.tv_ngayXuatPhat);
        TextView tvXe = convertView.findViewById(R.id.tv_xe);
        TextView tvTuyen = convertView.findViewById(R.id.tv_tuyen);
        ImageView iv_icon = convertView.findViewById(R.id.iv_icon);

        PhanCong xt = arrPhanCong.get(position);
        tvSoPhieu.setText(xt.getSoPhieu().toString());
        tvNgayTao.setText(xt.getNgayXuatPhieu().toString());
        tvNgayXuatPhat.setText(xt.getNgayXuatPhat().toString());
        tvXe.setText(xt.getXe().toString());
        tvTuyen.setText(xt.getTuyen().toString());

        if (xt.getTuyen().toString().equals(MainActivity.TUYEN_1)) {
            iv_icon.setImageResource(R.drawable.tuyen1);
        }
        if (xt.getTuyen().toString().equals(MainActivity.TUYEN_2)) {
            iv_icon.setImageResource(R.drawable.tuyen2);
        }
        if (xt.getTuyen().toString().equals(MainActivity.TUYEN_3)) {
            iv_icon.setImageResource(R.drawable.tuyen3);
        }
        return convertView;
    }
}
