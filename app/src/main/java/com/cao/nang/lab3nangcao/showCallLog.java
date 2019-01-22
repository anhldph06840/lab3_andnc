package com.cao.nang.lab3nangcao;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class showCallLog extends AppCompatActivity {
    private ListView listViewCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_call_log);
        listViewCall = (ListView) findViewById(R.id.lvCallLog);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(showCallLog.this,
                    new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CALL_LOG},
                    999);
        } else
            showCalllog();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 999) {
            if (ContextCompat.checkSelfPermission(showCallLog.this, Manifest.permission.READ_CALL_LOG)
                    == PackageManager.PERMISSION_GRANTED) {
                showCalllog();
            } else {
                Toast.makeText(showCallLog.this, "Chua duoc cap quyen", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void showCalllog() {


        Uri uri = Uri.parse("content://call_log/calls");
        Cursor cursor = getContentResolver().query(uri,
                null, null, null, null);
        List<String> arraycalllog = new ArrayList<>();
        String calllog = "";
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                // di chuyen den vi tri dau tien o con tro cursor
                cursor.moveToFirst();

                // kiem tra dieu kien khi vong while doc het con tro
                while (cursor.isAfterLast() == false) {

                    // lay sdt
                    String num= cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));

                    // lay name
                    String name= cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                    // noi chuoi id va name lay duoc vao bien contact
                    String duration =cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));// for duration
                    calllog = calllog + "\n \n " + num +"  "+name+" "+duration;

                    // di chuyen toi vi tri tiep theo trong con tro cursor
                    cursor.moveToNext();
                }
                cursor.close();
                arraycalllog.add(calllog);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                        R.layout.custom_view,R.id.tvItem, arraycalllog);
                listViewCall.setAdapter(adapter);

                Toast.makeText(this,
                        calllog, Toast.LENGTH_LONG).show();


            } else {
                // Thong bao ko co danh ba
                Toast.makeText(this,
                        "Khong co lic su cuoc goi", Toast.LENGTH_SHORT).show();
            }

        } else {
            //Thong bao la ko truy cap dc danh ba hoac co loi
            Toast.makeText(this,
                    "Khong truy cap duoc lich su!", Toast.LENGTH_SHORT).show();

        }
        }

}
