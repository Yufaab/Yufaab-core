package com.yufaab.yufaabcore.rest.controller;

import com.yufaab.yufaabcore.dao.domain.Counselling2022;
import com.yufaab.yufaabcore.dao.domain.Orders;
import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.rest.dto.request.OrderDTO;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.rest.dto.response.StudentResDTO;
import com.yufaab.yufaabcore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${cross.origin.url}")
@RestController
@RequestMapping("/api/student")
public class StudentController {

  @Autowired
  StudentService studentService;

  @PostMapping("/signup")
  public ResponseEntity<StudentResDTO> signupStudent(@RequestBody StudentDTO studentDTO){
    return ResponseEntity.ok(studentService.signupStudent(studentDTO));
  }

  @PostMapping("/login")
  public ResponseEntity<StudentResDTO> loginStudent(@RequestBody StudentDTO studentDTO){
    return ResponseEntity.ok(studentService.loginStudent(studentDTO));
  }

  @PostMapping("/logout")
  public void logoutStudent(){
    studentService.logoutStudent();
  }

  @PostMapping("/order")
  public ResponseEntity<Orders> createOrder(@RequestBody OrderDTO orderDTO){
    return ResponseEntity.ok(studentService.createOrder(orderDTO));
  }

  @PutMapping("/order")
  public ResponseEntity<Orders> updateOrder(@RequestBody OrderDTO orderDTO){
    orderDTO.isValid();
    return ResponseEntity.ok(studentService.updateOrder(orderDTO));
  }

  @GetMapping("/order")
  public ResponseEntity<List<Orders>> getAllOrder(){
    return ResponseEntity.ok(studentService.getAllOrder());
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
  public ResponseEntity<List<Counselling2022>> generateCounsellingData(@PathVariable String orderId){
    return ResponseEntity.ok(studentService.generateCounsellingData(orderId));
  }

  @GetMapping(value = "/generate/report/{orderId}", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> generatePdfReport(@PathVariable String orderId){
    return ResponseEntity.ok(studentService.generatePdfReport(orderId));
  }
}
