package Student.controllers;

import Service.IDGenerator;
import Student.models.Student;
import Student.views.StudentView;
import Subject.Subject;
import Subject.SubjectData;
import SubjectEnrollment.models.SubjectEnrollment;
import error.InvalidStudentIdException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StudentController {
    private static StudentView view;
    private static StudentManager studentManager;

    private static void registerStudent(String name, Map<String, SubjectEnrollment> enrollments) {
        int studentId = IDGenerator.getInstance().generateId();
        Student newStudent = new Student(studentId, name, enrollments);

        studentManager.addStudent(newStudent);
        System.out.println("학생 등록이 완료되었습니다.");
        view.displayStudentDetails(newStudent);
    }

    // 1. 이름 입력
    private static String getStudentName(BufferedReader br) throws IOException {
        System.out.print("학생 이름을 입력하세요: ");
        String name = br.readLine();
        if (name.isEmpty()) {
            System.out.println("이름을 입력해야 합니다.");
            return null;
        }
        return name;
    }

    public StudentController(StudentView view, StudentManager studentManager) {
        this.view = view;
        this.studentManager = studentManager;
    }

    public static void handelStudentRegistration(BufferedReader br) throws IOException {
        String name = getStudentName(br);
        if (name == null) {
            return;
        }

        List<Subject> allSubjects = SubjectData.getSubjectList();
        // 데이터 가져와서 보여주기

        Map<String, SubjectEnrollment> enrollments = getSubjectEnrollments(br, allSubjects);
        if (enrollments != null) {
            registerStudent(name, enrollments);
        }
    }

    public static Map<String, SubjectEnrollment> getSubjectEnrollments(BufferedReader br, List<Subject> allSubjects) throws IOException {
        displayRequiredSubjects("필수 과목", filterSubjectsByType(allSubjects.stream().filter(Subject -> Subject.getType().equals("required")).collect(Collectors.toList()), "required"));
        displayElectiveSubjects("선택 과목", filterSubjectsByType(allSubjects.stream().filter(Subject -> Subject.getType().equals("elective")).collect(Collectors.toList()), "elective"));

        System.out.println("필수 과목 및 선택 과목 중에서 선택할 과목의 ID를 입력하세요 (예: C01 D02):");
        System.out.println("**[필수과목은 3개] 이상, [선택과목는 2개이상]을 등록해야합니다.**");

        String[] SubjectIds = br.readLine().trim().split(" ");
        Map<String, SubjectEnrollment> enrollments = new HashMap<>();
        Set<String> uniqueIds = new HashSet<>(Arrays.asList(SubjectIds));

        int countRequired = 0, countElective = 0;

        for (String SubjectId : uniqueIds) {
            Subject subject = allSubjects.stream()
                    .filter(c -> SubjectId.equals(c.getSubjectId()))
                    .findFirst()
                    .orElse(null);

            if (subject != null) {
                enrollments.put(subject.getSubjectId(), new SubjectEnrollment(subject, new HashMap<>()));
                if ("required".equals(subject.getType())) {
                    countRequired++;
                } else if ("elective".equals(subject.getType())) {
                    countElective++;
                }
            } else {
                System.out.println("해당 과목 ID가 유효하지 않습니다: " + SubjectId);
            }
        }

        if (countRequired < 3 || countElective < 2) {
            System.out.println("필수 과목은 3개 이상, 선택 과목은 2개 이상 선택해야 합니다.");
            System.out.println("현재 필수 과목 수: " + countRequired + ", 선택 과목 수: " + countElective);
            return null;
        }
        return enrollments;
    }

    // 필수과목인지 선택과목인지 타입에 맞춰서 불러오기
    private static List<Subject> filterSubjectsByType(List<Subject> Subjects, String type) {
        return Subjects.stream()
                .filter(Subject -> Subject.getType().equals(type))
                .collect(Collectors.toList());
    }

    private static void displayRequiredSubjects(String header, List<Subject> Subjects) {
        System.out.println("--" + header + "--");
        Subjects.stream().filter(Subject -> Subject.getType().equals("required"))
                .forEach(Subject -> System.out.printf(" %s (%s) |", Subject.getSubjectName(), Subject.getSubjectId()));
        System.out.println("\n");
    }

    private static void displayElectiveSubjects(String header, List<Subject> Subjects) {
        System.out.println("--" + header + "--");
        Subjects.stream().filter(Subject -> Subject.getType().equals("elective"))
                .forEach(Subject -> System.out.printf(" %s (%s) |", Subject.getSubjectName(), Subject.getSubjectId()));
        System.out.println("\n");
    }

    // 유효한 학생 번호인지 값인지 확인하기
    public int getValidStudentId(BufferedReader br) throws InvalidStudentIdException, IOException {
        while (true) {
            System.out.print("학생 고유번호를 입력하세요: ");

            try {
                String input = br.readLine();

                int studentId = Integer.parseInt(input.trim());

                if (studentManager.getStudentById(studentId) != null) {
                    return studentId;
                } else {
                    throw new InvalidStudentIdException("없는 학생번호입니다. 다시 시도하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    //학생 이름 수정하기
    public void handleUpdateName(BufferedReader br) throws InvalidStudentIdException {
        StudentView studentView = new StudentView();
        studentView.displayBasicInfoStudent(studentManager.getAllStudents());

        System.out.println("이름을 변경할 학생의 고유번호를 입력하세요:");
        try {
            int studentId = getValidStudentId(br); // 고유번호 입력 받기

            Student studentToUpdate = studentManager.getStudentById(studentId);

            System.out.printf("변경하실 이름을 입력하세요 \n [현재 이름: %s] >> : ", studentToUpdate.getName());
            String newName = br.readLine().trim(); // 새 이름 입력 받기
            studentToUpdate.setName(newName); // 이름 변경
            System.out.println("이름이 성공적으로 변경되었습니다.");

        } catch (InvalidStudentIdException e) {
            throw new InvalidStudentIdException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 학생 지우기
    public void handleDeleteName(BufferedReader br) {
        view.displayBasicInfoStudent(studentManager.getAllStudents());

        try {
            System.out.println("삭제할 학생의 고유번호를 입력하세요:");

            int studentId = getValidStudentId(br); // 고유번호 입력 받기

            Student studentToUpdate = studentManager.getStudentById(studentId);
            if (studentToUpdate != null) {
                System.out.printf("삭제할 이름을 확인하세요 \n [현재 이름: %s]수강생을 정말 삭제하시겠습니까?(Y/N)\n >> : ", studentToUpdate.getName());
                String answer = br.readLine().trim();
                if (answer.equalsIgnoreCase("Y")) {

                    studentManager.deleteStudent(studentId);
                    //freeId
                    IDGenerator.getInstance().freeId(studentId);
                    System.out.println("성공적으로 삭제되었습니다.");
                } else {
                    System.out.println("해당 고유 번호를 가진 학생이 존재하지 않습니다.");
                }
            }
        } catch (InvalidStudentIdException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
