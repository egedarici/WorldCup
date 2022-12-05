package com.example.worldcup;

/**
 * Team class includes the variables and functionalities of a team in the simulation.
 */
public class Team {
    private final String name;
    private int win;
    private int lose;
    private int draw;
    private int points;
    private int goalDiff;
    private int goalFor;
    private int goalAgainst;
    private double strength;

    private boolean isGoal;

    /**
     * @param name Team name.
     * @param win Win count. Starts from 0.
     * @param lose Lose count. Starts from 0.
     * @param draw Draw count. Starts from 0.
     * @param points Point count. Starts from 0.
     * @param goalDiff Gaol difference count. Starts from 0.
     * @param goalFor Goal for count. Starts from 0.
     * @param goalAgainst Goal against count. Starts from 0.
     */
    public Team(String name, int win, int lose, int draw, int points, int goalDiff, int goalFor, int goalAgainst) {
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.points = points;
        this.goalDiff = goalDiff;
        this.goalFor = goalFor;
        this.goalAgainst = goalAgainst;
    }

    public String getName() { return name; }

    public int getWin() { return win; }

    public int getLose() { return lose; }

    public int getDraw() {
        return draw;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalDif() {
        return goalDiff;
    }

    public int getGoalFor() {
        return goalFor;
    }

    public int getGoalAgainst() {
        return goalAgainst;
    }

    public void setStrength(double strength) { this.strength = strength; }

    /**
     * score() is called when a team shoots the ball. If the teams strength is bigger than the chance, it's a goal. Else, it's a miss.
     */
    public void score() {
        double chance = Math.random();
        if (chance <= strength) {
            System.out.println("+++ " + name + " SIUUUU!!!");
            isGoal = true;
        } else {
            System.out.println(name + " missed the goal.");
            isGoal = false;
        }
    }

    /**
     * @ensures increment goalFor by 1, goalDiff by 1, points by 3 if it's a goal.
     * @return true if goal, false if miss
     */
    public boolean isGoal() {
        if (isGoal == true) {
            goalFor++;
            goalDiff++;
            points++;
            points++;
            points++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @ensures increment goalAgainst by 1, decrement goalDiff by 1 if it's a goal against the team. No deduction from points.
     */
    public void concede() {
        goalAgainst++;
        goalDiff--;
    }

    /**
     * @ensures increment win by 1 if team won.
     */
    public void win() { win++; }

    /**
     * @ensures increment lose by 1 if team lost.
     */
    public void lose() { lose++; }

    /**
     * @ensures increment draw by 1, points by 1 if it was a draw.
     */
    public void draw() {
        draw++;
        points++;
    }

    /**
     * @ensures clear the data of the team
     */
    public void clear() {
        goalFor = 0;
        goalAgainst = 0;
        goalDiff = 0;
        win = 0;
        lose = 0;
        draw = 0;
        points = 0;
    }
}
