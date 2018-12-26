package ru.breev.se.Marathon;

import ru.breev.se.Marathon.Competitor.Team;
import ru.breev.se.Marathon.Obstacle.Course;

public class Main {
    static void start(Team team) {
        Course c = new Course();
        c.doIt(team);
        team.showResults();
    }

    public static void main(String[] args) {

        Team team = new Team("team1", "Petr", "Bobik", "Murzik");
        Team team2 = new Team("team2", "Sidor", "Sharik", "Barsik");
        Team team3 = new Team("team3", "Ivan", "Mukhtar", "Vasily");
        start(team);
        start(team2);
        start(team3);
    }
}
