package Student;

import SubjectEnrollment.SubjectEnrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Student extends Person{
    private Map<String, SubjectEnrollment> subjects;

    private static List<Student> studentStore = new ArrayList<>();


    public Student(int id, String name, Map<String, SubjectEnrollment> subjects) {
        super(id, name);
        this.subjects = subjects;
    }

    public Map<String, SubjectEnrollment> getSubjects() {
        return subjects;
    }

    // 수강생 목록 조회
    private static void inquiryStudent(){
//        Student student = new Student();
//        String studentID = student.getId();

        System.out.println("\n 수강생 목록을 조회합니다...");
        System.out.println("=======================");

        for (int i = 0; i < studentStore.size(); i++) {
//            String studentName = studentStore.get(i).getStudentName();
//            System.out.println((i+1) + "." + "ID: " + studentID + "NAME: " + studentName);
        }
        System.out.println("=======================");
        System.out.println("\n 수강생 목록 조회 성공!");
    }



//
//    public String getStudentName() { 이름을 person으로 상속받아서 이부분에서는 구현이 없습니다!
//        return studentName;
//    }
//
//    public Subject[] getSelectMainSubjectName() {
//        return selectMainSubjectName;
//    }
//
//    public Subject[] getSelectSubSubjectName() {
//        return selectSubSubjectName;
//    }
//
//
//    // 과목별 등급 조회 메서드
//    public void inquiryGrade(){     // 특정 과목의 정보 출력 -> StudentController에서 다뤘습니다!
//        Scanner sc= new Scanner(System.in);
//
//        System.out.println(this.getStudentName() +"님의 과목등급을 조회합니다.");
//        System.out.println("조회할 과목의 타입을 선택하세요. (1) 필수 (2) 선택");  // 과목은 다 출력되고, 타입선택 후 또 과목설정이 필요할거 같아서 과목설정후 타입을 보여줬습니다!
//        int choiceType = sc.nextInt();
//
//        switch (choiceType){
//            case 1:
//                System.out.println("필수과목 중 어떤 과목의 등급을 조회하시겠습니까?");
//                this.getSelectMainSubjectName();
//                String choiceSubject=sc.nextLine();
//
//                for (Subject subject : this.getSelectMainSubjectName()) {
//                    if(subject.equals(choiceSubject)){
//                        for (int i=0;i<subject.getScore().length;i++){
//                            System.out.print(i+1+"회차 : "+subject.grade[i]+" ");
//                        }
//                    }
//                }
//                break;
//            case 2:
//                System.out.println("선택과목 중 어떤 과목의 등급을 조회하시겠습니까?");
//                this.getSelectSubSubjectName();
//                String choiceSubject2=sc.nextLine();
//
//                for (Subject subject : this.getSelectSubSubjectName()) {
//                    if(subject.equals(choiceSubject2)){
//                        for (int i=0;i<subject.getScore().length;i++){
//                            System.out.print(i+1+"회차 : "+subject.grade[i]+" ");
//                        }
//                    }
//                }
//                break;
//            default:
//                System.out.println("잘못된 입력입니다.");
//                break;
//        }
//    }

}