package com.example.lottery.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lottery.R;
import com.example.lottery.adapter.RecyclerAdapter;
import com.example.lottery.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<User> itemList;
    private TextInputEditText betAomunt, betNumber,txt_Total;
    private TextInputLayout betnum;
    private Button Tripel, Hundred, Custom_btn, Reverse_btn, Add, Betting;
    private TextView text;
    String num;
    int amount = 0;
    int itemCount;
    String s = "Triple";
    int total = 0;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.rec_recyclerView);
        Tripel = root.findViewById(R.id.btn_triple);
        Hundred = root.findViewById(R.id.btn_hundred);
        Custom_btn = root.findViewById(R.id.btn_Custom_num);
        betAomunt = root.findViewById(R.id.edt_betAmount);
        Reverse_btn = root.findViewById(R.id.btn_Reverse_data);
        Add = root.findViewById(R.id.btn_add);
        betNumber = root.findViewById(R.id.edt_number);
        betnum = root.findViewById(R.id.bet_num);
        text = root.findViewById(R.id.txt_text);
        Betting = root.findViewById(R.id.btn_bet);
        betnum.setVisibility(View.INVISIBLE);
        betNumber.setVisibility(View.INVISIBLE);
        txt_Total = root.findViewById(R.id.txt_total_amount);
        itemList = new ArrayList<>();
        adapter = new RecyclerAdapter(getContext(), itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        Tripel.setOnClickListener(this);
        Hundred.setOnClickListener(this);
        Custom_btn.setOnClickListener(this);
        Reverse_btn.setOnClickListener(this);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(User user) {
                num = user.getNumber();
                FontBuilder(num);
            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ks = Integer.valueOf(betAomunt.getText().toString().trim());
                onWorkingProcess(ks);
                recyclerView.setAdapter(adapter);
            }
        });
        txt_Total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalculateTotalAmount().execute();
            }
        });
        Betting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  txt_Total.setText(String.valueOf(total));
            }
        });
        return root;
    }

    private void FontBuilder(String number) {
        User user = new User();
        AlertDialog.Builder builderFont = new AlertDialog.Builder(getContext());
        builderFont.setCancelable(false);
        LayoutInflater inflater = HomeFragment.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_amount, null);
        builderFont.setView(dialogView);
        builderFont.setTitle(number).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderFont.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderFont.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_triple:
                text.setText("Triple Number");
                betnum.setVisibility(View.INVISIBLE);
                betNumber.setVisibility(View.INVISIBLE);
                s = "Triple";
                break;
            case R.id.btn_hundred:
                text.setText("Hundred Number");
                betnum.setVisibility(View.INVISIBLE);
                betNumber.setVisibility(View.INVISIBLE);
                s = "Hundred";
                break;
            case R.id.btn_Custom_num:
                text.setText("Custom Number");
                betnum.setVisibility(View.VISIBLE);
                betNumber.setVisibility(View.VISIBLE);
                s = "Custom";
                break;
            case R.id.btn_Reverse_data:
                text.setText("Reverse Number");
                betnum.setVisibility(View.VISIBLE);
                betNumber.setVisibility(View.VISIBLE);
                s = "Reverse";
                break;
        }
    }

    private void onWorkingProcess(int money) {
        String num = betNumber.getText().toString().trim();
        if (s.equals("Triple")) {
            itemList.add(new User("000", money));
            itemList.add(new User("111", money));
            itemList.add(new User("222", money));
            itemList.add(new User("333", money));
            itemList.add(new User("444", money));
            itemList.add(new User("555", money));
            itemList.add(new User("666", money));
            itemList.add(new User("777", money));
            itemList.add(new User("888", money));
            itemList.add(new User("999", money));
        } else if (s.equals("Hundred")) {
            itemList.add(new User("000", money));
            itemList.add(new User("100", money));
            itemList.add(new User("200", money));
            itemList.add(new User("300", money));
            itemList.add(new User("400", money));
            itemList.add(new User("500", money));
            itemList.add(new User("600", money));
            itemList.add(new User("700", money));
            itemList.add(new User("800", money));
            itemList.add(new User("900", money));

        } else if (s.equals("Custom")) {
            itemList.add(new User(num, money));
        } else if (s.equals("Reverse")) {
            String[] b = null;

            String reverse[] = {num.substring(0, 1), num.substring(1, 2), num.substring(2)};
            if ((reverse[0] == reverse[1]) || (reverse[0] == reverse[2]) || (reverse[1] == reverse[2])) {
                b = new String[4];
            } else {
                b = new String[6];
            }

            int c = 0;
            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 2; j++) {

                    if (reverse[j + 1] != reverse[j]) {
                        String temp = reverse[j + 1];
                        reverse[j + 1] = reverse[j];
                        reverse[j] = temp;
                        String bs = reverse[0] + reverse[1] + reverse[2];
                        b[c] = bs;
                        c++;
                    } else {

                        continue;
                    }

                }
            }
            Arrays.sort(b);
            int length = b.length;
            length = removeDuplicateElements(b, length);
            //printing array elements
            for (int i = 0; i < length; i++) {
                itemList.add(new User(String.valueOf(b[i]), money));
            }
        } else {
            itemList.add(new User("000", money));
            itemList.add(new User("111", money));
            itemList.add(new User("222", money));
            itemList.add(new User("333", money));
            itemList.add(new User("444", money));
            itemList.add(new User("555", money));
            itemList.add(new User("666", money));
            itemList.add(new User("777", money));
            itemList.add(new User("888", money));
            itemList.add(new User("999", money));
        }
    }

    public int removeDuplicateElements(String arr[], int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        String[] temp = new String[n];
        int j = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                temp[j++] = arr[i];
            }
        }
        temp[j++] = arr[n - 1];
        // Changing original array
        for (int i = 0; i < j; i++) {
            arr[i] = temp[i];
        }
        return j;
    }

    class CalculateTotalAmount extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            //get Arraylist amount in calculate amount

            itemCount = adapter.getItemCount();
            Log.d("Item Count", String.valueOf(itemCount));
            for (int i = 0; i < itemCount; i++) {
                View view = recyclerView.getChildAt(i);
                if (view != null) {
                    TextView textView = view.findViewById(R.id.txt_amount);
                    amount = amount + Integer.parseInt(textView.getText().toString());
                    total = amount;
                }
            }
            amount =0;
            Log.d("Amount", String.valueOf(total));
          /*  new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    txt_Total.setText("Hello");
                }
            };*/
            return total;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            txt_Total.setText(String.valueOf(integer));
        }
    }
}