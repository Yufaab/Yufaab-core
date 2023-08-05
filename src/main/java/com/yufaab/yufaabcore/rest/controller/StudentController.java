package com.yufaab.yufaabcore.rest.controller;

import com.yufaab.yufaabcore.dao.domain.Counselling2022;
import com.yufaab.yufaabcore.dao.domain.Orders;
import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.rest.dto.request.OrderDTO;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Document;
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
  public ResponseEntity<Orders> createOrder(@RequestBody OrderDTO orderDTO){
    return ResponseEntity.ok(studentService.createOrder(orderDTO));
  }

  @GetMapping("/order")
  public ResponseEntity<List<Orders>> getAllOrder(@RequestBody OrderDTO orderDTO){
    return ResponseEntity.ok(studentService.getAllOrder(orderDTO));
  }

  @GetMapping("/order/{orderId}")
  public ResponseEntity<Orders> getOrder(@PathVariable String orderId){
    return ResponseEntity.ok(studentService.getOrder(orderId));
  }

  @DeleteMapping("/order/{orderId}")
  public void deleteOrder(@PathVariable String orderId){
    studentService.deleteOrder(orderId);
  }

  @GetMapping("/generate/result/{orderId}")
  public void generateCounsellingData(@PathVariable String orderId){
    studentService.generateCounsellingData(orderId);
  }

  @GetMapping("/generate/report/{orderId}")
  public void generatePdfReport(@PathVariable String orderId){
    studentService.generatePdfReport(orderId);
  }
}
