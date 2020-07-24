package com.example.mailordeamon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainDashboardDetails extends AppCompatActivity {
    TextView headingtext1, headingLogo, headingtext2;
    ImageView imageView, backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_details);

        String heading1 = getIntent().getStringExtra("heading1");
        String image = getIntent().getStringExtra("image");

        headingtext1 = findViewById(R.id.heading_text_1);
        headingtext2 = findViewById(R.id.heading_text_2);
        headingtext1.setText(heading1);
        headingtext2.setText(getIntent().getStringExtra("heading2"));
        headingLogo = findViewById(R.id.heading_logo);
        headingLogo.setText(getIntent().getStringExtra("headingLogo"));
        imageView = findViewById(R.id.image_view);
        Picasso.get().load(image).into(imageView);

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

    }
}
