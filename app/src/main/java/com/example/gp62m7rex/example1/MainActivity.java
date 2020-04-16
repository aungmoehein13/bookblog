package com.example.gp62m7rex.example1;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends Activity
        implements AdapterView.OnItemSelectedListener{

    EditText txtUserName1;
    EditText textarea1;
    ImageView imagestore;
    ImageView imagebtn1;
    Button buttonsubmit1;
    TextView selection;
    private static final int gallerypick=1;
    String[] items= { "Enter book's type","Hay girl", "Omm pyaw", "OKi oki",
            "Chit lar", "A Chit tl", "Me Too",
            "Yayyyyyy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtUserName1 = (EditText) findViewById(R.id.txtUserName);
        buttonsubmit1 = (Button) findViewById(R.id.buttonsubmit);
        textarea1=(EditText) findViewById(R.id.textarea);
        imagebtn1=(ImageView)findViewById(R.id.imagebtn);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        selection= (TextView) findViewById(R.id.selection);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa= new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        //adding image from gallery
        imagebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,gallerypick);
            }
        });

        buttonsubmit1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userName = txtUserName1.getText().toString();
                String info = textarea1.getText().toString();
                String cata=selection.getText().toString();
                //to store img    Uri imagestore=imagebtn

            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==gallerypick && requestCode==RESULT_OK && data!=null)
        {

           try{
                Uri imageUri = data.getData();
               InputStream imageStream = getContentResolver().openInputStream(imageUri);
               Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
               imagebtn1.setImageBitmap(selectedImage);
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selection.setText(items[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");
    }
}
