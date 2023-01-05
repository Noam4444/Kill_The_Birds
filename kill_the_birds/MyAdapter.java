package com.example.kill_the_birds;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
//הדף שאחראי לחבר בין השרת לבסיס נתונים
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Players>list;

    public MyAdapter(Context context, ArrayList<Players> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Players players=list.get(position);
        holder.name.setText(String.valueOf(players.getName()));
        holder.score.setText((String.valueOf(players.getScore())));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name,score;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvname);
            score=itemView.findViewById(R.id.tvscoreItem);
        }
    }

}
