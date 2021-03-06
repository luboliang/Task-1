package com.jnshu.czm.service.impl;

import com.jnshu.czm.dao.UsersDao;
import com.jnshu.czm.model.Users;
import com.jnshu.czm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("usersService")
public class UsersServiceImpl implements UsersService {


    @Autowired
    private UsersDao usersDao;

    @Override
    public Users findUserById(int userId) {
        Users users = usersDao.findUserById(userId);
        return users;
    }

    @Override
    public void insertUser(Users users) {
        usersDao.insertUser(users);
    }


    @Override
    public  Users findUserByName(String username){
        Users users=usersDao.findUserByName(username);
        return users;
    }

    public boolean updateUser(Users users){
        return usersDao.updateUser(users);
    }
}
