package com.example.todomanager05.ui.boarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todomanager05.MainActivity;
import com.example.todomanager05.R;
import com.example.todomanager05.databinding.FragmentOnBoardBinding;
import com.example.todomanager05.utils.Constants;


public class OnBoardFragment extends Fragment {
    FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPositionFromViewPagerAdapter();
    }

    private void getPositionFromViewPagerAdapter() {
        if (getArguments() != null) {
            int position = getArguments().getInt(Constants.FRAGMENT_POSITION);
            switch (position){
                case 0:
                    binding.titleTv.setText("Приветствую");
                    break;
                case 1:
                    binding.titleTv.setText("Второй экран");
                    break;
                case 2:
                    binding.titleTv.setText("Третий экран");
                    binding.startBtn.setVisibility(View.VISIBLE);
                    binding.startBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                     public void onClick(View view) {
                     SharedPreferences sharedPreferences =requireActivity().getSharedPreferences(Constants.IS_SHOW_FILE, Context.MODE_PRIVATE);
                     sharedPreferences.edit().putBoolean(Constants.IS_SHOW,true).apply();
                     startActivity(new Intent(requireContext(),MainActivity.class));
                        }
                    });
                    break;

            }
        }
    }
}