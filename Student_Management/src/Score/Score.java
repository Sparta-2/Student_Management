package Score;

public class Score {

    // 점수, 등급
    private int score;
    private  String grade;

    public Score(int score, String grade) {
        this.score = score;
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

     //필수 과목 등급 계산 - 점수 등록시 메서드 호출
    public static void mainSubjectGrade(Score score) { // 객체의 값을 바꾼거기 때문에 void로 설정으로 바꿨습니다!
        int points = score.score;
        if (points >= 95) score.grade = "A"; // if else은 위에서 한번 걸러지고 내려오기 때문에 && <95는 암묵적으로 구현 됩니다!
        else if (points >= 90) score.grade = "B";
        else if (points >= 80) score.grade = "C";
        else if (points >= 70) score.grade = "D";
        else if (points >= 60) score.grade = "F";
        else score.grade = "N";
    }

    // 선택 과목 등급 계산 - 점수 등록시 메서드 호출
    public static void subSubjectGrade(Score score) {
        int points = score.score;
        if (points >= 90) score.grade = "A";
        else if (points >= 80) score.grade = "B";
        else if (points >= 70) score.grade = "C";
        else if (points >= 60) score.grade = "D";
        else if (points >= 50) score.grade = "F";
        else score.grade = "N";
//            System.out.println("잘못 입력된 점수입니다. (0~100 사이의 점수만 등급을 부여할 수 있습니다.)");
//            => 점수입력때 확인을 한후 등급을 생성하기 때문에 여기서는 사용하지 않아도 됩니다!
        }
}
