package com.devunlimit.project.admin.domain.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO {

  int checkAdmin(String id);

  int modify_log(String id);
}
