package com.manikganj.districtapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.manikganj.districtapp.AllSeba.Seba10Activity;
import com.manikganj.districtapp.AllSeba.Seba11Activity;
import com.manikganj.districtapp.AllSeba.Seba12Activity;
import com.manikganj.districtapp.AllSeba.Seba13Activity;
import com.manikganj.districtapp.AllSeba.Seba14Activity;
import com.manikganj.districtapp.AllSeba.Seba15Activity;
import com.manikganj.districtapp.AllSeba.Seba1Activity;
import com.manikganj.districtapp.AllSeba.Seba2Activity;
import com.manikganj.districtapp.AllSeba.Seba3Activity;
import com.manikganj.districtapp.AllSeba.Seba4Activity;
import com.manikganj.districtapp.AllSeba.Seba5Activity;
import com.manikganj.districtapp.AllSeba.Seba6Activity;
import com.manikganj.districtapp.AllSeba.Seba7Activity;
import com.manikganj.districtapp.AllSeba.Seba8Activity;
import com.manikganj.districtapp.AllSeba.Seba9Activity;
import com.manikganj.districtapp.ads.AdmobAds;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NetworkChangeReceiver.NetworkChangeListener{


    AlertDialog dialog;
    NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver(this);

    RecyclerView recyclerview;

    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        NavigationView();
        TextMarque();
        ImageSliderView();


        MyHashMap();
        recyclerview = findViewById(R.id.recyclerview);
        MyAdapter myAdapter = new MyAdapter();
        recyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        recyclerview.setAdapter(myAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//
//         //............banner add...........
//        LinearLayout banner_ads = findViewById(R.id.banner_ads);
//        AdmobAds.sdkInitialize(MainActivity.this);
//        AdmobAds.setBanner(banner_ads,MainActivity.this);
//        //............banner add...........


//        AdmobAds.load_Interstitial_ads(MainActivity.this);




    }


    private void MyHashMap() {


        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.hospital);
        hashMap.put("text", "হাসপাতাল");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.donor);
        hashMap.put("text", "ব্লাড ডোনার");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.emergencg);
        hashMap.put("text", "এ্যাম্বুলেন্স");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.doctor);
        hashMap.put("text", "ডাক্তার");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.firetruck);
        hashMap.put("text", "ফায়ার সার্ভিস");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.helpline);
        hashMap.put("text", "হেল্প লাইন");
        arrayList.add(hashMap);


//        hashMap = new HashMap<>();
//        hashMap.put("image", "" + R.drawable.policeman);
//        hashMap.put("text", "থানা পুলিশ");
//        arrayList.add(hashMap);



        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.writing);
        hashMap.put("text", "সাংবাদিক");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.compliance);
        hashMap.put("text", "বিএমইটি সেবা");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.cori);
        hashMap.put("text", "কুরিয়ার সার্ভিস");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.bed);
        hashMap.put("text", "স্বাস্থ্য কমপ্লেক্স");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.eco);
        hashMap.put("text", "পল্লি বিদ্যুৎ");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("image", "" + R.drawable.passport);
        hashMap.put("text", "পাসপোর্ট সেবা");
        arrayList.add(hashMap);

//
//        hashMap = new HashMap<>();
//        hashMap.put("image", "" + R.drawable.developer);
//        hashMap.put("text", "ডেভেলপার");
//        arrayList.add(hashMap);


    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View mView = layoutInflater.inflate(R.layout.home_item, parent, false);


            return new MyViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

            hashMap = arrayList.get(position);

            String TEXT = hashMap.get("text");
            String IMAGE = hashMap.get("image");

            holder.textView.setText(TEXT);

            int icon = Integer.parseInt(IMAGE);
            holder.imageView.setImageResource(icon);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    hashMap = arrayList.get(position);

                    int item = position;

                    if (item == 0) {


                           startActivity(new Intent(MainActivity.this, Seba1Activity.class));




                    }

                    if (item == 1) {



                            startActivity(new Intent(MainActivity.this, Seba2Activity.class));









                    }

                    if (item == 2) {



                            startActivity(new Intent(MainActivity.this, Seba3Activity.class));








                    }

                    if (item == 3) {



                            startActivity(new Intent(MainActivity.this, Seba4Activity.class));



                    }

                    if (item == 4) {



                            startActivity(new Intent(MainActivity.this, Seba5Activity.class));




                    }

                    if (item == 5) {




                            startActivity(new Intent(MainActivity.this, Seba6Activity.class));




                    }
