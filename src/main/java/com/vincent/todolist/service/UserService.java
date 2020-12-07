package com.vincent.todolist.service;

import com.vincent.todolist.model.dao.UserRepository;
import com.vincent.todolist.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userDao;

    public Optional<User> getTodosByUserId(Integer id) {
        Optional<User> data = userDao.findById(id);
        return data;
    }

    public Iterable<User> getUsers() {
        return userDao.findAll();
    }

    public Integer createUser(User user) {
        User rltUser = userDao.save(user);
        return rltUser.getId();
    }

    public Boolean updateUser(Integer id, User user) {
        Optional<User> isExistUser = findById(id);
        if (!isExistUser.isPresent()) {
            return false;
        }
//        User newUser = isExistUser.get();
//        if (user.getStatus() == null) {
//            return false;
//        }
//        newUser.setStatus(user.getStatus());
//        userDao.save(newUser);
        return true;
    }

    public Optional<User> findById(Integer id) {
        Optional<User> user = userDao.findById(id);
        return user;
    }

    public Boolean deleteUser(Integer id) {
        Optional<User> findUser = findById(id);
        if (!findUser.isPresent()) {
            return false;
        }
        userDao.deleteById(id);
        return true;
    }
}