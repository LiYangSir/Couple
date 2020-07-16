package com.quqiququai.dao;

import com.quqiququai.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUserNameAndPassword(String username, String password);

    User findByIdIsNot(Long id);
}
