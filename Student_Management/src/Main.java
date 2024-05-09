
import Student.Student;
import Student.StudentController;
import Student.StudentManager;
import Student.StudentView;
import SubjectEnrollment.SubjectEnrollmentController;
import error.InvalidScoreException;
import error.InvalidStudentIdException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    private static StudentView studentView = new StudentView();
    private static StudentManager studentManager = new StudentManager();
    private static StudentController studentController;
    private static SubjectEnrollmentController subjectEnrollmentController = new SubjectEnrollmentController();
    public static void main(String[] args) throws IOException, InvalidStudentIdException {
        studentController = new StudentController(studentView, studentManager);
        mainPage();
    }

    private static void mainPage() throws IOException, InvalidStudentIdException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n==============[수강생관리 메인화면]====================");
        System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
        System.out.println("1. 수강생 관리");
        System.out.println("2. 점수 관리");
        System.out.println("3. 프로그램 종료");
        System.out.print("관리 항목을 선택하세요...\n>> ");

        String input = br.readLine();

        switch (input) {
            case "1":
                studentManageSession(br);
                break;
            case "2":
                //  메인 >> 2 선택 시 : 수강생 리스트 쭉 보여주고 수강생 번호 입력하기
                studentManager.displayAllStudents();
                manageScores(br);
                mainPage();

                break;
            case "3":
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                mainPage();
                break;
        }
    }

    // 선택된 학생에 대한 과목
    private static void manageScores(BufferedReader br) {
        try {
            System.out.println("수강생 번호를 입력하세요:");
            int studentId = studentController.getValidStudentId(br);

            Student student = studentManager.getStudent(studentId);
            scoreSettingSession(br, student);

            if (student == null) {
                System.out.println("없는 학생입니다. 다시 시도하세요.");
                manageScores(br); // 재시도
            }
        }  catch (IOException e){
            System.out.println(e.getMessage());
        } catch (InvalidStudentIdException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void scoreSettingSession(BufferedReader br, Student student) throws IOException, InvalidStudentIdException {
        //2 점수관리
        System.out.println("==============[2. 점수 관리 페이지]====================");
        System.out.println("점수 관리 실행 중...");
        System.out.println("1. 수강생의 점수등록");
        System.out.println("2. 수강생의 과목별 회차 점수 수정");
        System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
        System.out.println("4. 다른 수강생 선택하기");
        System.out.println("0. 메인 화면 이동");

        String input = br.readLine();

        switch (input) {
            case "1":
                //수강생의 점수등록
                studentView.displaysSubjectSessions(student);
                subjectEnrollmentController.handleAddScores(br,student);
                break;
            case "2":
                subjectEnrollmentController.handleUpdateScores(br, student);
                break;
            case "3":
                // 수강생의 특정 과목 회차별 등급 조회
                subjectEnrollmentController.displaySessionGrades(br, student);
                break;
            case "4":
                manageScores(br);
                break;
            case "0":
                mainPage();
                break;
            default:
                System.out.println("관리 항목을 선택하세요");
                scoreSettingSession(br, student); // 재호출
        }
    }

    private static void studentManageSession(BufferedReader br) throws IOException, InvalidStudentIdException {
        System.out.println("==================================");
        System.out.println("수강생 관리 실행 중...");
        System.out.println("1. 수강생 등록");
        System.out.println("2. 수강생 목록 조회");
        System.out.println("3. 수강생 정보 수정");
        System.out.println("4. 수강생 정보 삭제");
        System.out.println("0. 메인 화면 이동");

        System.out.print("관리 항목을 선택하세요...\n>>");

        String input = br.readLine();

        switch (input) {
            case "1":
                studentController.handelStudentRegistration(br);
                mainPage();
                break;
            case "2":
                // 수강생 리스트
                studentView.displaysAllStudents(studentManager.getAllStudents());
                mainPage();

                // 점수 관리 핸들러 호출
                break;
            case "3":
                studentController.handleUpdateName(br);
                mainPage();
                break;
            case "4":
                studentController.handleDeleteName(br);
                mainPage();
                break;
            case "5":
                mainPage();
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                studentManageSession(br);
                break;
        }
    }
}
