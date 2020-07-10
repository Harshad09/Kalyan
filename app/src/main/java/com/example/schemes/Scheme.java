package com.example.schemes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Scheme extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);

        //fetching data
        firebaseFirestore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.firestore_list);

        //query
        Query query = firebaseFirestore.collection("fake");
        FirestoreRecyclerOptions<SchemeModal> options = new FirestoreRecyclerOptions.Builder<SchemeModal>()
                .setQuery(query,SchemeModal.class)
                .build();
        //recycleroption
        adapter = new FirestoreRecyclerAdapter<SchemeModal, SchemeViewHolder>(options) {
            @NonNull
            @Override
            public SchemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single,parent,false);
                return new SchemeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull SchemeViewHolder holder, int position, @NonNull SchemeModal model) {
                holder.list_name.setText(model.getName());
                holder.list_surname.setText(model.getSurname());
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
        //view holder


    }

    private class SchemeViewHolder extends RecyclerView.ViewHolder
    {
        private TextView list_name;
        private TextView list_surname;

        public SchemeViewHolder(@NonNull View itemView) {
            super(itemView);
            list_name = itemView.findViewById(R.id.list_name);
            list_surname = itemView.findViewById(R.id.list_surname);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
