package com.bobocode.radchenko.service;

import com.bobocode.radchenko.entity.Player;

import java.util.List;

public interface PlayerService {
    Player save(Player player);
    Player update(Player player);
    void remove(long id);

    List<Player> findAll();
    Player findById(long id);
    void assignTeam(long playerId, long teamId);
    Player findByLastName(String lastName);
}
