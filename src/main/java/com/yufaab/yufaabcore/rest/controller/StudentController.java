package com.yufaab.yufaabcore.rest.controller;

import com.yufaab.yufaabcore.dao.domain.Order;
import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.rest.dto.request.OrderDTO;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

  @Autowired
  StudentService studentService;
  @PostMapping("/signup")
  public ResponseEntity<Students> signupStudent(@RequestBody StudentDTO studentDTO){
    return ResponseEntity.ok(studentService.signupStudent(studentDTO));
  }

  @PostMapping("/login")
  public ResponseEntity<Students> loginStudent(@RequestBody StudentDTO studentDTO){
    return ResponseEntity.ok(studentService.loginStudent(studentDTO));
  }

  @PostMapping("/logout")
  public void logoutStudent(@RequestBody Students students){
    studentService.logoutStudent(students);
  }

  @PostMapping("/order")
  public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO){
    return ResponseEntity.ok(studentService.createOrder(orderDTO));
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
