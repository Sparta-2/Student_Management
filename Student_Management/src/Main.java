import java.util.Scanner;

public class Main {
    String[] mainSubject=new String[]{"Java 객체지향", "Spring", "JPA", "MySql"};
    String[] subSubject=new String[]{"디자인패턴", "Spring Security", "Redis", "MongoDB"};
    public static void main(String[] args) {

        Student student =new Student();

        Scanner sc= new Scanner(System.in);

        // 점수 수정
//        ScoreModify scoreModify = new ScoreModify(90);
//        System.out.println("2회차:"+ scoreModify.getScore() +" 에서 변환할 점수를 입력해주세요");
//        System.out.print(">> ");
//        int modifyScore = sc.nextInt();
//        System.out.println("2회차: " + modifyScore);
//        System.out.println("점수가 성공적으로 등록되었습니다.");
//
//        // 화면 이동
//        System.out.println("1. 점수등록\n2. 점수수정\n3. 회차별 등급 조회\n4. 다른수강생 선택하기\n0. 메인화면 이동");
//        System.out.print("이동하고 싶은 화면을 선택해주세요: ");
//        int screenChange = sc.nextInt();
//        switch (screenChange) {
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 0:
//                break;
//            default:
//                System.out.println("잘못된 입력입니다.");
//                break;
//        }



        // 수강생 특정 과목 회차별 등급 조회
        // 고유변호, 수강생 이름, 수강하는 과목 목록
        System.out.println(student.getStudentName() +"님의 과목등급을 조회합니다.");
        System.out.println("조회할 과목의 타입을 선택하세요. (1) 필수 (2) 선택");
        int choiceType = sc.nextInt();
        inquiryGrade(choiceType);

    }

    // 과목별 등급 조회 메서드
    public static void inquiryGrade(int choiceType){
        Student student = new Student();
        Scanner sc= new Scanner(System.in);

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