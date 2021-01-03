package com.example.musicapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.musicapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FolderFragment extends Fragment {


    public FolderFragment() {
        // Required empty public constructor
    }

    private static class SingletonHelper {
        private static final FolderFragment INSTANCE = new FolderFragment();
    }

    public static FolderFragment getInstance() {
        return FolderFragment.SingletonHelper.INSTANCE;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_folder, container, false);
    }

}