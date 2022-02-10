package genspark.assignments.section2;

import genspark.assignments.Assignment;

public class FizzBuzzBaz implements Assignment {
    public String solution(int fuzzy) {
        // ↓↓↓↓ your code goes here ↓↓↓↓
        if (Math.abs(fuzzy % 3) == 0 && Math.abs(fuzzy % 5) == 0) return "fizz buzz baz";
        if (Math.abs(fuzzy % 3) == 0) return "fizz";
        if (Math.abs(fuzzy % 5) == 0) return "buzz";

        return "Fizzled";
    }
}
