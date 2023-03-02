package com.example.text_to_speech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.ULocale;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
EditText editText;
Button convertor,movetospeech;
TextToSpeech speech;
String[] permissions={Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO};
int request_code=121;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindview();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissions, request_code);
            }
        }
        speech=new TextToSpeech(this,this);

        movetospeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Speechtext.class));
            }
        });

        convertor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speechtext =editText.getText().toString().trim();
                speakoutthemethod(speechtext);
            }
        });

    }

    private void speakoutthemethod(String speechtext) {
        speech.speak(speechtext,TextToSpeech.QUEUE_FLUSH,null);
    }

    private void bindview() {
        editText=findViewById(R.id.textedit);
        convertor=findViewById(R.id.convertbtn);
        movetospeech=findViewById(R.id.movetextspeech);

        }

    @Override
    public void onInit(int status) {
        if(status== TextToSpeech.SUCCESS)
        {
            int result= speech.setLanguage(Locale.US);
            if(result==TextToSpeech.LANG_MISSING_DATA || result ==TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Toast.makeText(this, "language is not supported", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
