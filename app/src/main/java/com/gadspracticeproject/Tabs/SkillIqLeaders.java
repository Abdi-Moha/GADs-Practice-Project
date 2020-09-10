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

import com.gadspracticeproject.Adapters.LearningLeadersAdapter;
import com.gadspracticeproject.Adapters.SkillIqAdapter;
import com.gadspracticeproject.ApiRepo;
import com.gadspracticeproject.Models.LeaderModel;
import com.gadspracticeproject.Models.SkillIqModel;
import com.gadspracticeproject.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillIqLeaders extends Fragment {

    ArrayList<SkillIqModel> skillIqModels;
    private static final String TAG = "LearningLeaders";
    RecyclerView recyclerView;
    SkillIqAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);

        Log.d(TAG, "onCreateView: Layout Inflated");
        //Implementing recyclerview;
        recyclerView = view.findViewById(R.id.skilIq_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));

        adapter = new SkillIqAdapter(getActivity());

        skillIqModels = new ArrayList<>();

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
        Call<ArrayList<SkillIqModel>> call = apiRepo.getIq();
        Log.d(TAG, "onCreateView: Repo Created Gotten");
        call.enqueue(new Callback<ArrayList<SkillIqModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SkillIqModel>> call, Response<ArrayList<SkillIqModel>> response) {
                Log.d(TAG, "onCreateView: Data Gotten");

                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+ response.message());
                }

                ArrayList<SkillIqModel> skillIqModels = response.body();
                adapter.setData(skillIqModels);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<SkillIqModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());

            }
        });


    }
}