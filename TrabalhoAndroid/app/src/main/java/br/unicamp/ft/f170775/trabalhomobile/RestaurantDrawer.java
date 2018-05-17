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
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantDrawer extends Fragment
        implements MyAdapter.OnItemClickListener {

    public Locals local;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private View lView;

    @Override
    public void onItemClick(Locals locals) {
        this.local = locals;
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
        local.add(new Locals("Subway", R.drawable.subway, "Av. Piracicaba, 569 - Vila Sao Joao, Limeira"));
        local.add(new Locals("Jangada", R.drawable.jangada, "Av. Ismael Ferreira dos Santos, 694 - Parque Egisto Ragazzo, Limeira"));
        local.add(new Locals("Maverick", R.drawable.maverick, "R. Paschoal Marmo, 908 - Jardim Piratininga, Limeira"));
        local.add(new Locals("McDonalds", R.drawable.mcdonalds, "Av. Comendador Agostinho Prada, 1731 - Jardim Maria Buchi Modeneis, Limeira"));
        mAdapter = new MyAdapter(local, this);
        mRecyclerView.setAdapter(mAdapter);

        lView.findViewById(R.id.buttonRestaurante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Voce apertou o botão para buscar", Toast.LENGTH_LONG).show();
            }
        });
        return lView;
    }
}
