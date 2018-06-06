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
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class UserChooseFragment extends AppCompatActivity implements RatingBar.OnClickListener, RatingBar.OnRatingBarChangeListener{

    private String name;
    private String endereco;
    private int image;
    private int avaliacao;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_choose);


        Intent in = getIntent();
        Locals local = (Locals) in.getSerializableExtra("local");
        image = local.getResId();
        ImageView imageView = (ImageView) findViewById(R.id.imageLocal);
        TextView nomeLocal = (TextView) findViewById(R.id.nomeLocal);
        TextView enderecoLocal = (TextView) findViewById(R.id.enderecoLocal);
        TextView totalAvaliacoes = (TextView) findViewById(R.id.totalAvaliacoes);
        totalAvaliacoes.setText(""+local.totalStars());
        TextView mediaAvaliacoes = (TextView) findViewById(R.id.mediaAvaliacoes);
        mediaAvaliacoes.setText(""+local.mediaStars());
        nomeLocal.setText(local.getName());
        enderecoLocal.setText(local.getEndereco());
        imageView.setImageResource(image);

    }

    @Override
    public void onClick(View v) {
        
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        avaliacao = (int) ratingBar.getRating();
    }
}
