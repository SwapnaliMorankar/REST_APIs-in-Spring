package spring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.Dao.StudentDao;
import spring.Model.Student;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    // Add or update a student
    public void addStudent(Student student) {
        studentDao.saveStudent(student);
    }

    // Fetch a student by ID
    public Student getStudentById(int id) {
        return studentDao.getStudent(id);
    }

    // Fetch all students
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    // Remove a student by ID
    public void removeStudent(int id) {
        studentDao.deleteStudent(id);
    }

    // Update a student's information
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }
}
