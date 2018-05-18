package br.unicamp.ft.f170775.trabalhomobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

public class Mercado extends Fragment
        implements MyAdapter.OnItemClickListener {

    public View lView;
    public Locals locals;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;


    @Override
    public void onItemClick(Locals locals) {
        this.locals = locals;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        lView = inflater.inflate(R.layout.content_restaurant_drawer, container, false);
        mRecyclerView = (RecyclerView) lView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<Locals> local = new ArrayList<>();
        local.add(new Locals("Santa Rita", R.drawable.santarita, "Av. Cônego Manoel Alves, 678 - Jardim Sao Paulo, Limeira"));
        local.add(new Locals("Covabra", R.drawable.covabra, "Av. Campinas, 50 - Vila Cidade Jardim, Limeira"));
        local.add(new Locals("Enxuto", R.drawable.enxuto, "R. Comendador Vicente Leone, 200 - Jardim Nossa Sra. de Fatima, Limeira"));
        mAdapter = new MyAdapter(local, this);
        mRecyclerView.setAdapter(mAdapter);

        lView.setOnClickListener(new View.OnClickListener() {
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
