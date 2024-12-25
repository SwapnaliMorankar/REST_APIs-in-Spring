package spring.Dao;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import spring.Model.Student;

@Repository
public class StudentDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    // Save or update a student object
    @Transactional
    public void saveStudent(Student student) {
        try {
            hibernateTemplate.saveOrUpdate(student);
        } catch (Exception e) {
            throw new RuntimeException("Error saving student: " + e.getMessage(), e);
        }
    }

    // Fetch a student by ID
    public Student getStudent(int id) {
        return hibernateTemplate.get(Student.class, id);
    }

    // Fetch all students
    public List<Student> getAllStudents() {
        return hibernateTemplate.loadAll(Student.class);
    }

    // Delete student by ID
    @Transactional
    public void deleteStudent(int id) {
        Student student = hibernateTemplate.get(Student.class, id);
        if (student != null) {
            hibernateTemplate.delete(student);
        } else {
            throw new IllegalArgumentException("Student not found with ID: " + id);
        }
    }

    // Update a student's details
    @Transactional
    public void updateStudent(Student student) {
        try {
            Student existingStudent = hibernateTemplate.get(Student.class, student.getId());
            if (existingStudent != null) {
                hibernateTemplate.merge(student);  // Update existing student
            } else {
                throw new IllegalArgumentException("Student not found with ID: " + student.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating student: " + e.getMessage(), e);
        }
    }
}
