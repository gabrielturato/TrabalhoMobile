package br.unicamp.ft.f170775.trabalhomobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class Shopping extends Fragment
        implements MyAdapter.OnItemClickListener {

    private View lView;
    public Locals locals;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private Button button;

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
        //local.add(new Locals("Shopping Pátio Limeira", R.drawable.shoppingpatio, "R. Carlos Gomes, 1321 - Centro, Limeira "));
        //local.add(new Locals("Shopping Naçôes", R.drawable.shoppingnacoes, "Rod. Dep. Laércio Côrte, 4500 - Jardim Res. Graminha III, Limeira"));
        mAdapter = new MyAdapter(local, this);
        mRecyclerView.setAdapter(mAdapter);

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
