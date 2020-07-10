package com.example.schemes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity
{

    EditText editText,editText2,aadhar,password;
    Button login;
    FirebaseFirestore db;
    private String TAG;
    TextView textView;
    String userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*TextView registergo = findViewById(R.id.textView4) ;
        SpannableString content = new SpannableString( "Register" ) ;
        content.setSpan( new UnderlineSpan() , 0 , content.length() , 0 ) ;
        registergo.setText(content) ;

        registergo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });*/


        aadhar = findViewById(R.id.aadhar);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        db = FirebaseFirestore.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Scheme.class));
                final String useraadhar = aadhar.getText().toString().trim();

                DocumentReference docRef = db.collection("users").document(useraadhar);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String userpassword = password.getText().toString().trim();

                        if (task.isSuccessful())
                        {
                            DocumentSnapshot document = task.getResult();
                            String pw = document.getString("password");
                            if (document.exists())
                            {
                                //Log.d(TAG, "DocumentSnapshot data: " + document.getString("password") );
                                if( pw.equalsIgnoreCase(userpassword))
                                {
                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                    //startActivity(new Intent(getApplicationContext(),Home.class));
                                    //Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                    //intent.putExtra("useraadhar",useraadhar);
                                    intent.putExtra("userpassword",userpassword);
                                    startActivity(intent);

                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Password is Wrong", Toast.LENGTH_SHORT).show();

                                }

                            }
                            else {
                                Intent intent = new Intent(MainActivity.this, Home.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "No user with aadhar", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Something is Wrong", Toast.LENGTH_SHORT).show();

                        }
                    }
                });




            }
        });








    }
}
