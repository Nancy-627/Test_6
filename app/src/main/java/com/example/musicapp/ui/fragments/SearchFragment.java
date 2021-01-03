package com.example.musicapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.R;
import com.example.musicapp.adapter.SearchCategoryAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends Fragment {



        @BindView(R.id.vp_search_category)
        ViewPager vpSearchCategory;

        @BindView(R.id.tl_search_category)
        TabLayout tbSearchCategory;

        public SearchFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_search, container, false);
            ButterKnife.bind(this, view);

            setup();
            return view;
        }

        private void setup() {
            vpSearchCategory.setOffscreenPageLimit(3);
            vpSearchCategory.setAdapter(new SearchCategoryAdapter(getFragmentManager()));

        }
    }