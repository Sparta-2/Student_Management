package SubjectEnrollment;

import Score.Score;
import Subject.Subject;
import error.IsFullSessionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SubjectEnrollment {

    Subject subject;

    private Map<Integer, Score> scoresBySession;

    public Subject getSubject() {
        return subject;
    }

    public SubjectEnrollment(Subject subject, Map<Integer,Score> scoresBySession) {
        this.subject = subject;
        this.scoresBySession = scoresBySession;
    }
    public Map<Integer, Score> getScoresBySession() {
        return scoresBySession;
    }
// Map<String, String> student = new HashMap<String, String>();
  //  static Map<Integer, Integer> subject = new HashMap<Integer, Integer>(10);

    public int findNextSession() throws IsFullSessionException {
        int nextSession = 1;
        while (scoresBySession.containsKey(nextSession)) {
            nextSession++;
            if (nextSession > 10) {
                throw new IsFullSessionException("섹션이 모두 가득 찼습니다");
            }
        }
        return nextSession;
    }

    Scanner sc = new Scanner(System.in);


    String StudentNo;
    String StudentName;
    int enrollmentScore = 0;
    int session = 0;
    public void inputScore(int session, int score)  {
        scoresBySession.put(session, new Score(score, "A"));
    }
//    public SubjectEnrollment() {
//
//        // 임시로 학번, 학생 등록
//        student.put("A02001", "박다미");
//        student.put("A02002", "김창민");
//        student.put("A02003", "윤일영");
//        student.put("A02004", "최지연");
//        student.put("A02005", "홍서영");
//
//        // 임시로 학생번호 입력받아서 변수에 저장 (원래는 2-1 들어오기 전, 2 화면에서 입력받기)
//        do {
//            System.out.println("학생번호를 입력해주세요.");
//            StudentNo = sc.next();
//            if (!student.containsKey(StudentNo)) {
//                System.out.println("학번 [" + StudentNo + "] 은 등록되지 않은 학번입니다.");
//            }
//        } while (!student.containsKey(StudentNo));
//
//        StudentName = student.get(StudentNo);
//
//    }
//
//    public void SubjectSessionFind() {
//
//        // 이게 모든 과목이 다 보여져야할듯
//        for(int i=1; i<=11; i++) {
//
//            if (!subject.containsKey(i)) {
//                session = i;
//                System.out.println("[" + StudentName + "] 학생의 " + i + "회차 점수 등록하기");
//                Enrollment();
//                break;
//            }
//
//        }
//
//        if (session == 0) {
//            System.out.println("10회차까지 등록 완료된 과목입니다.");
//        }
//    }
//
//    public void Enrollment() {
//
//        if (session == 0) {
//            System.out.println("이미 10회차까지 모두 등록된 과목입니다.");
//            System.exit(0);
//        }
//
//        do{
//            System.out.println(session + "회차 점수를 입력해주세요. (0~100 사이 정수 입력)");
//            enrollmentScore = sc.nextInt();
//        } while (enrollmentScore < 0 || enrollmentScore > 100);
//
//        subject.put(session, enrollmentScore);
//
//    }
}