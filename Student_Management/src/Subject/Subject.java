package Subject;
public class Subject {

    String subjectName;
    private String  subjectId; // 과목 번호
    private String type;  // "required" or "elective"

    public Subject(String subjectId, String subjectName, String type) {
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.type = type;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}