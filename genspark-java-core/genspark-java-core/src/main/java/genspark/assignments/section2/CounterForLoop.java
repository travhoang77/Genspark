package genspark.assignments.section2;

import genspark.assignments.Assignment;

public class CounterForLoop implements Assignment {
    public String solution(int count) {
        // ↓↓↓↓ your code goes here ↓↓↓↓
        String result = new String("");
        for (int i = count; i >= 0; i--) {
            result = result.concat(String.valueOf(i));
        }
        return result;
    }
}
