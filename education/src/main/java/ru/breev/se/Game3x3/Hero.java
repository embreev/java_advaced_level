package ru.breev.se.Game3x3;

import java.util.ArrayList;
import java.util.Random;

/*
Абстрактный класс родитель для создания героев
*/
abstract class Hero {

    // здоровье героя
    protected int health;
    // начальное здоровье героя
    protected int defaultHealth;
    // имя героя
    protected String name;
    // сколько урона может нанести герой
    protected int damage;
    // сколько здоровья может восстановть герой
    protected int addHeal;

    public Hero(int health, String name, int damage, int addHeal) {
        this.health = health;
        defaultHealth = health;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
    }

    // метод нанесения удара
    abstract String hit(Hero hero);

    // метод лечения
    abstract String healing(Hero hero);

    // метод получения удара
    String receiveDamage(int damage) {
        if(health < 0) {
            return "Герой уже мертвый!";
        } else {
            health -= damage;
        }
        return null;
    }

    public int getHealth() {
        return health;
    }

    // метод для добавления здоровья
    void addHealth(int health) {
        if (this.health < defaultHealth) {
            this.health = (this.health + health > defaultHealth) ? defaultHealth : this.health + health;
        }
    }

    String info() {
        return name + " " + (health < 0 ? "Герой мертвый" : health) + " " + damage;
    }
}

/*
Класс воин для создания конкретной реализации героя
*/
class Warrior extends Hero {

    public Warrior(int health, String type, int damage, int addHeal) {
        super(health, type, damage, addHeal);
    }

    @Override
    String hit(Hero hero) {
        // если герой не он сам, он может ударить
        if (hero != this) {
            // если у герой которого бьют жив, его можно ударить
            if(health < 0) {
                return "Герой погиб и бить не может!";
            } else {
                hero.receiveDamage(damage);
            }
            return this.name + " нанес урон " + hero.name + " " + damage;
        }
        return null;
    }

    @Override
    String healing(Hero hero) {
        return "Войны не умеют лечить!";
    }
}

/*
Класс убийца для создания конкретной реализации героя
*/
class Assasin extends Hero {

    int criticalHit;
    Random random = new Random();

    public Assasin(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
        this.criticalHit = random.nextInt(20);
    }

    @Override
    String hit(Hero hero) {
        // если герой не он сам, он может ударить
        if (hero != this) {
            // если у герой которого бьют жив, его можно ударить
            if(health < 0) {
                return "Герой погиб и бить не может!";
            } else {
                hero.receiveDamage(damage + criticalHit);
            }
            return this.name + " нанес урон " + hero.name + " " + damage + " + критический " + criticalHit;
        }
        return null;
    }

    @Override
    String healing(Hero hero) {
        return "Убийцы не умеют лечить!";
    }
}

/*
Класс доктор для создания конкретной реализации героя
*/
class Doctor extends Hero {

    public Doctor(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
    }

    @Override
    String hit(Hero hero) {
        return "Доктор не может бить!";
    }

    @Override
    String healing(Hero hero) {
        hero.addHealth(addHeal);
        return this.name + " вылечил " + hero.name + " на " + addHeal;
    }
}


class Game {
    final static ArrayList<Hero> team1 = new ArrayList<>();
    final static ArrayList<Hero> team2 = new ArrayList<>();
    static boolean flag = false;
    static final Random randomStep = new Random();
    static final Random randomHealing = new Random();

    static void addHeroTeam1(String classHero) {
        switch (classHero) {
            case "Warrior":
                team1.add(new Warrior(250, "Тигрил", 50, 0));
                break;
            case "Assasin":
                team1.add(new Assasin(150, "Акали", 70, 0));
                break;
            case "Doctor":
                team1.add(new Doctor(50, "Жанна", 0, 30));
                break;
        }
    }

    static void addHeroTeam2(String classHero) {
        switch (classHero) {
            case "Warrior":
                team2.add(new Warrior(290, "Минотавр", 60, 0));
                break;
            case "Assasin":
                team2.add(new Assasin(160, "Джинкс", 90, 0));
                break;
            case "Doctor":
                team2.add(new Doctor(50, "Зои", 0, 35));
                break;
        }
    }

    static void go() {
//        while (!flag){
        for (int j = 0; j < 10; j++) {
            // проходим по всем участникам команды
            for (int i = 0; i < team1.size(); i++) {
                // рандомно выбираем кто будет первый ходить
                if(randomStep.nextInt(2) == 0) {
                    // если персонаж не доктор, то он может ударить
                    // если доктор, то он лечит
                    if(team1.get(i) instanceof Doctor) {
                        team1.get(i).healing(team1.get(randomHealing.nextInt(2)));
                    } else {
                        team1.get(i).hit(team2.get(i));
                    }
                } else {
                    if(team2.get(i) instanceof Doctor) {
                        team2.get(i).healing(team2.get(randomHealing.nextInt(2)));
                    } else {
                        team2.get(i).hit(team1.get(i));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        final GUI ui = new GUI();
        ui.createGUI();


//        for (int j = 0; j < round; j++) {
//            // проходим по всем участникам команды
//            for (int i = 0; i < team1.length; i++) {
//                // рандомно выбираем кто будет первый ходить
//                if(randomStep.nextInt(2) == 0) {
//                    // если персонаж не доктор, то он может удрарить
//                    // если доктор, то он лечит
//                    if(team1[i] instanceof Doctor) {
//                        team1[i].healing(team1[randomHealing.nextInt(2)]);
//                    } else {
//                        team1[i].hit(team2[i]);
//                    }
//                } else {
//                    if(team2[i] instanceof Doctor) {
//                        team2[i].healing(team2[randomHealing.nextInt(2)]);
//                    } else {
//                        team2[i].hit(team1[i]);
//                    }
//                }
//            }
//        }
//
//        System.out.println("===============");
//
//        for (Hero t1: team1) {
//            t1.info();
//        }
//
//        System.out.println("---------------");
//
//        for (Hero t2: team2) {
//            t2.info();
//        }
    }
}