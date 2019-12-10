package com.example.firebasemashaallah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Tanaman, TanamanViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tanaman Hias Akar");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("jenis").child("akar");

        recyclerView = findViewById(R.id.list_tanaman);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);



        Query query = mDatabase;

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Tanaman>()
                .setQuery(query, Tanaman.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Tanaman, TanamanViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TanamanViewHolder tanamanViewHolder, int i, @NonNull Tanaman tanaman) {
                tanamanViewHolder.bindToTanaman(tanaman);
                Glide.with(MainActivity.this).load(tanaman.getGambar()).into(tanamanViewHolder.ivGambar);
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

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }
}
