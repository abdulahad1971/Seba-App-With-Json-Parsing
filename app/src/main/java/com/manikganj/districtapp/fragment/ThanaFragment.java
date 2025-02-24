package com.manikganj.districtapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.manikganj.districtapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ThanaFragment extends Fragment {


    RecyclerView recyclerview;

    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View MvIEW =  inflater.inflate(R.layout.fragment_thana, container, false);


        ProgressBar progressBar;
        progressBar = MvIEW.findViewById(R.id.progressBar);
        recyclerview = MvIEW.findViewById(R.id.recyclerview);


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://manikgonjcityapp.xyz/apps/thana.json";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray = response.getJSONArray("thana");

                    for (int i = 0; i<jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String thana = jsonObject.getString("thana");
                        String phone = jsonObject.getString("phone");

                        hashMap = new HashMap<>();
                        hashMap.put("name",name);
                        hashMap.put("thana",thana);
                        hashMap.put("phone",phone);
                        arrayList.add(hashMap);

                    }

                    MyAdapter myAdapter = new MyAdapter();
                    recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
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







        return MvIEW;
    }



    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.thana_item,parent,false);

            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {


            hashMap = arrayList.get(position);

            String NAME = hashMap.get("name");
            String thana = hashMap.get("thana");
            String PHONE = hashMap.get("phone");

            holder.name.setText("নাম : " +NAME);
            holder.thana.setText("থানার নাম : " +thana);
            holder.phone.setText("মোবাইল : "+PHONE);


            holder.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PHONE, null)));

                }
            });

            holder.google_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri gmmIntentUri = Uri.parse("geo:0,0?q="+thana);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

                }
            });


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView name,thana,phone;


            Button google_map,call;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                call = itemView.findViewById(R.id.call);
                google_map = itemView.findViewById(R.id.google_map);


                name = itemView.findViewById(R.id.name);
                thana = itemView.findViewById(R.id.thana);
                phone = itemView.findViewById(R.id.phone);

            }


        }
    }





}