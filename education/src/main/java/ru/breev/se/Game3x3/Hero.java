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
    abstract void hit(Hero hero);

    // метод лечения
    abstract void healing(Hero hero);

    // метод получения удара
    void receiveDamage(int damage) {
        if(health < 0) {
            System.out.println("Герой уже мертвый!");
        } else {
            health -= damage;
        }

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

    void info() {
        System.out.println(name + " " + (health < 0 ? "Герой мертвый" : health) + " " + damage);
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
    void hit(Hero hero) {
        // если герой не он сам, он может ударить
        if (hero != this) {
            // если у герой которого бьют жив, его можно ударить
            if(health < 0) {
                System.out.println("Герой погиб и бить не может!");
            } else {
                hero.receiveDamage(damage);
            }
            System.out.println(this.name + " нанес урон " + hero.name + " " + damage);
        }
    }

    @Override
    void healing(Hero hero) {
        System.out.println("Войны не умеют лечить!");
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
    void hit(Hero hero) {
        // если герой не он сам, он может ударить
        if (hero != this) {
            // если у герой которого бьют жив, его можно ударить
            if(health < 0) {
                System.out.println("Герой погиб и бить не может!");
            } else {
                hero.receiveDamage(damage + criticalHit);
            }
            System.out.println(this.name + " нанес урон " + hero.name + " " + damage + " + критический " + criticalHit);
        }
    }

    @Override
    void healing(Hero hero) {
        System.out.println("Убийцы не умеют лечить!");
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
    void hit(Hero hero) {
        System.out.println("Доктор не может бить!");
    }

    @Override
    void healing(Hero hero) {
        hero.addHealth(addHeal);
        System.out.println(this.name + " вылечил " + hero.name + " на " + addHeal);
    }
}


class Game {
    public static void main(String[] args) {

        Random randomStep = new Random();
        Random randomHealing = new Random();
        // количество раундов
        int round = 50;

//        // создаюстся две команды
//        Hero[] team1 = new Hero[]{new Warrior(250, "Тигрил", 50, 0)
//                , new Assasin(150, "Акали", 70, 0)
//                , new Doctor(50, "Жанна", 0, 30)};
//
//
//        Hero[] team2 = new Hero[]{new Warrior(290, "Минотавр", 60, 0)
//                , new Assasin(160, "Джинкс", 90, 0)
//                , new Doctor(50, "Зои", 0, 35)};

        ArrayList<Hero> team1 = new ArrayList<>();
        ArrayList<Hero> team2 = new ArrayList<>();

        UI window = new UI();


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