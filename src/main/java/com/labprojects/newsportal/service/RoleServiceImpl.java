package com.labprojects.newsportal.service;

import com.labprojects.newsportal.dao.RoleDAOImpl;
import com.labprojects.newsportal.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAOImpl roleDAO;

//    @Override
//    public Role getRole(Long id) {
//        return roleDAO.getRole(id);
//    }
}
