package com.devunlimit.project.admin.service.serviceImpl;

import com.devunlimit.project.admin.domain.dao.AdminDAO;
import com.devunlimit.project.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  private AdminDAO adminDAO;

  @Override
  public int checkAdmin(String id) {
    return adminDAO.checkAdmin(id);
  }

  @Override
  public int modify_log(String id) {
    return adminDAO.modify_log(id);
  }
}
