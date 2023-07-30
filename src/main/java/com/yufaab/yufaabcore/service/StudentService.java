package com.yufaab.yufaabcore.service;

import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.dao.repository.StudentRepository;
import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import com.yufaab.yufaabcore.rest.dto.mapper.StudentMapper;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.service.externalservice.GoogleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private GoogleClient googleClient;

  private final StudentMapper studentMapper = StudentMapper.getMapper();

  public void signupStudent(StudentDTO studentDTO) {
    try{
      Students students;
      if(!Objects.isNull(studentDTO.getGoogleAccessToken())){
        GoogleClient.GoogleRes googleRes = googleClient.googleLogin(studentDTO.getGoogleAccessToken());
        students = studentMapper.googleResToStudents(googleRes);
      } else {
        students = studentMapper.studentDTOtoStudents(studentDTO);
      }
      studentRepository.save(students);
    } catch (Exception e){
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  public void loginStudent(Students students) {
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
