public class Subject {

    String subjectName;
    int[] score = new int[10];  // 과목당 시험 회차 총 10번
    char[] grade= new char[10];

    public String getSubjectName() {
        return subjectName;
    }

    public int[] getScore() {
        return score;
    }

    public char[] getGrade() {
        return grade;
    }

    // 필수 과목 등급 계산 - 점수 등록시 메서드 호출
    private char[] mainSubjectGrade(int score, int index){
        if (score>=95 && score<=100){
            this.grade[index]='A';
        } else if (score>=90 && score<95){
            this.grade[index]='B';
        } else if (score>=80 && score<90) {
            this.grade[index]='C';
        } else if (score>=70 && score<80){
            this.grade[index]='D';
        } else if (score>=60 && score<70) {
            this.grade[index]='F';
        } else if (score<60 && score>=0) {
            this.grade[index]='N';
        } else {
            System.out.println("잘못 입력된 점수입니다. (0~100 사이의 점수만 등급을 부여할 수 있습니다.)");
        }
        return grade;
    }

    // 선택 과목 등급 계산 - 점수 등록시 메서드 호출
    private char[] subSubjectGrade(int score,int index){
        if (score>=90 && score<=100){
            this.grade[index]='A';
        } else if (score>=80 && score<90){
            this.grade[index]='B';
        } else if (score>=70 && score<80) {
            this.grade[index]='C';
        } else if (score>=60 && score<70){
            this.grade[index]='D';
        } else if (score>=50 && score<60) {
            this.grade[index]='F';
        } else if (score<50 && score>=0) {
            this.grade[index]='N';
        } else {
            System.out.println("잘못 입력된 점수입니다. (0~100 사이의 점수만 등급을 부여할 수 있습니다.)");
        }
        return grade;
    }

}