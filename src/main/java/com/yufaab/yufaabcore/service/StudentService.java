package com.yufaab.yufaabcore.service;

import com.yufaab.yufaabcore.dao.domain.Orders;
import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.dao.repository.OrderRepository;
import com.yufaab.yufaabcore.dao.repository.StudentRepository;
import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import com.yufaab.yufaabcore.rest.dto.mapper.OrderMapper;
import com.yufaab.yufaabcore.rest.dto.mapper.StudentMapper;
import com.yufaab.yufaabcore.rest.dto.request.OrderDTO;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.service.externalservice.GoogleClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private GoogleClient googleClient;

  private final StudentMapper studentMapper = StudentMapper.getMapper();

  private final OrderMapper orderMapper = OrderMapper.getMapper();

  public Students signupStudent(StudentDTO studentDTO) {
    try{
      Students students;
      if(!StringUtils.isEmpty(studentDTO.getGoogleAccessToken())){
        log.info("Google access token found: {}", studentDTO.getGoogleAccessToken());
        GoogleClient.GoogleRes googleRes = googleClient.googleLogin(studentDTO.getGoogleAccessToken());
        students = studentMapper.googleResToStudents(googleRes);
      } else {
        students = studentMapper.studentDTOtoStudents(studentDTO);
      }
      studentRepository.save(students);
      return students;
    } catch (Exception e){
      log.info("Student signup failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP, e.getMessage());
    }
  }

  public Students loginStudent(StudentDTO studentDTO) {
    try{
      String email;
      if(!StringUtils.isEmpty(studentDTO.getGoogleAccessToken())){
        log.info("Google access token found: {}", studentDTO.getGoogleAccessToken());
        GoogleClient.GoogleRes googleRes = googleClient.googleLogin(studentDTO.getGoogleAccessToken());
        email = googleRes.getEmail();
      } else {
        email = studentDTO.getEmail();
      }
      return studentRepository
              .findByEmailAndPassword(email,studentDTO.getPassword());
    }catch(Exception e){
      log.info("Login signup failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  public void logoutStudent(Students students) {
  }

  public Orders createOrder(OrderDTO orderDTO) {
    try{
      Orders orders;
      if(orderDTO.isNewOrder()) {
        log.info("New order found with parameters: {}",orderDTO.toString());
        orders = orderRepository.save(orderMapper.orderDTOtoOrder(orderDTO));
        Students students = studentRepository.findById(orderDTO.getOrderedBy())
                .orElseThrow(() -> new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP));
        students.addOrder(orders);
        studentRepository.save(students);
      } else {
        log.info("Update in the existing order with parameters: {}", orderDTO.toString());
        orders = orderRepository.findById(orderDTO.getOrderId())
                .orElseThrow(() -> new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP));
        orderRepository.save(orders);
      }
      return orders;
    } catch (Exception e){
      log.info("Create order failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  public Orders getOrder(String orderId) {
    try{
      return orderRepository.findById(orderId)
              .orElseThrow(() -> new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP));
    }catch (Exception e){
      log.info("Get orders failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  public void deleteOrder(String orderId) {
    try{
      orderRepository.deleteById(orderId);
    }catch (Exception e){
      log.info("Delete order failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  public List<Orders> getAllOrder(OrderDTO orderDTO) {
    try{
      return orderRepository.findByOrderedBy(orderDTO.getOrderedBy());
    }catch (Exception e){
      log.info("Get all orders failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  public void generateCounsellingData(String orderId) {
  }

  public void generatePdfReport(String orderId) {
  }
}
