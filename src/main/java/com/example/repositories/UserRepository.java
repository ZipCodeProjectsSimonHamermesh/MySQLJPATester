package com.example.repositories;

import com.example.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by simonhamermesh on 3/7/16.
 */
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {


    public User findByEmail(String email);

    public User findById(Long id);
}
