package com.football.codeSeek.repositories;

import com.football.codeSeek.models.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface TeamRepo extends CrudRepository<Team, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM teams")
    List<Team> getAll();

    @Query(nativeQuery = true,
            value = "SELECT * FROM teams WHERE id = :id")
    Team getById(Long id);

    void deleteById(Long id);

    @Query(nativeQuery = true,
            value = "select * from teams where id NOT IN(select team_id from players where id = :id);")
    List<Team> getTeamsWithoutCurrentTeam(Long id);
}
