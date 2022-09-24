package kg.geektech.game.general;

import kg.geektech.game.players.*;

import java.awt.*;
import java.util.Random;

public class RPG_Game {
    private static int roundNumber;
    public static Random random = new Random();

    public static void StartGame() {
        Boss boss = new Boss(1000, 50, "Tanos");
        Warrior warrior = new Warrior(270, 10, "Nurbol");
        Medic doc = new Medic(250, 5, 15, "Kayrat");
        Magic magic = new Magic(260, 20, "Ilyas");
        Berserk berserk = new Berserk(280, 15, "Aziz");
        Medic assistant = new Medic(290, 10, 5, "Abdulaziz");
        Hero[] heroes = {warrior, doc, magic, berserk, assistant};

        printStatics(boss, heroes);
        while (!isGameFinished(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    public static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence(heroes);
        boss.attack(heroes);
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getAbility() != boss.getDefence() && heroes[i].getHealth() > 0)
                heroes[i].attack(boss);
            heroes[i].applySuperPower(boss, heroes);
        }
        printStatics(boss, heroes);
    }

    private static void printStatics(Boss boss, Hero[] heroes) {
        System.out.println("ROUND " + roundNumber + " -------------");
        System.out.println(boss);
        for (int i = 0; i < heroes.length; i++) {
            System.out.println(heroes[i]);
        }
    }

    private static boolean isGameFinished(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
}
/*
часть урона полученного от босса Magic должен увеличивать атаку каждого героя
после каждого раунда на n-ное количество
Warrior каждый раз при атаке критует. Т.е. каждая атака умножается на случайное
число от 2 до 4.*/
