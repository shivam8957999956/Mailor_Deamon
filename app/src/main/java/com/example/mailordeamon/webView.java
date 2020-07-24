package com.example.mailordeamon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class webView extends AppCompatActivity {
    WebView browser;
    TextInputLayout searchBar;
    EditText text;
    Button button,searchBtn;
    ProgressBar progressBar;

    ImageView backBtn;

    //recyclerview
    private RecyclerView recyclerViewColumnOne, recyclerViewColumnTwo;
    private DatabaseReference mRef;
    //column one
    private ArrayList<PopularNewsSites> popularNewsSitesList;
    private PopularNewsAdapter popularNewsAdapter;
    //coloumn two

    private ArrayList<PopularNewsSites> popularNewsSitesListTwo;
    private PopularSitesAdapterTwo popularSitesAdapterTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progressBar=findViewById(R.id.progress_bar);

        recyclerViewColumnOne = findViewById(R.id.recycler_view_column_one);
        recyclerViewColumnTwo = findViewById(R.id.recycler_view_column_two);
        mRef = FirebaseDatabase.getInstance().getReference();

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

        searchBar=findViewById(R.id.search_news_channel);
        searchBtn=findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link=searchBar.getEditText().getText().toString().trim();
                String l="";
                for(int i=0;i<link.length();i++){
                    if(link.charAt(i)!=' '){
                        l=l+link;
                    }
                }

                Intent i=new Intent(getApplicationContext(),webViewMain.class);
                i.putExtra("site","https://www."+l+".com/");
                startActivity(i);
            }
        });

        recyclercolumnOne();
        recyclerColumnTwo();
        progressBar.setVisibility(View.GONE);

    }

    private void recyclerColumnTwo() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewColumnTwo.setLayoutManager(linearLayoutManager);
        recyclerViewColumnTwo.setHasFixedSize(true);
        popularNewsSitesListTwo = new ArrayList<>();

        clearDataTwo();

        getDataFromFirebaseColumnOneTwo();


    }

    private void clearDataTwo() {

        if(popularNewsSitesListTwo!=null){
            popularNewsSitesListTwo.clear();

            if(popularSitesAdapterTwo!=null){
                popularSitesAdapterTwo.notifyDataSetChanged();
            }

        }
        popularNewsSitesListTwo=new ArrayList<>();

    }

    private void getDataFromFirebaseColumnOneTwo() {
        Query query=mRef.child("sites column two");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearDataTwo();

                for (DataSnapshot popularsnapshottwo: snapshot.getChildren()){
                    PopularNewsSites popularNewsSites=new PopularNewsSites();
                    popularNewsSites.setSite(popularsnapshottwo.child("site").getValue().toString());
                    popularNewsSites.setName(popularsnapshottwo.child("name").getValue().toString());
                    popularNewsSites.setImage(popularsnapshottwo.child("image").getValue().toString());

                    popularNewsSitesListTwo.add(popularNewsSites);
                }
                popularSitesAdapterTwo =new PopularSitesAdapterTwo(getApplicationContext(),popularNewsSitesListTwo);
                recyclerViewColumnTwo.setAdapter(popularSitesAdapterTwo);
                popularSitesAdapterTwo.notifyDataSetChanged();

                popularSitesAdapterTwo.setOnClickListener(new PopularSitesAdapterTwo.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {

                        String site=popularNewsSitesListTwo.get(position).getSite();
                        Intent intent=new Intent(getApplicationContext(),webViewMain.class);
                        intent.putExtra("site",site);
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void recyclercolumnOne() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewColumnOne.setLayoutManager(linearLayoutManager);
        recyclerViewColumnOne.setHasFixedSize(true);
        popularNewsSitesList = new ArrayList<>();

        clearData();

        getDataFromFirebaseColumnOne();

    }

    private void clearData() {

        if (popularNewsSitesList != null) {
            popularNewsSitesList.clear();

            if (popularNewsAdapter != null) {
                popularNewsAdapter.notifyDataSetChanged();
            }
        }
        popularNewsSitesList = new ArrayList<>();

    }

    private void getDataFromFirebaseColumnOne() {
        Query query = mRef.child("sites column one");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearData();

                for (DataSnapshot popularsnapshot : snapshot.getChildren()) {

                    PopularNewsSites popularNewsSites = new PopularNewsSites();
                    popularNewsSites.setImage(popularsnapshot.child("image").getValue().toString());
                    popularNewsSites.setName(popularsnapshot.child("name").getValue().toString());
                    popularNewsSites.setSite(popularsnapshot.child("site").getValue().toString());

                    popularNewsSitesList.add(popularNewsSites);

                }
                popularNewsAdapter = new PopularNewsAdapter(getApplicationContext(), popularNewsSitesList);
                recyclerViewColumnOne.setAdapter(popularNewsAdapter);
                popularNewsAdapter.notifyDataSetChanged();

                popularNewsAdapter.setOnItemClickListener(new PopularNewsAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        String site=popularNewsSitesList.get(position).getSite();
                        Intent intent=new Intent(getApplicationContext(),webViewMain.class);
                        intent.putExtra("site",site);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
