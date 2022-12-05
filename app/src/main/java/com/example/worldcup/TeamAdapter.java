package com.example.worldcup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Adapter class for updating the Team constraints on the scoreboard.
 * Uses RecyclerView
 * Team data is designed to show as in team_layout_item.xml
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder> {

    private final Context context;
    private final ArrayList<Team> teams;

    public TeamAdapter(Context context, ArrayList<Team> teams) {
        this.context = context;
        this.teams = teams;
    }

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_layout_item, parent,false);

        return new TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, int position) {
        Team team = teams.get(position);
        holder.SetDetails(team);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    /**
     * All the data of Team objects are assigned to their constraints.
     */
    static class TeamHolder extends RecyclerView.ViewHolder{

        private final TextView txtName;
        private final TextView txtWin;
        private final TextView txtLose;
        private final TextView txtDraw;
        private final TextView txtPoints;
        private final TextView txtGoalDiff;
        private final TextView txtGoalFor;
        private final TextView txtGoalAgainst;
        public TeamHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtWin = itemView.findViewById(R.id.txtWin);
            txtLose = itemView.findViewById(R.id.txtLose);
            txtDraw = itemView.findViewById(R.id.txtDraw);
            txtGoalFor = itemView.findViewById(R.id.txtGoalFor);
            txtGoalAgainst = itemView.findViewById(R.id.txtGoalAgainst);
            txtGoalDiff = itemView.findViewById(R.id.txtGoalDif);
            txtPoints = itemView.findViewById(R.id.txtPoints);
        }

        /**
         * Sets the data of the constraints.
         * @param team is the team which it's data in the constraints will be updated as follows.
         */
        void SetDetails(Team team) {
            txtName.setText(team.getName());
            txtWin.setText(String.format(Locale.US, "W : %d", team.getWin()));
            txtLose.setText(String.format(Locale.US, "L : %d", team.getLose()));
            txtDraw.setText(String.format(Locale.US, "D : %d", team.getDraw()));
            txtGoalFor.setText(String.format(Locale.US, "GF : %d", team.getGoalFor()));
            txtGoalAgainst.setText(String.format(Locale.US, "GA : %d", team.getGoalAgainst()));
            txtGoalDiff.setText(String.format(Locale.US, "GD : %d", team.getGoalDif()));
            txtPoints.setText(String.format(Locale.US, "POINTS : %d", team.getPoints()));
        }
    }
}
