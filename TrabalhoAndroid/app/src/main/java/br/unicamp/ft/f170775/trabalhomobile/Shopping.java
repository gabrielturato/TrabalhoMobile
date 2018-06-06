package br.unicamp.ft.f170775.trabalhomobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Shopping extends Fragment
        implements MyAdapter.OnItemClickListener {

    private View lView;
    public Locals locals;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private Button button;
    private DatabaseReference mFirebaseDatabaseReference;
    private ArrayList<Locals> local = new ArrayList<>();

    private void setValorImagem(){
        for(Locals L:local){
            switch (L.getName()){
                case "Shopping Center Limeira":
                    local.get(0).setResId(R.drawable.shoppingcenter);
                    break;
                case "Shopping Nações Limeira":
                    local.get(1).setResId(R.drawable.shoppingnacoes);
                    break;
                case "Pátio Limeira Shopping":
                    local.get(2).setResId(R.drawable.shoppingpatio);
                    break;
            }
        }
    }

    public void passarArrayParaAdapter(ArrayList<Locals> locais){
        setValorImagem();
        mAdapter = new MyAdapter(locais, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(Locals locals) {
        this.locals = locals;
        Intent in = new Intent(getContext(), UserChooseFragment.class);
        in.putExtra("local", locals);
        startActivity(in);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {

        lView = inflater.inflate(R.layout.content_restaurant_drawer, container, false);
        mRecyclerView = (RecyclerView) lView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot remoteLocals = dataSnapshot.child("Shoppings");
                for (DataSnapshot remoteLocal: remoteLocals.getChildren()) {
                    local.add(remoteLocal.getValue(Locals.class));
                }
                passarArrayParaAdapter(local);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG,"Failed to read value.", error.toException());
            }
        });
        //local.add(new Locals("Shopping Pátio Limeira", R.drawable.shoppingpatio, "R. Carlos Gomes, 1321 - Centro, Limeira "));
        //local.add(new Locals("Shopping Naçôes", R.drawable.shoppingnacoes, "Rod. Dep. Laércio Côrte, 4500 - Jardim Res. Graminha III, Limeira"));

        lView.findViewById(R.id.buttonRestaurante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MapsActivity.class);
                i.putExtra("endereco", locals.endereco);
                startActivity(i);
            }
        });
        return lView;
    }

}
