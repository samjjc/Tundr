package com.example.johnny.tundr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void GoToLocation(View view){
        Intent startNewActivity = new Intent (this, ShowLocation.class);
        startActivity(startNewActivity);
    }

    public void GoToMap(View view){
        Intent startNewActivity = new Intent (this, MapsActivity.class);
        startActivity(startNewActivity);
    }
    public void addData(View view){

    }

}
