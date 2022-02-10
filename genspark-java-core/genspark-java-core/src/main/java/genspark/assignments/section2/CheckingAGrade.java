package genspark.assignments.section2;

import genspark.assignments.Assignment;
import org.apache.commons.lang3.Range;

public class CheckingAGrade implements Assignment {
    public String solution(int grade) {
        // ↓↓↓↓ your code goes here ↓↓↓↓
        Range<Integer> rangeA = Range.between(90, 100);
        Range<Integer> rangeB = Range.between(80, 89);
        Range<Integer> rangeC = Range.between(70, 79);
        if (rangeA.contains(grade)) return "A";
        if (rangeB.contains(grade)) return "B";
        if (rangeC.contains(grade)) return "C";
        return "FAILURE";
    }
}
