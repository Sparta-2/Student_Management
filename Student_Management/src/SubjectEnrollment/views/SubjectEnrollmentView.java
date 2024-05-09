package SubjectEnrollment.views;

import Score.Score;
import Student.models.Student;
import SubjectEnrollment.controllers.SubjectEnrollmentController;
import SubjectEnrollment.models.SubjectEnrollment;

import java.io.BufferedReader;
import java.io.IOException;

import static Score.Score.mainSubjectGrade;
import static Score.Score.subSubjectGrade;

public class SubjectEnrollmentView {
    static SubjectEnrollmentController controller = new SubjectEnrollmentController();
    public void displaySelectedCourseScores(SubjectEnrollment enrollment) {
        displaySubjectScores(enrollment);
    }

    public void displayAllCourseScores(Student student) {
        System.out.println("등록된 모든 과목의 점수:");
        for (SubjectEnrollment enrollment : student.getSubjects().values()) {
            System.out.println("과목: " + enrollment.getSubject().getSubjectName() + "의 점수");
            for (int session = 1; session <= 10; session++) {
                Score score = enrollment.getScoresBySession().get(session);
                String scoreOutput = (score != null) ? String.valueOf(score.getScore()) : "점수 없음";
                System.out.printf(" 회차 %d: 점수 %s\n", session, scoreOutput);
            }
        }
    }

    public void displaySessionGrades(BufferedReader br, Student student) throws IOException {
        String subjectId = "";
        SubjectEnrollment subjectEnrollment = null;
        while (true) {
            System.out.println("등급을 조회할 과목의 ID를 입력하세요:");

            subjectId = br.readLine().trim();

            subjectEnrollment = student.getSubjects().get(subjectId);

            if (subjectEnrollment == null) {
                System.out.println("해당 ID의 과목이 존재하지 않습니다. 다시 입력해주세요.");
            } else {
                break;
            }
        }
        int session = 0;

        displaySelectedCourseScores(subjectEnrollment);

        while (true) {
            System.out.printf("[%s]의 조회하고 싶은 [회차]를 입력하세요:\n", subjectEnrollment.getSubject().getSubjectName());
            try {
                session = Integer.parseInt(br.readLine().trim());
                if (!subjectEnrollment.getScoresBySession().containsKey(session) || session < 1 || session > 10) {
                    System.out.println("유효하지 않은 세션 번호입니다. 다시 입력해주세요.");
                } else {
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자를 입력해야 합니다.");
            }
        }
        controller.changeGrade(subjectEnrollment, session);
        Score score = subjectEnrollment.getScoresBySession().get(session);

        System.out.printf("과목: %s, 회차: %d, 점수: %d, 등급: %s\n",
                subjectEnrollment.getSubject().getSubjectName(), session, score.getScore(), score.getGrade());
    }

    public void displaySubjectScores(SubjectEnrollment enrollment) {
        System.out.println("과목: " + enrollment.getSubject().getSubjectName() + "의 점수");
        for (int session = 1; session <= 10; session++) {
            Score score = enrollment.getScoresBySession().get(session);
            String scoreOutput = (score != null) ? String.valueOf(score.getScore()) : "점수 없음";
            System.out.printf(" 회차 %d: 점수 %s\n", session, scoreOutput);
        }
    }
}
