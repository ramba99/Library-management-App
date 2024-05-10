package com.example.assignment7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class managePublisher extends AppCompatActivity {

    Button button;
    private DBManager dbManager;

    EditText editText1, editText2, editText3;
    Button addpublish;
    Button clearpub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_publisher);

        button = findViewById(R.id.btnback4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminMenu();
            }
        });

        dbManager= new DBManager(this);

        dbManager.open();

        addpublish = findViewById(R.id.btnaddpub);
        clearpub =  findViewById(R.id.btndeletepub);
        editText1 =  findViewById(R.id.txtPubName);
        editText2 =  findViewById(R.id.txtpublisheradd);
        editText3 = findViewById(R.id.txtpubcontact);

        addpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()||
                        editText3.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fields can't be null", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                if(valid) {

                    String Name = editText1.getText().toString();
                    String Address = editText2.getText().toString();
                    String Phone = editText3.getText().toString();


                    dbManager.insert("insert into Publisher values('" + Name + "','" + Address + "'," +
                            "'" + Phone + "')");
                    Toast.makeText(managePublisher.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                    Log.e("first","Inserted");
                    dbManager.close();
                }

                else
                {
                    Toast.makeText(managePublisher.this, "Error in Publisher Adding", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clearpub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText1.getText().clear();
                editText2.getText().clear();
                editText3.getText().clear();

                Toast.makeText(getApplicationContext(),
                        "Your Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void adminMenu(){
        Intent intent = new Intent(this, adminMenu.class);
        startActivity(intent);
    }
}