//                    if (item == 6) {
//
//
//
//                            startActivity(new Intent(MainActivity.this, Seba7Activity.class));
//
//
//
//
//                    }


                    if (item == 6) {


                            startActivity(new Intent(MainActivity.this, Seba9Activity.class));


                    }

                    if (item == 7) {



                            startActivity(new Intent(MainActivity.this, Seba10Activity.class));


                    }

                    if (item == 8) {




                            startActivity(new Intent(MainActivity.this, Seba11Activity.class));


                    }

                    if (item == 9) {


                            startActivity(new Intent(MainActivity.this, Seba12Activity.class));


                    }

                    if (item == 10) {


                            startActivity(new Intent(MainActivity.this, Seba13Activity.class));


                    }

                    if (item == 11) {



                            startActivity(new Intent(MainActivity.this, Seba14Activity.class));


                    }

//                    if (item == 12) {
//
//
//                            startActivity(new Intent(MainActivity.this, DeveloperActivity.class));
//
//
//                    }


                }
            });


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.textView);
                imageView = itemView.findViewById(R.id.imageView);


            }
        }
    }


    private void ImageSliderView() {


        //.........................Image Slider ..............................
        ImageSlider image_slider;
        image_slider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.m1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.m2, ScaleTypes.CENTER_CROP));

        image_slider.setImageList(imageList);
        //.........................Image Slider ..............................


    }

    private void TextMarque() {

        // ........................Text Marque .............................
        TextView textMarquee;
        textMarquee = findViewById(R.id.textMarquee);
        textMarquee.setSelected(true);
        //........................Text Marque ................................

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://manikganjcityapp.xyz/apps/notice.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textMarquee.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textMarquee.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);




    }

    private void NavigationView() {

        DrawerLayout drawerLayout;
        NavigationView navigationView;
        ImageView menu;
        View header;


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        menu = findViewById(R.id.menu);

        header = navigationView.getHeaderView(0);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.share) {
                    String shareBody = "Hye,Online Quiz App" + "http://play.google.com/store/app/details?id =" + MainActivity.this.getPackageName();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.star) {

                    Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + MainActivity.this.getPackageName()));
                    startActivity(rateIntent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else if (item.getItemId() == R.id.home) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else if (item.getItemId() == R.id.developer) {
                    startActivity(new Intent(MainActivity.this,DeveloperActivity.class));
                }

                else if (item.getItemId() == R.id.logout) {
                    finishAndRemoveTask();
                }

                else if (item.getItemId() == R.id.privacy) {

                    String url = "https://sites.google.com/view/manikgonj-city-app--/home";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }



                return false;
            }
        });

    }

    //Custom Alert Dialog
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        ShowDialogBox();

    }

    private void ShowDialogBox (){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCancelable(false);

        mView.findViewById(R.id.chancelBTN).setOnClickListener(v -> {
            alertDialog.dismiss();
            finishAndRemoveTask();
        });

        mView.findViewById(R.id.okBTN).setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        alertDialog.show();
    }

    //Custom Alert Dialog


    @Override
    public void onNetworkChanged(boolean isConnected) {
        if (isConnected) {

            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }

        } else {

            if (dialog == null || !dialog.isShowing()) {

                ShowDialog();
            }

        }
    }

    private void ShowDialog() {

        dialog = new AlertDialog.Builder(MainActivity.this)
                .setView(R.layout.no_internet_dialog)
                .setCancelable(false)
                .create();
        dialog.show();


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));


        TextView playButton = dialog.findViewById(R.id.playButton);

        playButton.setOnClickListener(view->{

            dialog.dismiss();
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeReceiver);
    }



}