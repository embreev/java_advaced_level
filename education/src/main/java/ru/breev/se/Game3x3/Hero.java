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
        if (health <= 0) {
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
        return name + " " + (health <= 0 ? "Герой мертвый" : health) + " " + damage;
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
            if (health <= 0) {
                return "Герой погиб и бить не может!";
            } else {
                hero.receiveDamage(damage);
            }
            return "Воин " + this.name + " нанес урон " + hero.name + " " + damage;
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
            if (health <= 0) {
                return "Герой погиб и бить не может!";
            } else {
                hero.receiveDamage(damage + criticalHit);
            }
            return "Убийца " + this.name + " нанес урон " + hero.name + " " + damage + " + критический " + criticalHit;
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
        return "Лекарь не может бить!";
    }

    @Override
    String healing(Hero hero) {
        hero.addHealth(addHeal);
        return "Лекарь " + this.name + " вылечил " + hero.name + " на " + addHeal;
    }
}


class Game {
    protected static ArrayList<Hero> team1 = new ArrayList<>();
    protected static ArrayList<Hero> team2 = new ArrayList<>();
    protected static final Random randomStep = new Random();
    protected static final Random randomHealing = new Random();
    protected static String status;
    protected static int quantityHeros = 3;
    protected static boolean flag = true;

    static int getQuantityHeros(ArrayList<Hero> team) {
        return team.size();
    }

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

    static void checkWinner() {
        int countTeam1 = 0;
        int countTeam2 = 0;

        for (Hero hero : team1) {
            if (hero.health <= 0) {
                countTeam1++;
            }
        }
        for (Hero hero : team2) {
            if (hero.health <= 0) {
                countTeam2++;
            }
        }

        if (countTeam1 == quantityHeros) {
            status = "=====================\nПобедила Команда 1";
            flag = false;
        }
        if (countTeam2 == quantityHeros) {
            status = "=====================\nПобедила Команда 2";
            flag = false;
        }
    }

    static void go() {
        // проходим по всем участникам команды
        for (int i = 0; i < quantityHeros; i++) {
            // рандомно выбираем кто будет первый ходить
            if (randomStep.nextInt(2) == 0) {
                // если персонаж не доктор, то он может ударить
                // если доктор, то он лечит
                if (team1.get(i) instanceof Doctor) {
                    status = team1.get(i).healing(team1.get(randomHealing.nextInt(2)));
                } else {
                    status = team1.get(i).hit(team2.get(i));
                }
            } else {
                if (team2.get(i) instanceof Doctor) {
                    status = team2.get(i).healing(team2.get(randomHealing.nextInt(2)));
                } else {
                    status = team2.get(i).hit(team1.get(i));
                }
            }
        }
    }

    public static void main(String[] args) {
        final GUI ui = new GUI();
        ui.createGUI();
    }
}