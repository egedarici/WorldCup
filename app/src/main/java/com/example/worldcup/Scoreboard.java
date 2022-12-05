package com.example.worldcup;

import static com.example.worldcup.Scheduler.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Scoreboard is the second activity of the application.
 * It shows the scores of each team in a descending order of points.
 */
public class Scoreboard extends AppCompatActivity {

    RecyclerView scoreboard;
    private TeamAdapter adapter;
    private ArrayList<Team> teamArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        scoreboard = findViewById(R.id.scoreboard);
        scoreboard.setLayoutManager(new LinearLayoutManager(this));
        teamArrayList = new ArrayList<>();
        adapter = new TeamAdapter(this, teamArrayList);
        scoreboard.setAdapter(adapter);
        scoreboard.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));

        System.out.println(Arrays.toString(getTeams()));
        setScoreboardData();
    }

    /**
     * Sets the data to the scoreboard constraint in a descending order.
     */
    private void setScoreboardData() {
        HashMap<Team, Integer> teamPoints = new HashMap<Team, Integer>();
        ArrayList<Integer> points = new ArrayList<>();
        for (int i = 0; i < teams.length; i++) {
            points.add(teams[i].getPoints());
            teamPoints.put(teams[i],teams[i].getPoints());
        }
        Collections.sort(points,Collections.reverseOrder());
        int i = 0;
        for (int r = 0; r < teams.length; r++) {
            for (Map.Entry<Team,Integer> entry: teamPoints.entrySet()) {
                System.out.println(entry.getValue());
                if (entry.getValue() == points.get(i) && !teamArrayList.contains(entry.getKey())) {
                    teamArrayList.add(entry.getKey());
                    System.out.println("added");
                }
            }
            i++;
        }
    }
}