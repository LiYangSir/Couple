package com.quqiququai.service;

import com.quqiququai.NotFoundException;
import com.quqiququai.dao.UserRepository;
import com.quqiququai.po.User;
import com.quqiququai.utils.MD5Utils;
import com.quqiququai.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUserNameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public User getAdverseUser(Long id) {
        return userRepository.findByIdIsNot(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public User updateUser(Long id, User user) {
        User userById = getUserById(id);
        if (userById == null) {
            throw new NotFoundException("该用户不存在");
        }
        BeanUtils.copyProperties(user, userById, MyBeanUtils.getNullPropertyNames(user));
        userById.setUpdateTime(new Date());
        return userRepository.save(userById);
    }

    @Override
    public User updateUser(User user) {
        User userById = getUserById(user.getId());
        BeanUtils.copyProperties(user, userById, MyBeanUtils.getNullPropertyNames(user));
        userById.setUpdateTime(new Date());
        return userRepository.save(userById);
    }
}
