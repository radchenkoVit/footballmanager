package com.bobocode.radchenko.repository.team;

import com.bobocode.radchenko.entity.Team;
import com.bobocode.radchenko.repository.team.custom.TeamCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamCustomRepository {

    Optional<Team> findById(long id);

    List<Team> findAll();

    @Query("Select t From Team t left join fetch t.players")
    Set<Team> findAllFetchPlayers();

    @Query("Select t From Team t left join fetch t.players where t.id =:id")
    Optional<Team> findByIdFetchPlayers(@Param(value = "id") long id);

    boolean existsById(long id);

    Optional<Team> findByCaptainId(long id);

}
