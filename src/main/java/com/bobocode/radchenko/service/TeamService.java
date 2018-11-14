package com.bobocode.radchenko.service;

import com.bobocode.radchenko.entity.Player;
import com.bobocode.radchenko.entity.Team;

import java.util.List;
import java.util.Set;

public interface TeamService {

    Team save(Team team);
    Team update(Team team);
    void delete(long id);

    Team findById(long id);
    Team findByIdFetchPlayers(long id);
    List<Team> findAll();
    List<Team> findAllFetchPlayers();
    Team findByCaptain(long id);
    void addPlayer(long teamId, Player player);
    void removePlayer(long teamId, Player player);
    Set<Player> findPlayersByTeamId(long teamId);
    boolean existById(long id);
}
