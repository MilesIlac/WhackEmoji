package com.milesilac.whackamolu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreRecViewAdapter extends RecyclerView.Adapter<ScoreRecViewAdapter.ViewHolder> {

    ArrayList<Score> allScores = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_scores,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.scoreName.setText(allScores.get(position).getName());
        holder.scoreNum.setText(String.valueOf(allScores.get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        return allScores.size();
    }

    public void setAllScores(ArrayList<Score> allScores) {
        this.allScores = allScores;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout scoreLayout;
        private TextView scoreName, scoreNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            scoreLayout = itemView.findViewById(R.id.scoreListParent);
            scoreName = itemView.findViewById(R.id.scoreName);
            scoreNum = itemView.findViewById(R.id.scoreNum);
        }
    }
}
