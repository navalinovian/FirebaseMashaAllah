package com.example.firebasemashaallah;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class TanamanFragment extends Fragment {
    View myFragment;
    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Tanaman, TanamanViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;


    public TanamanFragment() {
        // Required empty public constructor
    }

    public static TanamanFragment newInstances()
    {
        TanamanFragment tanamanFragment = new TanamanFragment();
        return tanamanFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_tanaman,container,false);
//        setTitle("Tanaman Hias Akar");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("jenis").child("akar");

        recyclerView = myFragment.findViewById(R.id.list_tanaman);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(container.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
//        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(linearLayoutManager);



        Query query = mDatabase;

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Tanaman>()
                .setQuery(query, Tanaman.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Tanaman, TanamanViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TanamanViewHolder tanamanViewHolder, int i, @NonNull Tanaman tanaman) {
                tanamanViewHolder.bindToTanaman(tanaman);
                Glide.with(TanamanFragment.this).load(tanaman.getGambar()).into(tanamanViewHolder.ivGambar);
            }

            @NonNull
            @Override
            public TanamanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new TanamanViewHolder(inflater.inflate(R.layout.item_tanaman, parent, false));
            }
        };

        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);

        if (mAdapter != null) {
            mAdapter.startListening();
        }

        System.out.println(mAdapter);

        return myFragment;
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }
}
