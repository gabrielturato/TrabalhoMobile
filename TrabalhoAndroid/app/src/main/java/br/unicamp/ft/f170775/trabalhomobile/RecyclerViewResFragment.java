package br.unicamp.ft.f170775.trabalhomobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewResFragment extends Fragment implements MyAdapter.OnItemClickListener {

    public Locals local;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    View lView;

    public RecyclerViewResFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lView = inflater.inflate(R.layout.content_main, container, false);
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
        return lView;


    }


    @Override
    public void onItemClick(Locals locals) {
        this.local = locals;
    }
}
