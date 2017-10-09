package com.example.admin.w5d1_contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Uri uri = Uri.parse("content://com.example.admin.w5d1_contentprovider.contentprovider." +
                "DatabaseContentProvider/Players");

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("pName"));
            Log.d(TAG, "onCreate: " + name);
        }
    }

    public void addNewPlayer(View view) {

        Intent intent = new Intent(this, AddPlayerActivity.class);
        startActivity(intent);
    }
}
