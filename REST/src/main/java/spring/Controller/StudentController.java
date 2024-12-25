package spring.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.Model.Student;
import spring.Service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Save student data
    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
            return ResponseEntity.ok("Student saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error saving student: " + e.getMessage());
        }
    }

    // Fetch all students
    @GetMapping("/showallstudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> studentList = studentService.getAllStudents();
            return ResponseEntity.ok(studentList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get student by ID
    @GetMapping("/getstudent/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Delete student by ID
    @DeleteMapping("/deletestudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        try {
            studentService.removeStudent(id);
            return ResponseEntity.ok("Student deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting student: " + e.getMessage());
        }
    }

    // Update student data
    @PutMapping("/updatestudent/{id}")
    public ResponseEntity<String> updateStudent(
            @PathVariable("id") int id,
            @RequestBody Student updatedStudent) {
        try {
            // Ensure the path ID matches the body ID
            if (id != updatedStudent.getId()) {
                return ResponseEntity.badRequest().body("Student ID in path and body do not match.");
            }

            studentService.updateStudent(updatedStudent);
            return ResponseEntity.ok("Student updated successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating student: " + e.getMessage());
        }
    }
}
