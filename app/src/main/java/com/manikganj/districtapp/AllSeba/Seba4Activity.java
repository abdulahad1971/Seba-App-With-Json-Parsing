package com.manikganj.districtapp.AllSeba;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.manikganj.districtapp.R;
import com.manikganj.districtapp.ads.AdmobAds;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Seba4Activity extends AppCompatActivity {


    RecyclerView recyclerview;

    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();


    EditText search;
    MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seba4);

        ProgressBar progressBar;
        progressBar = findViewById(R.id.progressBar);

//
//        //............banner add...........
//        LinearLayout banner_ads = findViewById(R.id.banner_ads);
//        AdmobAds.sdkInitialize(Seba4Activity.this);
//        AdmobAds.setBanner(banner_ads,Seba4Activity.this);
//        //............banner add...........





        search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                search(s.toString());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





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



        RequestQueue queue = Volley.newRequestQueue(Seba4Activity.this);
        String url = "https://manikganjcityapp.xyz/apps/doctor.json";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("doctor");



                    for (int i = 0; i<jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String image = jsonObject.getString("image");
                        String education = jsonObject.getString("education");
                        String podobi = jsonObject.getString("podobi");
                        String chamber = jsonObject.getString("chamber");
                        String address = jsonObject.getString("address");
                        String phone = jsonObject.getString("phone");

                        hashMap = new HashMap<>();
                        hashMap.put("name",name);
                        hashMap.put("image",image);
                        hashMap.put("education",education);
                        hashMap.put("podobi",podobi);
                        hashMap.put("chamber",chamber);
                        hashMap.put("address",address);
                        hashMap.put("phone",phone);
                        arrayList.add(hashMap);
                    }

                    myAdapter = new MyAdapter(arrayList);
                    recyclerview.setLayoutManager(new LinearLayoutManager(Seba4Activity.this));
                    recyclerview.setAdapter(myAdapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

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



        ArrayList<HashMap<String,String>> MAIN_ARRAYLIST;


        public MyAdapter(ArrayList<HashMap<String, String>> MAIN_ARRAYLIST) {
            this.MAIN_ARRAYLIST = MAIN_ARRAYLIST;
        }






        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.doctor_item,parent,false);

            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {


            hashMap = MAIN_ARRAYLIST.get(position);

            String NAME = hashMap.get("name");
            String image = hashMap.get("image");
            String education = hashMap.get("education");
            String podobi = hashMap.get("podobi");
            String chamber = hashMap.get("chamber");
            String ADDRESS = hashMap.get("address");
            String PHONE = hashMap.get("phone");

            holder.name.setText("নাম: " +NAME);
            holder.education.setText("শিক্ষাগত যোগ্যতা : "+education);
            holder.podobi.setText("পদবি : "+podobi);
            holder.chamber.setText("চেম্বার : " +chamber);
            holder.address.setText("ঠিকানা: " +ADDRESS);
            holder.phone.setText("ফোন : "+PHONE);

            Picasso.get()
                    .load(image)
                    .placeholder(R.drawable.doctor)
                    .into(holder.image);

            holder.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PHONE, null)));

                }
            });


            holder.google_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ADDRESS);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

                }
            });



        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView name,address,phone,education,podobi,chamber;


            Button google_map,call;

            CircleImageView image;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                address = itemView.findViewById(R.id.address);
                phone = itemView.findViewById(R.id.phone);
                education = itemView.findViewById(R.id.education);
                podobi = itemView.findViewById(R.id.podobi);
                chamber = itemView.findViewById(R.id.chamber);


                image = itemView.findViewById(R.id.image);


                call = itemView.findViewById(R.id.call);
                google_map = itemView.findViewById(R.id.google_map);


            }
        }


        @Override
        public int getItemCount() {
            return MAIN_ARRAYLIST.size();
        }




        private void setArrayList(ArrayList<HashMap<String,String>> arrayListER){

            MAIN_ARRAYLIST = arrayListER;
            notifyDataSetChanged();

        }

    }


    private void search(String searchText){

        ArrayList<HashMap<String,String>> searchList = new ArrayList<>();

        for (HashMap<String,String> hashMap1 : arrayList){

            if (hashMap1.get("name").toLowerCase().contains(searchText) || hashMap1.get("address").toLowerCase().contains(searchText)
                    || hashMap1.get("phone").toLowerCase().contains(searchText) || hashMap1.get("education").toLowerCase().contains(searchText)
                    || hashMap1.get("podobi").toLowerCase().contains(searchText)|| hashMap1.get("chamber").toLowerCase().contains(searchText)
            ){

                searchList.add(hashMap1);

            }

        }

        myAdapter.setArrayList(searchList);


    }







}