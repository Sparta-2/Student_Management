package Score;

import error.InvalidScoreException;
import error.IsFullSessionException;
import utils.ErrorMessages;

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
    public void mainSubjectGrade(Score score) {
        int points = score.score;
        if ((points <= 100 ) && (points >= 95)) score.grade = "A";
        else if (points >= 90) score.grade = "B";
        else if (points >= 80) score.grade = "C";
        else if (points >= 70) score.grade = "D";
        else if (points >= 60) score.grade = "F";
        else score.grade = "N";
    }

    // 선택 과목 등급 계산 - 점수 등록시 메서드 호출
    public void subSubjectGrade(Score score) {
        int points = score.score;
        if ((points <= 100 ) && (points >= 90)) score.grade = "A";
        else if (points >= 80) score.grade = "B";
        else if (points >= 70) score.grade = "C";
        else if (points >= 60) score.grade = "D";
        else if (points >= 50) score.grade = "F";
        else score.grade = "N";
        }
    public static int invalidScore(int newScore) throws InvalidScoreException {
        if (newScore >= 0 && newScore <= 100) {
            return newScore;
        } else {
            throw new InvalidScoreException(ErrorMessages.INVALID_SCORE);
        }
    }
}
