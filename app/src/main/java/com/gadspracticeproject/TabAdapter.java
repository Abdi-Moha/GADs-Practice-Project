package com.gadspracticeproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gadspracticeproject.Tabs.LearningLeaders;
import com.gadspracticeproject.Tabs.SkillIqLeaders;

class TabAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public TabAdapter(Context context, FragmentManager fragmentManager, int totalTabs){
        super(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.totalTabs = totalTabs;


    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LearningLeaders learningLeaders = new LearningLeaders();
                return learningLeaders;
            case 1:
                SkillIqLeaders skillIqLeaders = new SkillIqLeaders();
                return skillIqLeaders;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
