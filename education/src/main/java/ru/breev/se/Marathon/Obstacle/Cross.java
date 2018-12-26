package ru.breev.se.Marathon.Obstacle;

import ru.breev.se.Marathon.Obstacle.Obstacle;
import ru.breev.se.Marathon.api.Competitor;

public class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}