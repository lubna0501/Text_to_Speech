package com.example.text_to_speech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Locale;

public class Speechtext extends AppCompatActivity {
EditText edit;
ImageView image;
static final Integer recordaudio=1;
private SpeechRecognizer speechRecognizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speechtext);
        bindview();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent speechintent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());

speechRecognizer.setRecognitionListener(new RecognitionListener() {
    @Override
    public void onReadyForSpeech(Bundle params) {
        edit.setText("");
        edit.setHint("listening...");
    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
image.setImageResource(R.drawable.mic2);
    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> data=results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String datatext = data.get(0);
        edit.setHint(data.get(0));

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
});
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    image.setImageResource(R.drawable.mic1);
                    speechRecognizer.stopListening();
                }
                if(event.getAction()== MotionEvent.ACTION_DOWN)
                {
                    image.setImageResource(R.drawable.mic2);
                    speechRecognizer.startListening(speechintent);
                }
                return false;
            }
        });
    }

    private void bindview() {
        edit=findViewById(R.id.speechtotext);
        image=findViewById(R.id.mic);
    }
}