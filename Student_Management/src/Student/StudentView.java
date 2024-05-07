package Student;

import java.util.Map;

public class StudentView { // 일여ㅇ

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
//    public void displayCourseSessions(Student student, String courseId) {
//
//        CourseEnrollment courseEnrollment = student.getSubjects().get(courseId);
//        if (courseEnrollment != null) {
//            System.out.println("=======================================");
//            System.out.printf("학생 ID: %d, 이름: %s\n", student.getId(), student.getName());
//            System.out.printf("과목 ID: %s, 과목 이름: %s\n", courseId, courseEnrollment.getSubjects().getCourseName());
//            System.out.println("**세션별 점수 및 등급:**");
//            courseEnrollment.getScoresBySession().forEach((session, score) -> {
//                System.out.printf("[%d]세션: [%s]점수 -> [%s]등급\n", session, score.getScore() == 0 ? "점수 없음" : score.getScore(), score.getGrade());
//            });
//            System.out.println("=========================================");
//        } else {
//            System.out.println("해당 과목 ID를 가진 과목이 없습니다.");
//        }
//    }

    //    모든 학생 목록 출력 id
    public void displaysAllStudents(Map<Integer, Student> students) {
        students.forEach((id, student) -> displayStudentDetails(student));
    }
}
