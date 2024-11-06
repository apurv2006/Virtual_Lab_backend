package crce.example.virtual_lab;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student_entity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //This will be treated as the password for current purposes
    private String name;

    public void Student()
    {}

    public void Student(String name, Long id)
    {
        this.name=name;
        this.id=id;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id=id;
    }
    public String getname()
    {
        return name;
    }
    public void setname(String name)
    {
        this.name=name;
    }
}
