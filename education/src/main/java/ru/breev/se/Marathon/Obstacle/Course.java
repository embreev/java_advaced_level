package ru.breev.se.Marathon.Obstacle;

import ru.breev.se.Marathon.Competitor.Team;
import ru.breev.se.Marathon.api.Competitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Course {
    final Random random = new Random();
    final int distance = random.nextInt(150);
    final List<Obstacle> courses = new ArrayList<>();
    final int OBS_POINTS = 5;
    Obstacle [] course = new Obstacle[OBS_POINTS];
    Competitor[] competitors;

    public Course() {
        courses.add(new Cross(distance));
        courses.add(new Wall(distance));
        courses.add(new Water(distance));

        for (int i = 0; i < course.length; i++) {
            course[i] = courses.get(random.nextInt(courses.size()));
        }
    }

    public void doIt(Team team) {
        for (Competitor member: team.getMembers()) {

        }
//        for (Competitor competitor : competitors) {
//            for (Obstacle obstacle : course) {
//                obstacle.doIt(competitor);
//                if (!competitor.isOnDistance()) break;
//            }
//        }
    }
}
