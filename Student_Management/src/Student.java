import java.util.Scanner;

public class Student {
    String studentName;
    Subject[] selectMainSubjectName;
    Subject[] selectSubSubjectName;

    public String getStudentName() {
        return studentName;
    }

    public Subject[] getSelectMainSubjectName() {
        return selectMainSubjectName;
    }

    public Subject[] getSelectSubSubjectName() {
        return selectSubSubjectName;
    }


    // 과목별 등급 조회 메서드
    public void inquiryGrade(){
        Student student = new Student();
        Scanner sc= new Scanner(System.in);

        System.out.println(student.getStudentName() +"님의 과목등급을 조회합니다.");
        System.out.println("조회할 과목의 타입을 선택하세요. (1) 필수 (2) 선택");
        int choiceType = sc.nextInt();

        switch (choiceType){
            case 1:
                System.out.println("필수과목 중 어떤 과목의 등급을 조회하시겠습니까?");
                student.getSelectMainSubjectName();
                String choiceSubject=sc.nextLine();

                for (Subject subject : student.getSelectMainSubjectName()) {
                    if(subject.equals(choiceSubject)){
                        for (int i=0;i<subject.getScore().length;i++){
                            System.out.print(i+1+"회차 : "+subject.grade[i]+" ");
                        }
                    }
                }
                break;
            case 2:
                System.out.println("선택과목 중 어떤 과목의 등급을 조회하시겠습니까?");
                student.getSelectSubSubjectName();
                String choiceSubject2=sc.nextLine();

                for (Subject subject : student.getSelectSubSubjectName()) {
                    if(subject.equals(choiceSubject2)){
                        for (int i=0;i<subject.getScore().length;i++){
                            System.out.print(i+1+"회차 : "+subject.grade[i]+" ");
                        }
                    }
                }
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
}
