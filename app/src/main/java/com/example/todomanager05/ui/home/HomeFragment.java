package com.example.todomanager05.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomanager05.OnItemClickListener;
import com.example.todomanager05.R;
import com.example.todomanager05.databinding.FragmentHomeBinding;
import com.example.todomanager05.ui.create.TaskAdapter;
import com.example.todomanager05.ui.create.TaskModel;
import com.example.todomanager05.utils.App;
import com.example.todomanager05.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TaskModel model;
    TaskAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_home_to_createTaskFragment);
            }

        });

        initAdapter();
        alertDialog();
    }

    private ArrayList<TaskModel> getDateFromDateBase() {
        return (ArrayList<TaskModel>) App.getInstance().getDateBase().taskDao().getAll();
    }

    private void initAdapter() {
        adapter = new TaskAdapter(getDateFromDateBase());
        binding.taskRecycler.setAdapter(adapter);
    }
    private void alertDialog() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onLongClick(TaskModel model) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("???????????? ??????????????????!")
                        .setMessage("???????????? ???????????????!")
                        .setNegativeButton("No", null)
                        .setPositiveButton("????", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                App.getInstance().getDateBase().taskDao().delete(model);
                                adapter.delete(model);
                            }
                        });
                builder.show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}