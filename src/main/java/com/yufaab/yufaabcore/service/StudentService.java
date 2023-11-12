package com.yufaab.yufaabcore.service;

import com.itextpdf.text.DocumentException;
import com.yufaab.yufaabcore.dao.domain.Counselling2022;
import com.yufaab.yufaabcore.dao.domain.Orders;
import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.dao.repository.CounsellingDAL;
import com.yufaab.yufaabcore.dao.repository.OrderRepository;
import com.yufaab.yufaabcore.dao.repository.StudentRepository;
import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import com.yufaab.yufaabcore.rest.dto.mapper.OrderMapper;
import com.yufaab.yufaabcore.rest.dto.mapper.StudentMapper;
import com.yufaab.yufaabcore.rest.dto.request.OrderDTO;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.rest.dto.response.StudentResDTO;
import com.yufaab.yufaabcore.util.JwtHelper;
import com.yufaab.yufaabcore.service.externalservice.GoogleClient;
import com.yufaab.yufaabcore.util.PDFGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Slf4j
public class StudentService {

  @Autowired
  private PDFGenerator pdfGenerator;

  @Autowired
  private JwtHelper jwtHelper;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CounsellingDAL counsellingDAL;

  @Autowired
  private GoogleClient googleClient;

  private final StudentMapper studentMapper = StudentMapper.getMapper();

  private final OrderMapper orderMapper = OrderMapper.getMapper();

  public StudentResDTO signupStudent(StudentDTO studentDTO) {
    try{
      Students students;
      if(!StringUtils.isEmpty(studentDTO.getGoogleAccessToken())){
        log.info("Google access token found: {}", studentDTO.getGoogleAccessToken());
        GoogleClient.GoogleRes googleRes = googleClient.googleLogin(studentDTO.getGoogleAccessToken());
        students = studentMapper.googleResToStudents(googleRes);
      } else {
        students = studentMapper.studentDTOtoStudents(studentDTO);
      }
      Students studentCreated = studentRepository.save(students);
      return studentMapper.studentToStudentResDTO(studentCreated, jwtHelper.generateToken(studentCreated.getId()));
    } catch (Exception e){
      log.info("Student signup failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP, e.getMessage());
    }
  }

  public StudentResDTO loginStudent(StudentDTO studentDTO) {
    try{
      String email;
      if(!StringUtils.isEmpty(studentDTO.getGoogleAccessToken())){
        log.info("Google access token found: {}", studentDTO.getGoogleAccessToken());
        GoogleClient.GoogleRes googleRes = googleClient.googleLogin(studentDTO.getGoogleAccessToken());
        email = googleRes.getEmail();
      } else {
        email = studentDTO.getEmail();
      }
      Students students = studentRepository.findByEmailAndPassword(email,studentDTO.getPassword());
      return studentMapper.studentToStudentResDTO(students, jwtHelper.generateToken(students.getId()));
    }catch(Exception e){
      log.info("Login signup failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_LOGIN);
    }
  }

  public void logoutStudent(Students students) {
  }

  public Orders createOrder(OrderDTO orderDTO) {
    try{
      Orders orders;
      if(orderDTO.isNewOrder()) {
        String userId = jwtHelper.getUserId();
        log.info("New order found with parameters: {} and user id: {}", orderDTO.toString(), userId);
        Students students = studentRepository.findById(userId)
                .orElseThrow(() -> new AppException(AppErrorCodes.STUDENT_NOT_FOUND));
        orders = orderRepository.save(orderMapper.orderDTOtoOrder(orderDTO,userId));
        students.addOrder(orders);
        studentRepository.save(students);
      } else {
        log.info("Update in the existing order with parameters: {}", orderDTO.toString());
        orders = orderRepository.findById(orderDTO.getOrderId())
                .orElseThrow(() -> new AppException(AppErrorCodes.ORDER_NOT_FOUND));
        orderRepository.save(orders);
      }
      return orders;
    } catch (Exception e){
      log.info("Create order failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.ORDER_NOT_CREATED_OR_UPDATED);
    }
  }

  public Orders getOrder(String orderId) {
    try{
      return orderRepository.findById(orderId)
              .orElseThrow(() -> new AppException(AppErrorCodes.ORDER_NOT_FOUND));
    }catch (Exception e){
      log.info("Get orders failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.ORDER_NOT_FOUND);
    }
  }

  public void deleteOrder(String orderId) {
    try{
      orderRepository.deleteById(orderId);
    }catch (Exception e){
      log.info("Delete order failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.ORDER_NOT_FOUND);
    }
  }

  public List<Orders> getAllOrder() {
    try{
      return orderRepository.findByOrderedBy(jwtHelper.getUserId());
    }catch (Exception e){
      log.info("Get all orders failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.ORDER_NOT_FOUND);
    }
  }

  public List<Counselling2022> generateCounsellingData(String orderId) {
    try{
      Orders orders = orderRepository.findById(orderId)
              .orElseThrow(() -> new AppException(AppErrorCodes.ORDER_NOT_FOUND));
      return counsellingDAL.dataGenerator(orders);
    }catch (Exception e){
      log.info("Generate counselling data failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.UNABLE_TO_GENERATE_REPORT);
    }
  }

  public byte[] generatePdfReport(String orderId) {
    try {
      Orders orders = orderRepository.findById(orderId)
              .orElseThrow(() -> new AppException(AppErrorCodes.ORDER_NOT_FOUND));
      pdfGenerator.setCounselling2022List(counsellingDAL.dataGenerator(orders));
      return pdfGenerator.generatePdf();
    } catch (DocumentException | IOException e) {
      log.info("Generate PDF report failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.UNABLE_TO_GENERATE_REPORT);
    }
  }
}
