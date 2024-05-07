import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScoreEdit {

    Map<String, String> student = new HashMap<String, String>();
    Scanner sc = new Scanner(System.in);

    String[] EssentialSubject = {"Java", "객체지향", "Spring", "JPA", "MySql"};
    String[] SelectSubject = {"디자인패턴", "Spring Security", "Redis", "MongoDB"};
    String StudentNo;

    public ScoreEdit() {

        // 임시로 학번, 학생 등록
        student.put("02001", "박다미");
        student.put("02002", "김창민");
        student.put("02003", "윤일영");
        student.put("02004", "최지연");
        student.put("02005", "홍서영");

        // 임시로 학생번호 입력받아서 변수에 저장 (원래는 2-1 들어오기 전, 2 화면에서 입력받기)
        do {
            System.out.println("학생번호를 입력해주세요.");
            StudentNo = sc.next();
            if (!student.containsKey(StudentNo)) {
                System.out.println("학번 [" + StudentNo + "] 은 등록되지 않은 학번입니다.");
            }
        } while (!student.containsKey(StudentNo));

        // 1. 입력받은 학번으로 해당 학생이 수강하는 과목 조회
        //    어떤 과목에 점수를 등록할 것인지 선택하기

//        <예시>
//        1. Java (1회차 등록하기)
//        2. 객체지향 (3회차 등록하기)
//        3. JPA (10회차까지 등록 완료)
//        4. Spring Security (2회차 등록하기)
//        5. MongoDB (9회차 등록하기)

        // Subject 에 있는 Map을 가져와서 ?
        // for문 돌린다던지 해서 몇회까지 등록했는지 조회






        // 2. 수강하는 과목의 점수 입력하기
//        [김창민] 학생의 Spring Security 2회차 점수를 등록해주세요.

        // 3. 저장하기


        // 4. 저장 완료 후 2번화면으로 이동

    }

}