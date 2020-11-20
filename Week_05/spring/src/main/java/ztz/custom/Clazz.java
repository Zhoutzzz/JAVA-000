package ztz.custom;

import java.util.List;

public class Clazz {

    public String className;

    public List<Student> students;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "className='" + className + '\'' +
                ", students=" + students +
                '}';
    }
}
