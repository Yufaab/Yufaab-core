package com.yufaab.yufaabcore.service;

import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.dao.repository.StudentRepository;
import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import com.yufaab.yufaabcore.rest.dto.mapper.StudentMapper;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.service.externalservice.GoogleClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@Slf4j
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private GoogleClient googleClient;

  private final StudentMapper studentMapper = StudentMapper.getMapper();

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

  public void createOrder(Students students) {
  }

  public void getOrder(Students students) {
  }

  public void deleteOrder(Students students) {
  }

  public void getAllOrder(Students students) {
  }

  public void generateCounsellingData(Students students) {
  }

  public void generatePdfReport(Students students) {
  }
}
