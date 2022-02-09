package genspark.assignments.project1;

import genspark.assignments.Assignment;

import java.util.Scanner;
import java.util.Random;

public class DragonCave implements Assignment {

    private static void encounterBadDragon() {
        System.out.println("It is dark and spooky...");
        System.out.println("A large dragon jump out in front of you! He opens his jaw add...");
        System.out.println("Gobbles you down in one bite!");
    }

    private static void encounterGoodDragon() {
        System.out.println("It is bright and welcoming");
        System.out.println("A friendly dragon greets you...");
        System.out.println("He offers you some cake a tour of his treasures.");
    }

    private static int generateOneOrTwo() {
        return new Random().nextInt(2) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("You are in a land full of dragons, In front of you");
        System.out.println("you see two caves. In one cave the dragon is friendly");
        System.out.println("and will share his treasure with you. The other dragon");
        System.out.println("is greedy and hungry and will eat you on sight");
        System.out.println("Which cave will you go into? (1 or 2)");

        int userInput = 1;
        do {
            if(userInput != 1 && userInput != 2) {
                System.out.println("Please select again:");
            }
            userInput = sc.nextInt();

        } while (userInput != 1 && userInput != 2);

        int number = generateOneOrTwo();

        System.out.println("You approach the cave...");

        if (userInput == number) encounterBadDragon();
        else encounterGoodDragon();
    }
}
