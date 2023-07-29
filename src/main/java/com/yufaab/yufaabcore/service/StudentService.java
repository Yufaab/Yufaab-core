package com.yufaab.yufaabcore.service;

import com.yufaab.yufaabcore.dao.domain.Student;
import com.yufaab.yufaabcore.dao.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  StudentRepository studentRepository;
  public void signupStudent(Student student) {
    studentRepository.save(student);
  }

  public void loginStudent(Student student) {
  }

  public void logoutStudent(Student student) {
  }

  public void createOrder(Student student) {
  }

  public void getOrder(Student student) {
  }

  public void deleteOrder(Student student) {
  }

  public void getAllOrder(Student student) {
  }

  public void generateCounsellingData(Student student) {
  }

  public void generatePdfReport(Student student) {
  }
}
