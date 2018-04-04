package br.unicamp.g172356ft.telainicial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Turato on 03/04/2018.
 */

public class MainActivity extends AppCompatActivity {

    private TextView boasvindas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boasvindas= (TextView) findViewById(R.id.boasvindas);
        Intent in = getIntent();

        if(in!=null){
            boasvindas.setText("Seja bem vindo "+in.getStringExtra("usuario")+" !");
        }
    }
}