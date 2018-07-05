package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends AppCompatActivity {

    EditText etCompose;
    Button btSubmit;

    private TwitterClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        client = TwitterApplication.getRestClient(this);
        etCompose = (EditText) findViewById(R.id.etCompose);
        btSubmit = (Button) findViewById(R.id.btSubmit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etCompose.getText().toString();
                JsonHttpResponseHandler handler = new JsonHttpResponseHandler();
                client.sendTweet(message, handler);
                
            }
        });
    }




}
