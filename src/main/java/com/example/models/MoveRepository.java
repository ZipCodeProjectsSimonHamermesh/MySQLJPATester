package com.example.models;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */

@Transactional
public interface MoveRepository extends CrudRepository<Move,Long>{
}
