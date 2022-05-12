package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import com.example.lostandfound.Data.DatabaseHelper;
import com.example.lostandfound.Model.Item;

public class NewAdvertActivity extends AppCompatActivity {
    EditText na_Name,na_Phone,na_desc,na_Date,na_Location;
    Button na_Save;
    CheckBox na_Lost,na_Found;
    DatabaseHelper databaseHelper;
    String checkedStatus = "",item_name,item_phone,item_desc,item_date,item_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);
        na_Name = findViewById(R.id.na_Name);
        na_Phone = findViewById(R.id.na_Phone);
        na_desc = findViewById(R.id.na_Desc);
        na_Date = findViewById(R.id.na_Date);
        na_Location = findViewById(R.id.na_Location);
        na_Save = findViewById(R.id.na_Save);
        na_Lost = findViewById(R.id.na_Lost);
        na_Found = findViewById(R.id.na_Found);
        databaseHelper = new DatabaseHelper(this);
        na_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name = na_Name.getText().toString();
                item_location = na_Location.getText().toString();
                item_date = na_Date.getText().toString();
                item_desc = na_desc.getText().toString();
                item_phone = na_Phone.getText().toString();
                Item i = new Item();
                i.setDate(item_date);
                i.setDescription(item_desc);
                i.setLocation(item_location);
                i.setName(item_name);
                i.setPhone(item_phone);
                i.setLost(checkedStatus);
                databaseHelper.insertItem(i);
                finish();
            }
        });

    }
    public void onCheckBoxClicked(View v){
        Boolean checked =((CheckBox) v).isChecked();

        switch(v.getId()) {
            case R.id.na_Lost:
                if (checked){
                    checkedStatus="lost";
                    ((CheckBox)findViewById(R.id.na_Found)).setChecked(false);}
                break;
            case R.id.na_Found:
                if (checked){
                    checkedStatus="found";
                    ((CheckBox)findViewById(R.id.na_Lost)).setChecked(false);}
                break;
        }

    }
}