package com.example.repositories;

import com.example.models.Move;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SIMONTHEPIMON on 8/28/2016.
 */

@Transactional
public interface MoveRepository extends CrudRepository<Move,Long>{

    public List<Move> findByGameId(long gameId);
}
