package br.unicamp.ft.f170775.trabalhomobile;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private TextView totalAvaliacoes;
    private RatingBar mediaAvaliacoes;
    FragmentManager fragmentManager;

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
        totalAvaliacoes = (TextView) findViewById(R.id.totalAvaliacoes);
        totalAvaliacoes.setText(""+local.totalStars());
        mediaAvaliacoes = (RatingBar) findViewById(R.id.mediaAvaliacoes);
        mediaAvaliacoes.setRating((float) local.mediaStars());
        mediaAvaliacoes.setEnabled(false);
        nomeLocal.setText(local.getName());
        enderecoLocal.setText(local.getEndereco());
        imageView.setImageResource(image);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        shoppings = mFirebaseDatabaseReference.child("Shoppings");
        mercados = mFirebaseDatabaseReference.child("Mercados");
        restaurantes = mFirebaseDatabaseReference.child("Restaurantes");
        fragmentManager = getSupportFragmentManager();
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                avaliacao = (int) rating;
            }
        });

    }

    public void onClickAvaliar(View v){
        String nomeFirebase = "";
        switch(local.getName()){
            case "Subway":
                nomeFirebase = "Subway";
                verificaStarsRestaurantes(avaliacao, nomeFirebase);
                break;
            case "Jangada":
                nomeFirebase = "Jangada";
                verificaStarsRestaurantes(avaliacao, nomeFirebase);
                break;
            case "Maverick":
                nomeFirebase = "Maverick";
                verificaStarsRestaurantes(avaliacao, nomeFirebase);
                break;
            case "Mc'Donalds":
                nomeFirebase = "Subway";
                verificaStarsRestaurantes(avaliacao, nomeFirebase);
                break;
            case "Santa Rita":
                nomeFirebase = "Santarita";
                verificaStarsMercado(avaliacao, nomeFirebase);
                break;
            case "Enxuto":
                nomeFirebase = "Enxuto";
                verificaStarsMercado(avaliacao, nomeFirebase);
                break;
            case "Covabra":
                nomeFirebase = "Covabra";
                verificaStarsMercado(avaliacao, nomeFirebase);
                break;
            case "Pátio Limeira Shopping":
                nomeFirebase = "Patio";
                verificaStarShopping(avaliacao, nomeFirebase);
                break;
            case "Shopping Center Limeira":
                nomeFirebase = "Center";
                verificaStarShopping(avaliacao, nomeFirebase);
                break;
            case "Shopping Nações Limeira":
                nomeFirebase = "Nacoes";
                verificaStarShopping(avaliacao, nomeFirebase);
                break;
        }
        atualizaInfo(nomeFirebase);
    }

    public void atualizaInfo(String nomeFirebase){
        if(nomeFirebase == "Subway" || nomeFirebase == "Jangada" || nomeFirebase == "Maverick" || nomeFirebase == "Mcdonalds"){
            restaurantes.child(nomeFirebase).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    local = dataSnapshot.getValue(Locals.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });

        }
        totalAvaliacoes.setText(""+local.totalStars());
        mediaAvaliacoes.setText(""+local.mediaStars());
    }

    public void verificaStarShopping(int avaliacao, String nome){
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

    public void verificaStarsMercado(int avaliacao, String nome){
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(this , MapsActivity.class);
        startActivity(in);
    }

}
