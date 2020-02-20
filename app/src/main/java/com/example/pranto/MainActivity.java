package com.example.pranto;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Camera;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   EditText s;
    Camera c;
    TextView t;
    Button b;
   // private CodeScanner mCodeScanner;
   String text;
//public CameraSource s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s =  findViewById(R.id.surfaceView);
        t = findViewById(R.id.focus_text);
        b = findViewById(R.id.fetch);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = s.getText().toString();

                Call<ResponseBody> call = RetrofitCl
                        .getInstance()
                        .getApi()
                        .check(text);

                Log.d("denug" , text);
                call.enqueue(new Callback<ResponseBody>() {
                    @SuppressLint("LogConditional")
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String resp = null;
                        try {

                            if (response.body() != null){
                                resp = response.body().string();
                                Log.d("debug" , resp);
                            }else{

                            }

                            try {
                                //JSONArray jsonArray = new JSONArray(resp);
                                JSONObject jsonObject = new JSONObject(resp);

                                JSONObject jsonObject1 = jsonObject.getJSONObject("ticket_validity");

                                if (jsonObject1.toString().equals("valid") == true) {
                                    Intent intent = new Intent(MainActivity.this, Success.class);
//                                    intent.putExtra("rfid", input_rfid);
//                                    intent.putExtra("mobile", input_mobile);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }else{

                                }
                                //JSONObject jsonObject2 = jsonObject1.getJSONObject("Status_Code");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }

                });
            }

        });




    }
}
