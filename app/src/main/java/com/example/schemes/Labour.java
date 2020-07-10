package com.example.schemes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchableInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapter.LabourAdapter;
import Model.LabourModel;

public class Labour extends AppCompatActivity {

    RecyclerView labourRecyclerView ;
    SearchView searchView ;
    //firebase obj
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    LabourAdapter mDataAdapter;
    LabourModel labourModel;
    List<LabourModel> LabourList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour);


        labourRecyclerView = findViewById(R.id.labourRecyclerView);
        searchView = findViewById(R.id.filter);
        searchView.getQuery();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mDataAdapter.getFilter().filter(newText);
                return false;
            }
        });


        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        labourRecyclerView.setLayoutManager(layoutManager);
        mDataAdapter = new LabourAdapter(this,LabourList);
        labourRecyclerView.setAdapter(mDataAdapter);

        db.collection("scheme").document("labour").collection("schemes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                labourModel = document.toObject(LabourModel.class);
                                LabourList.add(labourModel);
                                Log.d("kalyan", document.getId() + " => " + document.getData());
                            }
                            mDataAdapter.setEntityFilter(LabourList);
                            mDataAdapter.notifyDataSetChanged();
                        } else {
                            Log.d("kalyan", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
