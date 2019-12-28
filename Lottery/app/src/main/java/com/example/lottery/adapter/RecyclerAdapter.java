package com.example.lottery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lottery.R;
import com.example.lottery.model.User;
import java.util.List;
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<User> userList;
    private OnItemClickListener listener;
    private LayoutInflater inflater;

    public RecyclerAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rowlayout,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(userList!=null){
            User user=userList.get(position);
            holder.textNumber.setText(user.getNumber());
            holder.textAmount.setText(String.valueOf(user.getAmount()));
        }   else {
            Toast.makeText(context, "Value Null", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }

    //Interface OnItemClickListener
    public interface OnItemClickListener{
        void OnItemClick(User user);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView textNumber;
         TextView textAmount;
         ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textNumber=itemView.findViewById(R.id.txt_number);
            textAmount=itemView.findViewById(R.id.txt_amount);
            img=itemView.findViewById(R.id.txt_img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i=getAdapterPosition();
                    if (listener!= null && i!= RecyclerView.NO_POSITION){
                        listener.OnItemClick(userList.get(i));
                    }
                }
            });

        }
    }
}
