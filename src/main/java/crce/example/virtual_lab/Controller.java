package crce.example.virtual_lab;

import java.util.Optional;

import crce.example.virtual_lab.Student_entity;
import crce.example.virtual_lab.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/students")
public class Controller
{
    @Autowired
    private Service studentService;

    @PostMapping("register")
    public String registerStudent(@RequestBody String student) 
    {
        return Service.saveStudent(Student_entity);
    }

    /**
     * @param name
     * @param id
     * @return
     */
    @GetMapping("/login")
    public Optional<String> loginStudent(@RequestParam String name,@RequestParam Long id)
    {
        Optional<String> student=Service.getStudentByName(name);
        if (student.isPresent() && student.get.getID().equals(id))
        {
            return student; //Denotes that the login is successful
        }
        return Optional.empty(); //Fail Login
    }
}
