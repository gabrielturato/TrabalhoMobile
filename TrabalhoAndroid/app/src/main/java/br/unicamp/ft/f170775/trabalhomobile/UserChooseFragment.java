package br.unicamp.ft.f170775.trabalhomobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class UserChooseFragment extends AppCompatActivity{

    private String name;
    private String endereco;
    private int image;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_choose);


        Intent in = getIntent();
        endereco = in.getStringExtra("endereco");
        name = in.getStringExtra(name);
        image = in.getIntExtra("image", 0);
        ImageView imageView = (ImageView) findViewById(R.id.imageLocal);
        TextView nomeLocal = (TextView) findViewById(R.id.nomeLocal);
        TextView enderecoLocal = (TextView) findViewById(R.id.enderecoLocal);
        nomeLocal.setText(name);
        enderecoLocal.setText(endereco);
        imageView.setImageResource(image);

    }

}
