import java.util.Scanner;

public class Main {
    String[] mainSubject=new String[]{"Java 객체지향", "Spring", "JPA", "MySql"};
    String[] subSubject=new String[]{"디자인패턴", "Spring Security", "Redis", "MongoDB"};
    public static void main(String[] args) {

        Student student =new Student();
        Scanner sc= new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("점수 관리 실행 중...");
        System.out.println("1. 수강생의 점수등록");
        System.out.println("2. 수강생의 과목별 회차 점수 수정");
        System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
        System.out.println("4. 점수관리 화면 이동");
        System.out.println("0. 메인 화면 이동");
        System.out.print("관리 항목을 선택하세요...");
        int inputChoice = sc.nextInt();

        switch (inputChoice){
            case 1:
                // 수강생의 점수등록
                break;
            case 2:
                // 수강생의 과목별 회차 점수 수정
                break;
            case 3:
                // 수강생 특정 과목 회차별 등급 조회
                // 고유변호, 수강생 이름, 수강하는 과목 목록
                student.inquiryGrade();
                break;
            case 4:
                break;
            case 0:
                break;
        }




    }



}