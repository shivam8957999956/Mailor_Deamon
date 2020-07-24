package com.example.mailordeamon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //recycler
    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<Message> messagesList;
    private RecyclerAdapter recyclerAdapter;
    TextView topHeading;
    //progressbar
    ProgressBar progressBarNational;
    LinearLayout nationalLinearLayout;

    //wdget for covid 19

    ProgressBar progressBarCovid;
    RecyclerView recyclerViewCovid;
    private RecyclerAdapterCovid recyclerAdapterCovid;
    private ArrayList<MessageCovid> messageListCovid;
    LinearLayout covidLinearLayout;


    //widgets for sports
    ProgressBar progressBarsports;
    RecyclerView recyclerViewsports;
    private RecyclerAdapterSports recyclerAdaptersports;
    private ArrayList<Message> messageListsports;
    LinearLayout sportsLinearLayout;

    //widgets for entertainment
    ProgressBar progressBarentertainment;
    RecyclerView recyclerViewentertainment;
    private RecyclerAdapterEntertainment recyclerAdapterentertainment;
    private ArrayList<Message> messageListentertainment;
    LinearLayout entertainmentLinearLayout;

    //widgets for technology
    ProgressBar progressBartechnology;
    RecyclerView recyclerViewtecnology;
    private RecyclerAdapterTechnology recyclerAdaptertechnology;
    private ArrayList<Message> messageListtechnology;
    LinearLayout technologyLinearLayout;


    //navigation view
    static final float END_SCALE = 0.7f;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    RelativeLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        topHeading = findViewById(R.id.top_heading);
        topHeading.setSelected(true);

        //hooks for progress bar
        progressBarNational = findViewById(R.id.progress_bar_national);
        recyclerView = findViewById(R.id.recyclerView);

        nationalLinearLayout = findViewById(R.id.national_linear_layout);

        //hooks for covid 19

        progressBarCovid = findViewById(R.id.progress_bar_covid);
        recyclerViewCovid = findViewById(R.id.recyclerViewCovid);
        covidLinearLayout = findViewById(R.id.covid_linear_layout);

        //hooks for sports

        progressBarsports = findViewById(R.id.progress_bar_sport);
        recyclerViewsports = findViewById(R.id.recyclerViewSport);
        sportsLinearLayout = findViewById(R.id.sport_linear_layout);

        //hooks for sports

        progressBarentertainment = findViewById(R.id.progress_bar_entertainment);
        recyclerViewentertainment = findViewById(R.id.recyclerViewEntertainment);
        entertainmentLinearLayout = findViewById(R.id.entertainment_linear_layout);

        //hooks for sports

        progressBartechnology = findViewById(R.id.progress_bar_technology);
        recyclerViewtecnology = findViewById(R.id.recyclerViewTechnology);
        technologyLinearLayout = findViewById(R.id.technology_linear_layout);

        //navigation view
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        menuIcon = findViewById(R.id.menu_icon);
        content = findViewById(R.id.container);
        navigationDrawer();

        //firebase hooks
        myRef = FirebaseDatabase.getInstance().getReference();
        topHeadingNews();
        nationalPolitcs();

        covid();

        sports();

        entertainment();

        technology();

    }

    private void technology() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewtecnology.setLayoutManager(linearLayoutManager);
        recyclerViewtecnology.setHasFixedSize(true);

        messageListtechnology = new ArrayList<>();

        clearAllTechnology();

        getDataFromFireBaseTechnology();

    }


    private void getDataFromFireBaseTechnology() {

        Query query = myRef.child("technology");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAllTechnology();
                for (DataSnapshot snapsports : snapshot.getChildren()) {
                    Message message = new Message();
                    message.setImage(snapsports.child("image").getValue().toString());
                    message.setText1(snapsports.child("text1").getValue().toString());
                    message.setText2(snapsports.child("text2").getValue().toString());
                    message.setText3(snapsports.child("text3").getValue().toString());
                    messageListtechnology.add(message);

                }
                recyclerAdaptertechnology = new RecyclerAdapterTechnology(getApplicationContext(), messageListtechnology);
                progressBartechnology.setVisibility(View.GONE);
                recyclerViewtecnology.setAdapter(recyclerAdaptertechnology);
                technologyLinearLayout.setVisibility(View.VISIBLE);
                recyclerAdapter.notifyDataSetChanged();

                recyclerAdaptertechnology.setOnItemClickListener(new RecyclerAdapterTechnology.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String text1 = messageListtechnology.get(position).getText1();
                        String text3 = messageListtechnology.get(position).getText3();
                        String image = messageListtechnology.get(position).getImage();
                        Intent intent = new Intent(getApplicationContext(), MainDashboardDetails.class);
                        intent.putExtra("headingLogo", "Technology");
                        intent.putExtra("heading1", text1);
                        intent.putExtra("heading2", text3);
                        intent.putExtra("image", image);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void clearAllTechnology() {
        if (messageListtechnology != null) {
            messageListtechnology.clear();

            if (recyclerAdaptertechnology != null) {
                recyclerAdaptertechnology.notifyDataSetChanged();
            }
        }
        messageListtechnology = new ArrayList<>();

    }

    private void entertainment() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewentertainment.setLayoutManager(linearLayoutManager);
        recyclerViewentertainment.setHasFixedSize(true);

        messageListentertainment = new ArrayList<>();

        clearAllEntertainment();

        getDataFromFireBaseEntertainment();

    }

    private void getDataFromFireBaseEntertainment() {
        Query query = myRef.child("entertainment");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAllEntertainment();
                for (DataSnapshot snapentertainment : snapshot.getChildren()) {
                    Message message = new Message();
                    message.setImage(snapentertainment.child("image").getValue().toString());
                    message.setText1(snapentertainment.child("text1").getValue().toString());
                    message.setText2(snapentertainment.child("text2").getValue().toString());
                    message.setText3(snapentertainment.child("text3").getValue().toString());
                    messageListentertainment.add(message);

                }
                recyclerAdapterentertainment = new RecyclerAdapterEntertainment(getApplicationContext(), messageListentertainment);
                progressBarentertainment.setVisibility(View.GONE);
                recyclerViewentertainment.setAdapter(recyclerAdapterentertainment);
                entertainmentLinearLayout.setVisibility(View.VISIBLE);
                recyclerAdapterentertainment.notifyDataSetChanged();

                recyclerAdapterentertainment.setOnItemClickListener(new RecyclerAdapterEntertainment.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String text1 = messageListentertainment.get(position).getText1();
                        String text3 = messageListentertainment.get(position).getText3();
                        String image = messageListentertainment.get(position).getImage();
                        Intent intent = new Intent(getApplicationContext(), MainDashboardDetails.class);
                        intent.putExtra("headingLogo", "Entertainment");
                        intent.putExtra("heading1", text1);
                        intent.putExtra("heading2", text3);

                        intent.putExtra("image", image);
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void clearAllEntertainment() {
        if (messageListentertainment != null) {
            messageListentertainment.clear();

            if (recyclerAdapterentertainment != null) {
                recyclerAdapterentertainment.notifyDataSetChanged();
            }
        }
        messageListentertainment = new ArrayList<>();
    }

    private void sports() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewsports.setLayoutManager(linearLayoutManager);
        recyclerViewsports.setHasFixedSize(true);

        messageListsports = new ArrayList<>();

        clearAllsports();


        getDataFromFireBaseSports();
    }

    private void clearAllsports() {
        if (messageListsports != null) {
            messageListsports.clear();

            if (recyclerAdaptersports != null) {
                recyclerAdaptersports.notifyDataSetChanged();
            }
        }
        messageListsports = new ArrayList<>();

    }

    private void getDataFromFireBaseSports() {
        Query query = myRef.child("sports");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAllsports();
                for (DataSnapshot snapsports : snapshot.getChildren()) {
                    Message message = new Message();
                    message.setImage(snapsports.child("image").getValue().toString());
                    message.setText1(snapsports.child("text1").getValue().toString());
                    message.setText2(snapsports.child("text2").getValue().toString());
                    message.setText3(snapsports.child("text3").getValue().toString());
                    messageListsports.add(message);

                }
                recyclerAdaptersports = new RecyclerAdapterSports(getApplicationContext(), messageListsports);
                progressBarsports.setVisibility(View.GONE);
                recyclerViewsports.setAdapter(recyclerAdaptersports);
                sportsLinearLayout.setVisibility(View.VISIBLE);
                recyclerAdaptersports.notifyDataSetChanged();
                recyclerAdaptersports.setOnClickListener(new RecyclerAdapterSports.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String text1 = messageListsports.get(position).getText1();
                        String text3 = messageListsports.get(position).getText3();
                        String image = messageListsports.get(position).getImage();
                        Intent intent = new Intent(getApplicationContext(), MainDashboardDetails.class);
                        intent.putExtra("headingLogo", "Sport");
                        intent.putExtra("heading1", text1);
                        intent.putExtra("heading2", text3);
                        intent.putExtra("image", image);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.live_channels);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.addDrawerListener((new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //scale the view based on current slide off set
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                content.setScaleX(offsetScale);
                content.setScaleY(offsetScale);

                //Translate the view,accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = content.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                content.setTranslationX(xTranslation);
            }
        }));

    }


    private void covid() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCovid.setLayoutManager(layoutManager);
        recyclerViewCovid.setHasFixedSize(true);
        messageListCovid = new ArrayList<>();

        clearAllCovid();

        getDataFromFireBaseCovid();
    }

    private void getDataFromFireBaseCovid() {
        Query query = myRef.child("covid");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAllCovid();
                for (DataSnapshot snapcovid : snapshot.getChildren()) {
                    MessageCovid messageCovid = new MessageCovid();
                    messageCovid.setImage(snapcovid.child("image").getValue().toString());
                    messageCovid.setText1(snapcovid.child("text1").getValue().toString());
                    messageCovid.setText2(snapcovid.child("text2").getValue().toString());
                    messageCovid.setText3(snapcovid.child("text3").getValue().toString());
                    messageListCovid.add(messageCovid);
                }
                recyclerAdapterCovid = new RecyclerAdapterCovid(getApplicationContext(), messageListCovid);
                progressBarCovid.setVisibility(View.GONE);
                recyclerViewCovid.setAdapter(recyclerAdapterCovid);
                covidLinearLayout.setVisibility(View.VISIBLE);
                recyclerAdapterCovid.notifyDataSetChanged();

                recyclerAdapterCovid.setOnItemClickListener(new RecyclerAdapterCovid.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String text1 = messageListCovid.get(position).getText1();
                        String text3 = messageListCovid.get(position).getText3();
                        String image = messageListCovid.get(position).getImage();
                        Intent intent = new Intent(getApplicationContext(), MainDashboardDetails.class);
                        intent.putExtra("headingLogo", "Covid 19 Report");
                        intent.putExtra("heading1", text1);
                        intent.putExtra("heading2", text3);
                        intent.putExtra("image", image);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void clearAllCovid() {
        if (messageListCovid != null) {
            messageListCovid.clear();

            if (recyclerAdapterCovid != null) {
                recyclerAdapterCovid.notifyDataSetChanged();
            }

        }
        messageListCovid = new ArrayList<>();

    }


    private void topHeadingNews() {
        Query query = myRef.child("top heading");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String _topHeading = snapshot.getValue().toString();
                topHeading.setText(_topHeading);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void nationalPolitcs() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        messagesList = new ArrayList<>();

        clearAll();


        getDataFromFireBase();


    }

    private void getDataFromFireBase() {
        Query query = myRef.child("national politics");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAll();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Message message = new Message();
                    message.setText1(snap.child("text1").getValue().toString());
                    message.setText2(snap.child("text2").getValue().toString());
                    message.setText3(snap.child("text3").getValue().toString());
                    message.setImage(snap.child("image").getValue().toString());

                    messagesList.add(message);
                }
                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), messagesList);
                progressBarNational.setVisibility(View.GONE);
                recyclerView.setAdapter(recyclerAdapter);
                nationalLinearLayout.setVisibility(View.VISIBLE);
                recyclerAdapter.notifyDataSetChanged();
                recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String text1 = messagesList.get(position).getText1();
                        String text3 = messagesList.get(position).getText3();
                        String image = messagesList.get(position).getImage();

                        Intent intent = new Intent(getApplicationContext(), MainDashboardDetails.class);
                        intent.putExtra("headingLogo", "National Political");
                        intent.putExtra("heading1", text1);
                        intent.putExtra("heading2", text3);
                        intent.putExtra("image", image);
                        startActivity(intent);


                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void clearAll() {
        if (messagesList != null) {

            messagesList.clear();
            if (recyclerAdapter != null) {
                recyclerAdapter.notifyDataSetChanged();
            }

        }
        messagesList = new ArrayList<>();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.live_channels:
                Intent i = new Intent(getApplicationContext(), LiveNewsChannels.class);
                startActivity(i);
                break;
            case R.id.news_sites:
                Intent i2 = new Intent(getApplicationContext(), webView.class);
                startActivity(i2);
                break;
            case R.id.share:
                Toast.makeText(this, "Service Unavailable as app is in under development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.entertainment:
                Toast.makeText(this, "Service Unavailable as app is in under development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sport:
                Toast.makeText(this, "Service Unavailable as app is in under development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.politics:
                Toast.makeText(this, "Service Unavailable as app is in under development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.technology:
                Toast.makeText(this, "Service Unavailable as app is in under development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Covid_19:
                Toast.makeText(this, "Service Unavailable as app is in under development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.feedback:
                Intent i3 = new Intent(getApplicationContext(), FeedBack.class);
                startActivity(i3);
                break;
        }

        return false;
    }
}
