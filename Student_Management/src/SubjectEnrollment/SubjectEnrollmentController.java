package SubjectEnrollment;

import Score.Score;
import Student.Student;
import Student.StudentView;
import Subject.Subject;
import error.IsFullSessionException;

import java.io.BufferedReader;
import java.io.IOException;

import static Score.Score.mainSubjectGrade;
import static Score.Score.subSubjectGrade;

public class SubjectEnrollmentController {
    static StudentView studentview = new StudentView();

    // 점수 더 추가하기
    public void handleAddScores(BufferedReader br, Student student) {
        studentview.displayStudentDetails(student);
        try {
            String SubjectId = getValidSubjectId(br, student);

            SubjectEnrollment SubjectEnrollment = student.getSubjects().get(SubjectId);

            if (SubjectEnrollment == null) {
                System.out.println("해당 ID의 과목이 존재하지 않습니다. 다시 입력해주세요.");
                return;
            }
            // 해당 섹션에 점수 입력
            getAddScoreSession(br, student, SubjectEnrollment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //점수 입력
    public void getMoreAddScoreSession(BufferedReader br, Student student, SubjectEnrollment subjectEnrollment) throws IOException {

        try {
            int nextSession = subjectEnrollment.findNextSession();

            while (true) {
                System.out.printf("[%s 과목]의 다음[%d섹션]의 점수도 입력할건가요?(Y/N)\n",
                        subjectEnrollment.getSubject().getSubjectName(), nextSession);
                String input = br.readLine().trim();

                if (input.equalsIgnoreCase("Y")) {
                    getAddScoreSession(br, student, subjectEnrollment);
                    break;
                } else if (input.equalsIgnoreCase("N")) {
                    System.out.printf("[%s 과목]의 점수 입력을 진행하지 않습니다.\n", subjectEnrollment.getSubject().getSubjectName());
                    System.out.println("점수를 입력할 다른 과목을 선택하시겠습니까?(Y/N)");

                    input = br.readLine().trim();
                    if (input.equalsIgnoreCase("Y")) {
                        handleAddScores(br,student);

                    } else {
                        System.out.println("더 이상 점수 입력을 진행하지 않습니다.");
                        break;
                    }
                    break;
                } else {
                    System.out.println("Y/N로 입력해야 합니다.");
                }

            }
        } catch (IsFullSessionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAddScoreSession(BufferedReader br, Student student, SubjectEnrollment subjectEnrollment) {
        // 채워지지않은 섹션보여주기
        try {
            int nextSession = subjectEnrollment.findNextSession();

            System.out.printf("%d 회차에 점수를 입력하세요: \n", nextSession);

            int newScore = getValidScore(br);

            subjectEnrollment.inputScore(nextSession, newScore); // 점수 추가
            System.out.println("점수가 성공적으로 추가되었습니다.");
            displayAllCourseScores(student);
            getMoreAddScoreSession(br, student, subjectEnrollment);

        } catch (IsFullSessionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //점수 수정하기
    public void handleUpdateScores(BufferedReader br, Student student) {

        studentview.displayStudentDetails(student);
        try {
            String subjectId = getValidSubjectId(br, student);

            if (subjectId == null) {
                System.out.println("입력한 과목 ID가 유효하지 않습니다.");
                return;
            }
            System.out.println("선택된 과목인 ["+student.getSubjects().get(subjectId).subject.getSubjectName() + "]의 바꿀 점수의 섹션을 입력하세요:");

            // 해당 과목 찾은 후 섹션 입력
            SubjectEnrollment subjectEnrollment = student.getSubjects().get(subjectId);
            int session = getValidScore(br);
            if (session == -1) {
                System.out.println("유효한 회차 정보가 입력되지 않았습니다.");
                return;
            }

            // 해당 섹션 찾은 후 점수 입력
            System.out.println("새로운 점수를 입력하세요:");

            int validScore = getValidScore(br);
            if (validScore == -1) {
                System.out.println("유효한 점수가 입력되지 않았습니다.");
                return;
            }

            // 점수 업데이트
            subjectEnrollment.inputScore(session, validScore);

            // 호출
            displaySelectedCourseScores(subjectEnrollment);
            return;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

}
    private static void displaySelectedCourseScores(SubjectEnrollment enrollment) {
        System.out.println("과목: " + enrollment.getSubject().getSubjectName() + "의 점수");
        for (int session = 1; session <= 10; session++) {
            Score score = enrollment.getScoresBySession().get(session);
            String scoreOutput = (score != null) ? String.valueOf(score.getScore()) : "점수 없음";
            System.out.printf(" 회차 %d: 점수 %s\n", session, scoreOutput);
        }
    }
    private static void displayAllCourseScores(Student student) {
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

    /// 유효한 과목 아이디 판별
    public static String getValidSubjectId(BufferedReader br, Student student) throws IOException {
        System.out.println("과목의 ID를 입력하세요:");
        String SubjectId = br.readLine().trim();

        if (!student.getSubjects().containsKey(SubjectId)) {
            System.out.println(SubjectId + "는 존재하지 않는 과목ID입니다. 적합한 과목ID를 입력하세요");
        }
        return SubjectId;
    }

    // 유효한 점수 확인
    public static int getValidScore(BufferedReader br) throws IOException {
        while (true) {
            try {
                int newScore = Integer.parseInt(br.readLine().trim());
                if (newScore >= 0 && newScore <= 100) {
                    return newScore;
                } else {
                    System.out.println("0~100의 점수를 입력해야 합니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void displaySessionGrades(BufferedReader br, Student student) throws IOException {

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
//             displaySelectedCourseScores(subjectEnrollment);
        //모두볼지 섹션별 선택할지

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

        Score score = subjectEnrollment.getScoresBySession().get(session);
        // 선택 과목인지 필수 과목인지에 따라 등급을 변환
        if (subjectEnrollment.getSubject().getType().equalsIgnoreCase("REQUIRED")) {
            mainSubjectGrade(score);
        } else if (subjectEnrollment.getSubject().getType().equalsIgnoreCase("ELECTIVE")) {
            subSubjectGrade(score);
        }

        System.out.printf("과목: %s, 회차: %d, 점수: %d, 등급: %s\n",
                subjectEnrollment.getSubject().getSubjectName(), session, score.getScore(), score.getGrade());
    }
}

