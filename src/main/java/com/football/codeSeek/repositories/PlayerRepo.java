package com.football.codeSeek.repositories;

import com.football.codeSeek.models.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepo extends CrudRepository<Player, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM players WHERE id = :id")
    Player getById(Long id);

    void deleteById(Long id);
}
