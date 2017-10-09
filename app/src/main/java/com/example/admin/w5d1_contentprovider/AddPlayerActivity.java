package com.example.admin.w5d1_contentprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayerActivity extends AppCompatActivity {
    EditText etFName, etAge, etPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        etFName = (EditText) findViewById(R.id.etFirst);
        etAge = (EditText) findViewById(R.id.etAge);
        etPos = (EditText) findViewById(R.id.etPos);
    }

    public void addNewPlayer(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());

        long saved = databaseHelper.addPlayer(etFName.getText().toString(), Integer.parseInt(etAge.getText().toString()),
                etPos.getText().toString());
        Toast.makeText(this, "DATA SAVED " + saved, Toast.LENGTH_SHORT).show();
    }
}
