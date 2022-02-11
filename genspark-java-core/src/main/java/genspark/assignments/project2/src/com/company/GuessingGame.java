package com.company;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private static int limit = 6;

    private static String askName() {
        Scanner scanner = new Scanner(System.in);
        String name = "";

        while (name.isEmpty()) {
            System.out.println("Hello! What is your name?");
            name = scanner.nextLine().strip();
        }
        return name;
    }

    private static void askQuestion(String player) {
        System.out.println(String.format("Well %s, I am thinking of a number between 1 and 20.", player));
        System.out.println("Take a guess.");
    }

    private static void startGuessing(String player) {
        int tries = 0;
        int number = new Random().nextInt(20) + 1;
        Scanner scanner = new Scanner(System.in);

        while(tries <= limit) {
            int guess = scanner.nextInt();
            tries++;
            if (guess > number) {
                System.out.println("Your guess is too high.");
                System.out.println("Take a guess");

            } else if (guess < number) {
                System.out.println("Your guess is too low");
                System.out.println("Take a guess");

            } else {
                System.out.println(String.format("Good job, %s! You guessed my number in %s %s!", player, String.valueOf(tries), (tries > 1) ? "guesses" : "guess"));
                break;
            }

            if(tries == limit) {
                System.out.println("Sorry you ran of tries.");
                break;
            }

        }
    }

    private static boolean keepPlaying() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Would you like to play again? (y or n)");
            String response = scanner.nextLine();

            if (response.strip().equalsIgnoreCase("y")) return true;

            if (response.strip().equalsIgnoreCase("n")) return false;
        }
    }
    public static void main(String[] args) {
	// write your code here
        String name = askName();
        askQuestion(name);
        startGuessing(name);

        while (keepPlaying()) {
            askQuestion(name);
            startGuessing(name);
        }

    }
}
