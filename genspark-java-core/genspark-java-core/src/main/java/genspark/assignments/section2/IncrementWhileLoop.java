package genspark.assignments.section2;

import genspark.assignments.Assignment;

public class IncrementWhileLoop implements Assignment {
    public String solution(int from, int to) {
        // ↓↓↓↓ your code goes here ↓↓↓↓
        String str = "";

        while (from <= to) {
            str = str.concat(String.valueOf(from));
            from++;
        }
        return str;
    }
}
