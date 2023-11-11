package com.yufaab.yufaabcore.service;

import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.dao.repository.StudentRepository;
import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserInfoService implements UserDetailsService {

  @Autowired
  private StudentRepository studentRepository;
  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    Students students = studentRepository.findById(id)
            .orElseThrow(()-> new AppException(AppErrorCodes.STUDENT_NOT_FOUND));
    return new User(students.getId(),students.getPassword(),new ArrayList<>());
  }
}
