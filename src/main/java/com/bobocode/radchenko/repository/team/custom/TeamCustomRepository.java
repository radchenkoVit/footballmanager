package com.bobocode.radchenko.repository.team.custom;

import com.bobocode.radchenko.entity.Player;

import java.util.Set;

public interface TeamCustomRepository {
    Set<Player> findPlayersByTeamId(long id);
}
