package genspark.assignments.section2;

import genspark.assignments.Assignment;

public class ReverseAnInteger implements Assignment {
    public static String solution(int number) {
        // ↓↓↓↓ your code goes here ↓↓↓↓
        int reverse = 0;
        int remainder = 0;

        while (number > 0)  {
            reverse *= 10;
            remainder = number % 10;
            number = (number - remainder) / 10;
            reverse += remainder;
        }

        return String.valueOf(reverse);
    }

}
