import java.util.ArrayList;
import java.util.List;

public class Subject {

    String subjectName;
    int[] score = new int[10];  // 과목당 시험 회차 총 10번
    char[] grade= new char[10];

    public String getSubjectName() {
        return subjectName;
    }

    public int[] getScore() {
        return score;
    }

    public char[] getGrade() {
        return grade;
    }


}