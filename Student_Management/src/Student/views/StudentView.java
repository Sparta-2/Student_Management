package Student.views;

import Student.models.Student;
import SubjectEnrollment.models.SubjectEnrollment;

import java.util.Map;

public class StudentView {

    //학생 정보 출력
    public void displayStudentDetails(Student student) {

        System.out.println("================== 학생 정보 =====================");

        System.out.printf("학생 ID: %d, 이름: %s\n", student.getId(), student.getName());
        System.out.println("수강 과목:");
        student.getSubjects().forEach((id, enrollment) ->
                System.out.println("과목 ID: " + id + ", 과목 이름: " + enrollment.getSubject().getSubjectName()));
        System.out.println("=========================================");
    }

    public void displayBasicInfoStudent(Map<Integer, Student> students) {

        System.out.println("============== 학생 ID, 이름정보 ================================");
        students.forEach((id, student) -> System.out.printf("학생 ID: %d, 이름: %s\n ", id, student.getName()));
        System.out.println("=========================================");
    }

    // 특정 과목의 정보만 출력
    public void displaySubjectSessions(Student student, String subjectId) {

        SubjectEnrollment subjectEnrollment = student.getSubjects().get(subjectId);
        if (subjectEnrollment != null) {
            System.out.println("=======================================");
            System.out.printf("학생 ID: %d, 이름: %s\n", student.getId(), student.getName());
            System.out.printf("과목 ID: %s, 과목 이름: %s\n", subjectId, subjectEnrollment.getSubject().getSubjectName());
            System.out.println("**세션별 점수 및 등급:**");
            subjectEnrollment.getScoresBySession().forEach((session, score) -> {
                System.out.printf("[%d]세션: [%s]점수 -> [%s]등급\n", session, score.getScore() == 0 ? "점수 없음" : score.getScore(), score.getGrade());
            });
            System.out.println("=========================================");
        } else {
            System.out.println("해당 과목 ID를 가진 과목이 없습니다.");
        }
    }

    public void displaysSubjectSessions(Student students) {

        //한명의 학생의 모든 과목id
        for (int i = 0; i < students.getSubjects().size(); i++) {
            String subjectId = students.getSubjects().keySet().toArray()[i].toString();
            displaySubjectSessions(students, subjectId);
        }
    }

    //    모든 학생 목록 출력 id
    public void displaysAllStudents(Map<Integer, Student> students) {
        students.forEach((id, student) -> displayStudentDetails(student));
    }
}
