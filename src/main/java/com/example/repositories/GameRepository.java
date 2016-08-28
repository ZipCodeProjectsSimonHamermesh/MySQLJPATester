package com.example.repositories;

import com.example.models.Game;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */
@Transactional
public interface GameRepository  extends CrudRepository<Game,Long>
{

    public Game findById(long id);

    public Game findByName(String name);

}
