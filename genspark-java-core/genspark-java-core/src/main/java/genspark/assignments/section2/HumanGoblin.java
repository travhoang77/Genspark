package genspark.assignments.section2;

import genspark.assignments.Assignment;

public class HumanGoblin implements Assignment {
    public String solution(String str) {

        // ↓↓↓↓ your code goes here ↓↓↓↓
        if (str.equals("goblin")) return "Attack the Goblin!";
        if (str.equals("human")) return "You aRe one of us!";

        return "";
    }
}
