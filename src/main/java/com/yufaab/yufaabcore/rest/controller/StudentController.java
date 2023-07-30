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

import java.util.List;

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
  public ResponseEntity<List<Order>> getAllOrder(@RequestBody OrderDTO orderDTO){
    return ResponseEntity.ok(studentService.getAllOrder(orderDTO));
  }

  @GetMapping("/order/{orderId}")
  public ResponseEntity<Order> getOrder(@RequestBody OrderDTO orderDTO){
    return ResponseEntity.ok(studentService.getOrder(orderDTO));
  }

  @DeleteMapping("/order/{orderId}")
  public void deleteOrder(@RequestBody OrderDTO orderDTO){
    studentService.deleteOrder(orderDTO);
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
