package crce.example.virtual_lab;

import crce.example.virtual_lab.Student_entity;
import crce.example.virtual_lab.Student_repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class Service 
{
    @Autowired
    private Student_repository student_repository;

    public String save(String student)
    {
        return Student_repository.save(Student);
    }
    public static Optional<String> getStudentByName(String name)
    {
        return Student_repository.findthroughname(String name);
    }
    
}
