package br.unicamp.ft.f170775.trabalhomobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RestaurantDrawer extends Fragment
        implements MyAdapter.OnItemClickListener {

    public Locals locals;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private View lView;
    private DatabaseReference mFirebaseDatabaseReference;
    private ArrayList<Locals> local = new ArrayList<>();

    private void setValorImagem(){
        for(Locals L:local){
            switch (L.getName()){
                case "Jangada":
                    local.get(0).setResId(R.drawable.jangada);
                    break;
                case "Maverick":
                    local.get(1).setResId(R.drawable.maverick);
                    break;
                case "Mc'Donalds":
                    local.get(2).setResId(R.drawable.mcdonalds);
                    break;
                case "Subway":
                    local.get(3).setResId(R.drawable.subway);
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
                DataSnapshot remoteLocals = dataSnapshot.child("Restaurantes");
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

        //local.add(new Locals("Subway", R.drawable.subway, "Av. Piracicaba, 569 - Vila Sao Joao, Limeira"));
        //local.add(new Locals("Jangada", R.drawable.jangada, "Av. Ismael Ferreira dos Santos, 694 - Parque Egisto Ragazzo, Limeira"));
        //local.add(new Locals("Maverick", R.drawable.maverick, "R. Paschoal Marmo, 908 - Jardim Piratininga, Limeira"));
        //local.add(new Locals("McDonalds", R.drawable.mcdonalds, "Av. Comendador Agostinho Prada, 1731 - Jardim Maria Buchi Modeneis, Limeira"));

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
