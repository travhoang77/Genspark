package genspark.assignments.section2;

import genspark.assignments.Assignment;

public class SwitchItUp implements Assignment {
    public String solution(int x) {
        // ↓↓↓↓ your code goes here ↓↓↓↓
        switch (x) {
            case 1 : return "one";
            case 2 : return "two";
            case 3 : return "three";
            case 4 : return "four";
            case 5 : return "give";
            default: return "none matched";
        }
    }
}
