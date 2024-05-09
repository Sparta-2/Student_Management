package Student.controllers;

import Student.models.Student;
import SubjectEnrollment.models.SubjectEnrollment;

import java.util.HashMap;
import java.util.Map;

public class StudentManager {
    private Map<Integer, Student> students = new HashMap<>();

    // 학생 추가
    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    // 학생 정보 가져오기
    public Student getStudent(int id) {
        return students.get(id);
    }
    public Map<Integer, Student> getAllStudents() {
        return students;
    }

    // 모든 학생 목록 출력
    public void displayAllStudents() {
        System.out.println("현재 등록된 모든 학생 목록:");
        for (Student student : students.values()) {
            System.out.printf("ID: %d, 이름: %s\n", student.getId(), student.getName());
        }
    }
    public void displayStudentsAllSubjects(Student student) {
        for (SubjectEnrollment enrollment : student.getSubjects().values()) {
            System.out.printf("과목 ID: %s, 과목 이름: %s\n", enrollment.getSubject().getSubjectId(), enrollment.getSubject().getSubjectName());
        }

    }
    // ID로 학생 검색
    public Student getStudentById(int id) {
        return students.get(id);
    }

    //학생 삭제
    public void deleteStudent(int id) {
        students.remove(id);
    }
}
