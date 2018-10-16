package com.bobocode.radchenko.service.impl;

import com.bobocode.radchenko.entity.Player;
import com.bobocode.radchenko.entity.Team;
import com.bobocode.radchenko.exceptions.EntityNotFoundException;
import com.bobocode.radchenko.repository.player.PlayerRepository;
import com.bobocode.radchenko.repository.team.TeamRepository;
import com.bobocode.radchenko.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void remove(long id) {
        Player managed = findById(id);
        playerRepository.delete(managed);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Player findById(long id) {
        return playerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Player found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> findByTeamId(long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    @Override//TODO:CREATE SUPPLIER FOR EXCEPTION
    public void assignTeam(long playerId, long teamId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("No Player found with id: " + playerId));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("No Team found with id: " + playerId));
        player.setTeam(team);
    }

    @Override
    @Transactional(readOnly = true)
    public Player findByLastName(String lastName) {
        return playerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new EntityNotFoundException("No Player found with lastName: " + lastName));
    }
}
