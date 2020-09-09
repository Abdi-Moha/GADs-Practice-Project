package com.gadspracticeproject.Tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gadspracticeproject.Adapters.LearningLeadersAdapter;
import com.gadspracticeproject.ApiRepo;
import com.gadspracticeproject.Models.LeaderModel;
import com.gadspracticeproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearningLeaders extends Fragment {

    ArrayList<LeaderModel> leaderModel;
    private static final String TAG = "LearningLeaders";
    RecyclerView recyclerView;
    LearningLeadersAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        Log.d(TAG, "onCreateView: Layout Inflated");
        //Implementing recyclerview;
        recyclerView = view.findViewById(R.id.leader_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));

        adapter = new LearningLeadersAdapter(getActivity());

        leaderModel = new ArrayList<>();

        Log.d(TAG, "onCreateView: Adapter Set");

        // getting network Requests
        getPosts();

        return view;
    }

    private  void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "onCreateView: Retrofit Gotten");
        ApiRepo apiRepo = retrofit.create(ApiRepo.class);
        Call<ArrayList<LeaderModel>> call = apiRepo.getHours();
        Log.d(TAG, "onCreateView: Repo Created Gotten");
        call.enqueue(new Callback<ArrayList<LeaderModel>>() {
            @Override
            public void onResponse(Call<ArrayList<LeaderModel>> call, Response<ArrayList<LeaderModel>> response) {
                Log.d(TAG, "onCreateView: Data Gotten");

                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+ response.message());
                }

                ArrayList<LeaderModel> leaderModel = response.body();
                adapter.setData(leaderModel);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<LeaderModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());

            }
        });


    }
}