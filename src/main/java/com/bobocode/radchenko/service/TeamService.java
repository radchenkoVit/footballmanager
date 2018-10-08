package com.bobocode.radchenko.service;

import com.bobocode.radchenko.entity.Team;

import java.util.List;

public interface TeamService {

    Team save(Team team);
    Team update(Team team);
    void delete(long id);

    Team findById(long id);
    Team findByIdFetchPlayers(long id);
    List<Team> findAll();
    List<Team> findAllFetchPlayers();
    Team findByCaptain(long id);
}
