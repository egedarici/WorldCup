package com.example.worldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * WorldCup is a football simulation between 4 teams that shows the scoreboard at the end.
 * It's a 2 activity application.
 * MainActivity is for creating the teams and starting the simulation.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Play button:
     * Assigns the user input team names to Team objects and user input strengths to double objects and checks if the strengths are in the range of 0.1 - 1.0.
     * Calls the schedule create function: createSchedule(), and feeds the teams array to the function
     * @requires the user input blanks to be filled in and the strength inputs to be between 0.1 and 1.0. If not, the app crashes.
     */
    Button play;

    /**
     * Scoreboard button:
     * Lists the teams in descending order by their points. Shows the Win, Lose, Draw, Goal For, Goal Against, Gaol Difference, Points information for each team.
     */
    Button scoreboard;

    /**
     * 4 team objects and 4 double objects created, since the app is only for 4 teams yet.
     */
    Team team1, team2, team3, team4;
    double strength1, strength2, strength3, strength4;

    /**
     * The user input team names and strengths
     */
    EditText teamName1, teamName2, teamName3, teamName4;
    EditText teamStrength1, teamStrength2, teamStrength3, teamStrength4;


    /**
     * Assigns the constraints in the xml file activity_main.xml to objects for future use.
     * Checks if the user filled the blanks correctly.
     * Clears the data for each team before starting.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamName1 = findViewById(R.id.teamName1);
        teamName2 = findViewById(R.id.teamName2);
        teamName3 = findViewById(R.id.teamName3);
        teamName4 = findViewById(R.id.teamName4);
        teamStrength1 = findViewById(R.id.teamStrength1);
        teamStrength2 = findViewById(R.id.teamStrength2);
        teamStrength3 = findViewById(R.id.teamStrength3);
        teamStrength4 = findViewById(R.id.teamStrength4);

        play = findViewById(R.id.button_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws NullPointerException {

                team1 = (!isEmpty(teamName1)) ? new Team(teamName1.getText().toString(),0,0,0,0,0,0,0) : null;
                team2 = (!isEmpty(teamName2)) ? new Team(teamName2.getText().toString(),0,0,0,0,0,0,0) : null;
                team3 = (!isEmpty(teamName3)) ? new Team(teamName3.getText().toString(),0,0,0,0,0,0,0) : null;
                team4 = (!isEmpty(teamName4)) ? new Team(teamName4.getText().toString(),0,0,0,0,0,0,0) : null;

                strength1 = (!isEmpty(teamStrength1) && inRange(teamStrength1)) ? Double.parseDouble(teamStrength1.getText().toString()) : null;
                strength2 = (!isEmpty(teamStrength2) && inRange(teamStrength2)) ? Double.parseDouble(teamStrength2.getText().toString()) : null;
                strength3 = (!isEmpty(teamStrength3) && inRange(teamStrength3)) ? Double.parseDouble(teamStrength3.getText().toString()) : null;
                strength4 = (!isEmpty(teamStrength4) && inRange(teamStrength4)) ? Double.parseDouble(teamStrength4.getText().toString()) : null;

                Team[] teamsFinal = {team1,team2,team3,team4};

                team1.setStrength(strength1);
                team2.setStrength(strength2);
                team3.setStrength(strength3);
                team4.setStrength(strength4);

                team1.clear();
                team2.clear();
                team3.clear();
                team4.clear();

                Scheduler scheduler = new Scheduler(teamsFinal);
                try {
                    scheduler.createSchedule(teamsFinal);
                    showToast("Games ended! Check the scoreboard.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        scoreboard = findViewById(R.id.button_scoreboard);
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Scoreboard.class);
                startActivity(i);
            }
        });
    }

    /**
     * Toast pop-up message generator for alerts.
     * @requires a String text
     * @param text
     */
    public void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Checks if the input is empty or not. If empty, alerts the user.
     * @param text
     * @return true is empty, false if not empty.
     */
    public boolean isEmpty(EditText text) {
        if (text.getText().toString().equals("")) {
            showToast("Please fill in the blanks.");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the strength input is in the range of 0.1 - 1.0. If not, alerts the user.
     * @param number
     * @return true if in range, false if not in range.
     */
    public boolean inRange(EditText number) {
        Double strength = Double.parseDouble(number.getText().toString());
        if (strength < 0.1 || strength > 1.0) {
            showToast("Please give a double between the range.");
            return false;
        } else {
            return true;
        }
    }
}