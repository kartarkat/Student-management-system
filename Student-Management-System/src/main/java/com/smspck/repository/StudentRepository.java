package com.smspck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smspck.model.Student;


@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

}
