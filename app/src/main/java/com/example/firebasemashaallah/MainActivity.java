package com.example.firebasemashaallah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

//    // [START define_database_reference]
//    private DatabaseReference mDatabase;
//    // [END define_database_reference]
//
//    private FirebaseRecyclerAdapter<Tanaman, TanamanViewHolder> mAdapter;
//    private RecyclerView recyclerView;
//    private LinearLayoutManager linearLayoutManager;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_show);

    }


    public void Show(View view) {
        Fragment selectedFragment = null;
        selectedFragment = TanamanFragment.newInstances();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, selectedFragment);
        transaction.commit();
    }
}
