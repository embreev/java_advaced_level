package ru.breev.se.Marathon.Competitor;

import ru.breev.se.Marathon.api.Competitor;

import java.util.ArrayList;
import java.util.List;

public class Team {
    String name;
    final List<Competitor> members = new ArrayList<>();

    public Team(String nameTeam) {
        this.name = nameTeam;
        members.add(new Human("Noname"));
        members.add(new Dog("Noname"));
        members.add(new Cat("Noname"));
    }
    public Team(String nameTeam, String nameHuman, String nameDog, String nameCat) {
        this.name = nameTeam;
        members.add(new Human(nameHuman));
        members.add(new Dog(nameDog));
        members.add(new Cat(nameCat));
    }

    public void showResults() {
        System.out.println(members);
    }

}
