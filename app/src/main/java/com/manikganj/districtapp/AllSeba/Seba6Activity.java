package com.manikganj.districtapp.AllSeba;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.manikganj.districtapp.R;
import com.manikganj.districtapp.ads.AdmobAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Seba6Activity extends AppCompatActivity {


    RecyclerView recyclerview;

    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seba6);


        ProgressBar progressBar;
        progressBar = findViewById(R.id.progressBar);

//        //............banner add...........
//        LinearLayout banner_ads = findViewById(R.id.banner_ads);
//        AdmobAds.sdkInitialize(Seba6Activity.this);
//        AdmobAds.setBanner(banner_ads,Seba6Activity.this);
//        //............banner add...........



        // .....................Status bar color ..........................................
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.main));
        window.setNavigationBarColor(getResources().getColor(R.color.main));
        // .....................Status bar color ..........................................


        ImageView menu;
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerview = findViewById(R.id.recyclerview);


        RequestQueue queue = Volley.newRequestQueue(Seba6Activity.this);
        String url = "https://manikganjcityapp.xyz/apps/help_line.json";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray = response.getJSONArray("help_line");

                    for (int i = 0; i<jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String phone = jsonObject.getString("phone");

                        hashMap = new HashMap<>();
                        hashMap.put("name",name);
                        hashMap.put("phone",phone);
                        arrayList.add(hashMap);
                    }
                    MyAdapter myAdapter = new MyAdapter();
                    recyclerview.setLayoutManager(new LinearLayoutManager(Seba6Activity.this));
                    recyclerview.setAdapter(myAdapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
progressBar.setVisibility(View.VISIBLE);
                    }
                });

        queue.add(jsonObjectRequest);














        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }





    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.helpline_item,parent,false);

            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {


            hashMap = arrayList.get(position);

            String NAME = hashMap.get("name");
            String PHONE = hashMap.get("phone");

            holder.help.setText(NAME);
            holder.phone.setText(PHONE);


            holder.animationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PHONE, null)));

                }
            });




        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView help,phone;


            LottieAnimationView animationView;



            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                help = itemView.findViewById(R.id.help);
                phone = itemView.findViewById(R.id.phone);
                animationView = itemView.findViewById(R.id.animationView);




            }
        }
    }







}