package Subject;

import java.util.ArrayList;
import java.util.List;

// 과목 더미 데이터 생성
public class SubjectData {
    private static List<Subject> subjects = new ArrayList<>();
    // 더미 데이터 생성
    public static void createSubjectList() {
        // 필수 과목
        subjects.add(new Subject("C01", "Java", "required"));
        subjects.add(new Subject("C02", "객체지향", "required"));
        subjects.add(new Subject("C03", "Spring", "required"));
        subjects.add(new Subject("C04", "JPA", "required"));
        subjects.add(new Subject("C05", "MySql", "required"));

        // 선택 과목
        subjects.add(new Subject("D01", "디자인패턴", "elective"));
        subjects.add(new Subject("D02", "Spring Security", "elective"));
        subjects.add(new Subject("D03", "Redis", "elective"));
        subjects.add(new Subject("D04", "MongoDB", "elective"));
    }

    // 더미데이터 전체 불러오기
    public static List<Subject> getCourseList() {
        return subjects;
    }
}
