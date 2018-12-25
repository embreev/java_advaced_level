package ru.breev.se.Marathon;

import ru.breev.se.Marathon.Competitor.Team;
import ru.breev.se.Marathon.Obstacle.*;
import ru.breev.se.Marathon.Competitor.Cat;
import ru.breev.se.Marathon.Competitor.Dog;
import ru.breev.se.Marathon.Competitor.Human;
import ru.breev.se.Marathon.api.Competitor;

public class Main {
    public static void main(String[] args) {
        Course c = new Course();
        Team team = new Team("team1");
        c.doIt(team);
        team.showResults();

//        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
//
//        Obstacle[] course = {new Cross(80), new Wall(2), new Wall(1), new Cross(120)};
//
//        for (Competitor c : competitors) {
//            for (Obstacle o : course) {
//                o.doIt(c);
//                if (!c.isOnDistance()) break;
//            }
//        }
//
//        for (Competitor c : competitors) {
//            c.info();
//        }
    }
}
