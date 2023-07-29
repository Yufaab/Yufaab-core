package com.yufaab.yufaabcore.rest.controller;

import com.yufaab.yufaabcore.dao.domain.Student;
import com.yufaab.yufaabcore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

  @Autowired
  StudentService studentService;
  @PostMapping("/signup")
  public void signupStudent(@RequestBody Student student){
    studentService.signupStudent(student);
  }

  @PostMapping("/login")
  public void loginStudent(@RequestBody Student student){
    studentService.loginStudent(student);
  }

  @PostMapping("/logout")
  public void logoutStudent(@RequestBody Student student){
    studentService.logoutStudent(student);
  }

  @PostMapping("/order")
  public void createOrder(@RequestBody Student student){
    studentService.createOrder(student);
  }

  @GetMapping("/order}")
  public void getAllOrder(@RequestBody Student student){
    studentService.getAllOrder(student);
  }

  @GetMapping("/order/{orderId}")
  public void getOrder(@RequestBody Student student){
    studentService.getOrder(student);
  }

  @DeleteMapping("/order/{orderId}")
  public void deleteOrder(@RequestBody Student student){
    studentService.deleteOrder(student);
  }

  @GetMapping("/generate/result/{orderId}")
  public void generateCounsellingData(@RequestBody Student student){
    studentService.generateCounsellingData(student);
  }

  @GetMapping("/generate/report/{orderId}")
  public void generatePdfReport(@RequestBody Student student){
    studentService.generatePdfReport(student);
  }
}
