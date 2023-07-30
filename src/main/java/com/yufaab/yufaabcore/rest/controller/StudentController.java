package com.yufaab.yufaabcore.rest.controller;

import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

  @Autowired
  StudentService studentService;
  @PostMapping("/signup")
  public void signupStudent(@RequestBody StudentDTO studentDTO){
    studentService.signupStudent(studentDTO);
  }

  @PostMapping("/login")
  public void loginStudent(@RequestBody Students students){
    studentService.loginStudent(students);
  }

  @PostMapping("/logout")
  public void logoutStudent(@RequestBody Students students){
    studentService.logoutStudent(students);
  }

  @PostMapping("/order")
  public void createOrder(@RequestBody Students students){
    studentService.createOrder(students);
  }

  @GetMapping("/order")
  public void getAllOrder(@RequestBody Students students){
    studentService.getAllOrder(students);
  }

  @GetMapping("/order/{orderId}")
  public void getOrder(@RequestBody Students students){
    studentService.getOrder(students);
  }

  @DeleteMapping("/order/{orderId}")
  public void deleteOrder(@RequestBody Students students){
    studentService.deleteOrder(students);
  }

  @GetMapping("/generate/result/{orderId}")
  public void generateCounsellingData(@RequestBody Students students){
    studentService.generateCounsellingData(students);
  }

  @GetMapping("/generate/report/{orderId}")
  public void generatePdfReport(@RequestBody Students students){
    studentService.generatePdfReport(students);
  }
}
