package com.example.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name_editText;
    EditText id_editText;
    Button add_button;
    ListView listView;
    ArrayList<String> dataSource;
    ArrayAdapter<String> arrayAdapter;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_editText = (EditText) findViewById(R.id.name_editText);
        id_editText = (EditText) findViewById(R.id.id_editText);
        add_button = (Button) findViewById(R.id.add_button);
        listView = (ListView) findViewById(R.id.listView);
        ButtonHandler handler = new ButtonHandler();
        add_button.setOnClickListener(handler);

        dataSource = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item,dataSource);
        listView.setAdapter(arrayAdapter);
        OnItemClickHandler itemClickListener = new OnItemClickHandler();
        listView.setOnItemClickListener(itemClickListener);

        builder = new AlertDialog.Builder(MainActivity.this);
    }

    private class ButtonHandler implements View.OnClickListener{
        public void onClick(View view){

                builder.setTitle("Confirm Message");
                builder.setMessage("are you sure to add a person ?");

                DelButtonHandler Dehandler = new DelButtonHandler();
                builder.setPositiveButton("ok", Dehandler);
                builder.setNegativeButton("cancel", null);
                builder.show();


        }
        private class DelButtonHandler implements DialogInterface.OnClickListener {
            public void onClick(DialogInterface dialog,int which){
                if(name_editText.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this , "姓名欄不能空白", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (id_editText.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this , "學號欄不能空白", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    String name = name_editText.getText().toString();
                    String number = id_editText.getText().toString();
                    dataSource.add("姓名: " + name + "  學號: " + number);
                    arrayAdapter.notifyDataSetChanged();
                }

            }
        }
    }
    public class OnItemClickHandler implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            builder.setTitle("Confirm Message");
            builder.setMessage("are you sure to del a person ?");

            ButtonHandler.DelButtonHandler Dehandler = new ButtonHandler().new DelButtonHandler() {
                public void onClick(DialogInterface dialog, int which) {
                    dataSource.remove((int)id);
                    arrayAdapter.notifyDataSetChanged();
                }
            };
            builder.setPositiveButton("ok",Dehandler);
            builder.setNegativeButton("cancel",null);
            builder.show();
        }
    }
}


