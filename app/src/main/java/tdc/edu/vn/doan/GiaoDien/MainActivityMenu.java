package tdc.edu.vn.doan.GiaoDien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.doan.R;

public class MainActivityMenu extends AppCompatActivity {

//    ListView lvMenu;
//    TextView txtDangXuat;
//    ArrayList<String> toollist;
//    ArrayAdapter<String> toolAdapter;
//
////    String[] listItemMenu = {"XE", "TÀI XẾ", "PHÂN CÔNG", "TUYẾN", "TỈNH","TÌM KIẾM","THÔNG KÊ"};
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_menu);
//        setControl();
//
//        setEvent();
//    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_bar, menu);
//        getMenuInflater().inflate(R.menu.menu_option, menu);
//        MenuItem searchViewItem = menu.findItem(R.id.bar_search);
//        final SearchView searchView = (SearchView) searchViewItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchView.clearFocus();
//                if(toollist.contains(query)){
//                    toolAdapter.getFilter().filter(query);
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Không tìm thấy kết quả phù hợp", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                toolAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void setEvent() {
//        setDSMenu();
//        final ArrayAdapter<String> adapterMenu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, toollist);
//            lvMenu.setAdapter(adapterMenu);
//        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//
//                switch (position){
//                    case 0:
//                        intent.setClass(MainActivityMenu.this, MainActivityXeTai.class);
//                        startActivity(intent);
//                        break;
//                    case 1:
//                        intent.setClass(MainActivityMenu.this, ActivityTaiXe.class);
//                        startActivity(intent);
//                        break;
//                    case 2:
//                        intent.setClass(MainActivityMenu.this, MainActivityPhanCong.class);
//                        startActivity(intent);
//                        break;
//                    case 3:
//                        intent.setClass(MainActivityMenu.this, MainActivityTuyen.class);
//                        startActivity(intent);
//                        break;
//                    case 4:
//                        intent.setClass(MainActivityMenu.this, MainActivityTinh.class);
//                        startActivity(intent);
//                        break;
//                    case 5:
//                        intent.setClass(MainActivityMenu.this, MainActivityTimKiem.class);
//                        startActivity(intent);
//                        break;
//                    case 6:
//                        intent.setClass(MainActivityMenu.this, MainActivityThongKe.class);
//                        startActivity(intent);
//                        break;
//                }
//            }
//        });
//        // Nut dang xuat
//        txtDangXuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Bao cho nguoi dung biet ban co muon dang xuat khong ???
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityMenu.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
//                builder.setTitle("Bạn có muốn đăng xuất không ???");
//                builder.setMessage("Hảy lựa chọn");
//                builder.setIcon(android.R.drawable.ic_dialog_alert);
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent();
//                        intent.setClass(MainActivityMenu.this, MainActivityDangNhap.class);
//                        startActivity(intent);
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.show();
//            }
//        });
//
//    }
//    private void setDSMenu(){
//        toollist = new ArrayList<>();
//        toollist.add("XE");
//        toollist.add("TÀI XẾ");
//        toollist.add("PHÂN CÔNG");
//        toollist.add("TUYẾN");
//        toollist.add("TỈNH");
//
//        toolAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toollist);
//        lvMenu.setAdapter(toolAdapter);
//    }
//
//    private void setControl() {
//        lvMenu = (ListView) findViewById(R.id.lvMenu);
//        txtDangXuat = (TextView) findViewById(R.id.txtDangXuat);
//    }
    private TextView txtDangXuat;
    private ListView lvMenu;
    ArrayList<String> toollist;
    ArrayAdapter<String> toolAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setControl();
        setDSMenu();
        setEvent();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);
        getMenuInflater().inflate(R.menu.menu_option, menu);
        MenuItem searchViewItem = menu.findItem(R.id.bar_search);
        final SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                if(toollist.contains(query)){
                    toolAdapter.getFilter().filter(query);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Không tìm thấy kết quả phù hợp", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                toolAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.itemCaiDat:
                Toast.makeText(getApplicationContext(), "Cài đặt", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDSMenu(){
        toollist = new ArrayList<>();
        toollist.add("XE");
        toollist.add("TÀI XẾ");
        toollist.add("PHÂN CÔNG");
        toollist.add("TUYẾN");
        toollist.add("TỈNH");
//        toollist.add("TÌM KIẾM");
//        toollist.add("THÔNG KÊ");

        toolAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toollist);
        lvMenu.setAdapter(toolAdapter);
    }

    private void setEvent() {
        //final ArrayAdapter<String> adapterMenu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, toollist);
        lvMenu.setAdapter(toolAdapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();

                switch (position){
                    case 0:
                        intent.setClass(MainActivityMenu.this, MainActivityXe.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(MainActivityMenu.this, ActivityTaiXe.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(MainActivityMenu.this, MainActivityPhanCong.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(MainActivityMenu.this, MainActivityTuyen.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(MainActivityMenu.this, MainActivityTinh.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(MainActivityMenu.this, MainActivityTimKiem.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(MainActivityMenu.this, MainActivityThongKe.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        // Nut dang xuat
        txtDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bao cho nguoi dung biet ban co muon dang xuat khong ???
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityMenu.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có muốn đăng xuất không ???");
                builder.setMessage("Hảy lựa chọn");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivityMenu.this, MainActivityDangNhap.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }

    private void setControl() {
        lvMenu = (ListView) findViewById(R.id.lvMenu);
        txtDangXuat = (TextView) findViewById(R.id.txtDangXuat);
    }
}
