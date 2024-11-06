package crce.example.virtual_lab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Student_repository extends JpaRepository<String, Long>
{
    Optional<String> findthroughname(String name);
}

