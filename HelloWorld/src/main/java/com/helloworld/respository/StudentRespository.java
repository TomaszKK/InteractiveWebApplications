package com.helloworld.respository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.helloworld.model.Student;

@Repository
public interface StudentRespository extends JpaRepository<Student, Long> {
    Student findById(long id);
}
