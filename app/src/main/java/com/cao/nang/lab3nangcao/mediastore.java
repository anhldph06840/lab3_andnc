package com.cao.nang.lab3nangcao;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.MediaStore;
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

public class mediastore extends AppCompatActivity {
    ListView listViewmedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediastore);
        listViewmedia = findViewById(R.id.lvMedia);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mediastore.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    999);
        } else
            showMedia();
        ;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 999) {
            if (ContextCompat.checkSelfPermission(mediastore.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                showMedia();
            } else {
                Toast.makeText(mediastore.this, "Chua duoc cap quyen", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void showMedia() {

        Uri uri = Uri.parse("content://media/external/audio/media");
        Cursor cursor = getContentResolver().query(uri,
                null, null, null, null);
        List<String> arrayMedia = new ArrayList<>();
        String media = "";
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                // di chuyen den vi tri dau tien o con tro cursor
                cursor.moveToFirst();

                // kiem tra dieu kien khi vong while doc het con tro
                while (cursor.isAfterLast() == false) {

                    // lay sdt
                    int id= cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));

                    // lay name
                    String name= cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME));
                    // noi chuoi id va name lay duoc vao bien contact
                   String type= cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE));
                    media = media + "\n \n " + (id-10) +".  "+name+"\n"+type;

                    // di chuyen toi vi tri tiep theo trong con tro cursor
                    cursor.moveToNext();
                }
                cursor.close();
                arrayMedia.add(media);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                        R.layout.custom_view,R.id.tvItem, arrayMedia);
                listViewmedia.setAdapter(adapter);

                Toast.makeText(this,
                        media, Toast.LENGTH_LONG).show();


            } else {
                // Thong bao ko co danh ba
                Toast.makeText(this,
                        "Khong  bai hat", Toast.LENGTH_SHORT).show();
            }


}}}