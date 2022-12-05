package com.example.worldcup;

public class Scheduler {

    /**
     * Team array to store Team data.
     */
    static Team[] teams;

    /**
     * Team array to store the data of the 2 teams playing the first match in the round.
     */
    static Team[] roundFirstTeams = new Team[2];

    /**
     * Team array to store the data of the 2 teams playing the second match in the round.
     */
    static Team[] roundSecondTeams = new Team[2];

    /**
     * Constructor for class Scheduler.
     * @param teams
     */
    public Scheduler(Team[] teams) {
        this.teams = teams;
    }

    /**
     * createSchedule():
     * @ensures schedules between teams for each round using Round Robin scheduling and calls the play() function each round to start the football simulation.
     * @param teams
     * @throws InterruptedException
     */
    public void createSchedule(Team[] teams) throws InterruptedException {
        int numOfTeams = teams.length;
        Team[] evenTeams;
        int k;
        if (numOfTeams % 2 == 0) {
            evenTeams = new Team[numOfTeams-1];
            for(k = 0; k < numOfTeams-1; k++)
                evenTeams[k] = (teams[k+1]);
        } else {
            evenTeams = new Team[numOfTeams];
            for(k = 0;k < numOfTeams-1; k++)
                evenTeams[k] = (teams[k+1]);
            evenTeams[numOfTeams-1] = null;
        }
        int teamsSize = evenTeams.length;
        int total = ((teamsSize+1) - 1); //Rounds needed to finish.
        int halfSize = (teamsSize+1)/ 2;
        int count = 0;
        for (int round = total-1; round >= 0; round--)  {
            System.out.println("round " + (++count));
            int teamIdx = round % teamsSize;
            roundFirstTeams[0] = teams[0];
            roundFirstTeams[1] = evenTeams[teamIdx];
            for (int i = 1; i < halfSize; i++) {
                int firstTeam = (round + i) % teamsSize;
                int secondTeam = (round  + teamsSize - i) % teamsSize;
                roundSecondTeams[0] = evenTeams[firstTeam];
                roundSecondTeams[1] = evenTeams[secondTeam];
            }
            System.out.println();
            play(); //The football simulation starts
        }
    }

    /**
     * play():
     * shotLimit: The amount of chances to shoot in a match. If increased, more shots are taken and more goal chances are possible.
     * chance: A random number to decide the shooting possibility. If chance is bigger than 50%, score() is called which is used to shoot the ball. Accordingly, shotLimit is increased.
     * isGoal(): If it's a goal, a goalFor is added the team
     * concede(): a goalAgainst is added to the opposite team.
     * Winner is decided by comparing the goals for 1st team and 2nd team. Winner Team calls win(), loser calls lose(),
     * If draw, draw() is called by both teams.
     * @ensures each team gets an equal chance to shoot. If they miss, a counter attack opportunity is possible and the opposite team gets to shoot.
     * @ensures each round is played.
     * @throws InterruptedException
     */
    public static void play() throws InterruptedException {
        System.out.println("Playing!");
        int shotLimit = 0;
        int goalsFirstTeam = 0;
        int goalsSecondTeam = 0;
        while (shotLimit <= 2) {
            double chance = Math.random();
            if (chance >= 0.5) {
                roundFirstTeams[0].score();
                shotLimit++;
                if (roundFirstTeams[0].isGoal()) {
                    goalsFirstTeam++;
                    roundFirstTeams[1].concede();
                } else {
                    System.out.println("Counter attack opportunity?!");
                    roundFirstTeams[1].score();
                    shotLimit++;
                    if (roundFirstTeams[1].isGoal()) {
                        goalsSecondTeam++;
                        roundFirstTeams[0].concede();
                    }
                }
            } else {
                roundFirstTeams[1].score();
                shotLimit++;
                if (roundFirstTeams[1].isGoal()) {
                    goalsSecondTeam++;
                    roundFirstTeams[0].concede();
                } else {
                    System.out.println("Counter attack opportunity?!");
                    roundFirstTeams[0].score();
                    shotLimit++;
                    if (roundFirstTeams[0].isGoal()) {
                        goalsFirstTeam++;
                        roundFirstTeams[1].concede();
                    }
                }
            }
        }

        System.out.println("The game is over!.");
        System.out.println(roundFirstTeams[0].getName() + " " + goalsFirstTeam + " - " + goalsSecondTeam +  " " + roundFirstTeams[1].getName());
        if (goalsFirstTeam > goalsSecondTeam) {
            roundFirstTeams[0].win();
            roundFirstTeams[1].lose();
            System.out.println("Winner of the game is: " + roundFirstTeams[0].getName());
        }
        if (goalsSecondTeam > goalsFirstTeam) {
            roundFirstTeams[1].win();
            roundFirstTeams[0].lose();
            System.out.println("Winner of the game is: " + roundFirstTeams[1].getName());
        }
        if (goalsFirstTeam == goalsSecondTeam) {
            roundFirstTeams[0].draw();
            roundFirstTeams[1].draw();
            System.out.println("DRAW. No Winner.");
        }

        shotLimit = 0;
        goalsFirstTeam = 0;
        goalsSecondTeam = 0;
        while (shotLimit <= 2) {
            double chance = Math.random();
            if (chance >= 0.5) {
                roundSecondTeams[0].score();
                shotLimit++;
                if (roundSecondTeams[0].isGoal()) {
                    goalsFirstTeam++;
                    roundSecondTeams[1].concede();
                } else {
                    System.out.println("Counter attack opportunity?!");
                    roundSecondTeams[1].score();
                    shotLimit++;
                    if (roundSecondTeams[1].isGoal()) {
                        goalsSecondTeam++;
                        roundSecondTeams[0].concede();
                    }
                }
            } else {
                roundSecondTeams[1].score();
                shotLimit++;
                if (roundSecondTeams[1].isGoal()) {
                    goalsSecondTeam++;
                    roundSecondTeams[0].concede();
                } else {
                    System.out.println("Counter attack opportunity?!");
                    roundSecondTeams[0].score();
                    shotLimit++;
                    if (roundSecondTeams[0].isGoal()) {
                        goalsFirstTeam++;
                        roundSecondTeams[1].concede();
                    }
                }
            }
        }

        System.out.println("The game is over!.");
        System.out.println(roundSecondTeams[0].getName() + " " + goalsFirstTeam + " - " + goalsSecondTeam + " " + roundSecondTeams[1].getName());
        if (goalsFirstTeam > goalsSecondTeam) {
            roundSecondTeams[0].win();
            roundSecondTeams[1].lose();
            System.out.println("Winner of the game is: " + roundSecondTeams[0].getName());
            System.out.println();
        }
        if (goalsSecondTeam > goalsFirstTeam) {
            roundSecondTeams[1].win();
            roundSecondTeams[0].lose();
            System.out.println("Winner of the game is: " + roundSecondTeams[1].getName());
            System.out.println();
        }
        if (goalsFirstTeam == goalsSecondTeam) {
            roundSecondTeams[0].draw();
            roundSecondTeams[1].draw();
            System.out.println("DRAW. No Winner.");
            System.out.println();
        }
    }

    /**
     * @return a Team array containing the teams
     */
    public static Team[] getTeams() {
        return teams;
    }
}

