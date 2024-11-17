package com.example.studentcontroller.StudentController;

import com.example.studentcontroller.ApiResponse.ApiResponse;
import com.example.studentcontroller.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student-controller")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Student> firstStudents = new ArrayList<>();
    ArrayList<Student> secondStudents = new ArrayList<>();
    double average;





    @GetMapping("/get")
    public ArrayList<Student> getStudents() {
        return students;
    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Successfully added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student) {
        students.set(index, student);
        return new ApiResponse("Successfully updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        students.remove(index);
        return new ApiResponse("Successfully deleted");
    }

    @GetMapping("/honors-categories/{index}")
    public ApiResponse getClassify(@PathVariable int index) {

            if (students.get(index).getGPA() >= 4.75) {
                firstStudents.add(students.get(index));
                return new ApiResponse(firstStudents + " First Class Honor. ");
            } else if (students.get(index).getGPA() >= 4.25) {
                secondStudents.add(students.get(index));
                return new ApiResponse(secondStudents + " Second Class Honor. ");
            }
            return new ApiResponse(students.get(index)+" No Class Honor. ");

    }

    public double average() {
        for (Student student : students) {
            average = average + student.getGPA();
        }
       return average = average / students.size();
    }

    @GetMapping("/average")
    public ApiResponse graterAverage() {
        ArrayList<Student> gpaStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getGPA() > average()) {
                gpaStudents.add(student);
            }
        }
        return new ApiResponse(gpaStudents+"");
    }


}
