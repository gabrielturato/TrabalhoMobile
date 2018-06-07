package br.unicamp.ft.f170775.trabalhomobile;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UserChooseFragment extends AppCompatActivity{

    private String name;
    private String endereco;
    private int image;
    private int avaliacao;
    private Locals local;
    private DatabaseReference mFirebaseDatabaseReference;
    private RatingBar ratingBar;
    private DatabaseReference shoppings;
    private DatabaseReference mercados;
    private DatabaseReference restaurantes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_choose);


        Intent in = getIntent();
        local = (Locals) in.getSerializableExtra("local");
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
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        shoppings = mFirebaseDatabaseReference.child("Shoppings");
        mercados = mFirebaseDatabaseReference.child("Mercados");
        restaurantes = mFirebaseDatabaseReference.child("Restaurantes");
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                avaliacao = (int) rating;
            }
        });

    }


    public void onClickAvaliar(View v){
        switch(local.getName()){
            case "Subway":
                System.out.println("Entrou no avaliar Subway");
                verificaStarsRestaurantes(avaliacao, "Subway");
                break;
            case "Jangada":
                verificaStarsRestaurantes(avaliacao, "Jangada");
                break;
            case "Maverick":
                verificaStarsRestaurantes(avaliacao, "Maverick");
                break;
            case "Mc'Donalds":
                verificaStarsRestaurantes(avaliacao, "Mcdonalds");
                break;
            case "Santa Rita":
                break;
            case "Enxuto":
                break;
            case "Covabra":
                break;
            case "Pátio Limeira Shopping":
                break;
            case "Shopping Center Limeira":
                break;
            case "Shopping Nações Limeira":
                break;
        }
    }

    public void verificaStarsRestaurantes(int avaliacao, String nome){
        Map<String, Object> hopperUpdates = new HashMap<>();
        switch (avaliacao){
            case 1:
                hopperUpdates.put("oneStar", local.getOneStar()+1);
                restaurantes.child(nome).updateChildren(hopperUpdates);
                break;
            case 2:
                hopperUpdates.put("twoStar", local.getTwoStar()+1);
                restaurantes.child(nome).updateChildren(hopperUpdates);
                break;
            case 3:
                hopperUpdates.put("threeStar", local.getThreeStar()+1);
                restaurantes.child(nome).updateChildren(hopperUpdates);
                break;
            case 4:
                hopperUpdates.put("fourStar", local.getFourStar()+1);
                restaurantes.child(nome).updateChildren(hopperUpdates);
                break;
            case 5:
                hopperUpdates.put("fiveStar", local.getFiveStar()+1);
                restaurantes.child(nome).updateChildren(hopperUpdates);
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
