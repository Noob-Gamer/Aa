package com.example.lottery.ui.mybet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lottery.R;

public class MyBetFragment extends Fragment {
    private MyBetViewModel myBetViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myBetViewModel = ViewModelProviders.of(this).get(MyBetViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mybet, container, false);
        final TextView textView = root.findViewById(R.id.text_mybet);
        myBetViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
