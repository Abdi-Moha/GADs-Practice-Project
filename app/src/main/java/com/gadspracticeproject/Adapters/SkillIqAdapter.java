package com.gadspracticeproject.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gadspracticeproject.Models.LeaderModel;
import com.gadspracticeproject.Models.SkillIqModel;
import com.gadspracticeproject.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SkillIqAdapter extends RecyclerView.Adapter<SkillIqAdapter.ViewHolder> {
    private static final String TAG = "SkillIqAdapter";

    private List<SkillIqModel> skillIqModels;
    private Context mContext;

    public void setData(List<SkillIqModel> skillIqModels) {
        this.skillIqModels = skillIqModels;
    }

    public SkillIqAdapter(Context context) {
        this.mContext = context;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: created");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skill_iq_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SkillIqModel skillIqModel = skillIqModels.get(position);
        Log.d(TAG, "onBindViewHolder: Setting Items");
        Glide.with(mContext).asBitmap().load(skillIqModel.getLeaderImage()).into(holder.leaderImage);

        holder.leader.setText(skillIqModel.getLeaderName());
        String hours = skillIqModel.getIqScore() + " skill IQ Score, " + skillIqModel.getLeaderLocation();
        holder.hours.setText(hours);
    }

    @Override
    public int getItemCount() {
        return skillIqModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView leaderImage;
        TextView leader, hours;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            leaderImage = itemView.findViewById(R.id.leader_image);
            leader = itemView.findViewById(R.id.leader);
            hours = itemView.findViewById(R.id.learning_hrs);
            leaderImage = itemView.findViewById(R.id.leader_image);
        }
    }
}
