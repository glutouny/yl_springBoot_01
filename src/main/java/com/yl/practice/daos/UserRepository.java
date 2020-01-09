package com.yl.practice.daos;

import com.yl.practice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2019/1/9  15:31
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);

    User findByUserNameAndAndEmail(String userName,String email);
}
