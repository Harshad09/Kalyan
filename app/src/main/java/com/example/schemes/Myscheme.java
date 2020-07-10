package com.example.schemes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapter.MySchemeAdapter;
import Model.MySchemeModel;

public class Myscheme extends AppCompatActivity {

    RecyclerView myschemeRecycler ;
    String filterName;
    List<String> group ;

    //firebase obj
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    MySchemeAdapter mDataAdapter;
    MySchemeModel mySchemeModel;
    List<MySchemeModel> mySchemeList = new ArrayList<>();
    List<String> check = new ArrayList<>();

    String caste,income,state,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myscheme);

        myschemeRecycler = findViewById(R.id.mySchemeRecyclerView);

        caste = "sc";
        income = "less" ;//less than 250000
        state = "maharashtra";

//        check.add(caste);
        check.add(income);
        check.add(state);
        //gender= "male";

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myschemeRecycler.setLayoutManager(layoutManager);
        mDataAdapter = new MySchemeAdapter(this,mySchemeList);
        myschemeRecycler.setAdapter(mDataAdapter);

        db.collection("scheme").document("education").collection("postmatric_scheme")
                .whereArrayContains("sort",caste)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                group = (List<String>) document.get("sort");
                                if (group.containsAll(check)){

                                    mySchemeModel = document.toObject(MySchemeModel.class);
                                    mySchemeList.add(mySchemeModel);
                                    Log.d("kalyan", document.getId() + " => " + document.getData());
                                }


                            }
                            mDataAdapter.notifyDataSetChanged();
                        } else {
                            Log.d("kalyan", "Error getting documents: ", task.getException());
                        }
                    }
                });








        //bottom

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.myscheme);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.myscheme:
                        return true;
                }

                return false;
            }
        });

        //bottomend

    }
}
