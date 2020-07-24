package com.example.mailordeamon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {
    TextInputLayout name,description;
    Button submitFeedback;
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        name=findViewById(R.id.feedback_name);
        description=findViewById(R.id.feedback_description);

        submitFeedback=findViewById(R.id.submit_btn);

        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainDashboard.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });


        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeData();

            }
        });


    }

    private void storeData() {
        DatabaseReference mRef= FirebaseDatabase.getInstance().getReference();
        String _name=name.getEditText().getText().toString().trim();
        String _description=description.getEditText().getText().toString().trim();
        String id =mRef.push().getKey();
        mRef.child("feedback").child(id).child("name").setValue(_name);
        mRef.child("feedback").child(id).child("description").setValue(_description);
        Toast.makeText(this, "FeedBack Submitted", Toast.LENGTH_SHORT).show();


    }
}
