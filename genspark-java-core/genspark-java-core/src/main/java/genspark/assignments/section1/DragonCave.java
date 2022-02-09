package genspark.assignments.section1;

import java.util.Scanner;
import java.util.Random;

public class DragonCave {

    private static void encounterBadDragon() {
        System.out.println("It's dark and spooky...");
        System.out.println("A large dragon jump out in front of you! He opens his jaw add...");
        System.out.println("Gobbles you down in one bite!");
    }

    private static void encountersGoodDragon(){
        System.out.println("It's bright and cheerful");
        System.out.println("A friendly dragon greets you...");
        System.out.println("He offers you some cake a tour of his cave.");
    }

    static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("You are in a land full of dragons, In front of you");
        System.out.println("you see two caves. In one cave the dragon is friendly");
        System.out.println("and will share his treasure with you. The other dragon");
        System.out.println("is greedy and hungry and will eat you on site");
        System.out.println("Which cave will you go into? (1 or 2)");

        int option = sc.nextInt();
        option = option % 2;
        int random = new Random().nextInt(2);

        if (option == random) encounterBadDragon();
        else encounterBadDragon();

        System.out.println("You approach the cave...");
    }
}
