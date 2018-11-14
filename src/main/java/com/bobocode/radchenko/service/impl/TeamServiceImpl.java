package com.bobocode.radchenko.service.impl;

import com.bobocode.radchenko.entity.Player;
import com.bobocode.radchenko.entity.Team;
import com.bobocode.radchenko.exceptions.EntityNotFoundException;
import com.bobocode.radchenko.repository.team.TeamRepository;
import com.bobocode.radchenko.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static java.lang.String.format;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team update(Team team) {
        return teamRepository.save(team);
    }

    public void delete(long id) {
        Team managed = findById(id);
        teamRepository.delete(managed);
    }

    @Transactional(readOnly = true)
    public Team findById(long id) {
        return teamRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Team findByIdFetchPlayers(long id) {
        return teamRepository
                .findByIdFetchPlayers(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Team> findAllFetchPlayers() {
        return teamRepository.findAllFetchPlayers();
    }

    @Transactional(readOnly = true)
    public Team findByCaptain(long id) {
        return teamRepository
                .findByCaptainId(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Team with captain id %s not found", id)));
    }

    @Override
    public void addPlayer(long teamId, Player player) {
        Team team = findById(teamId);
        team.addPlayer(player);
    }

    @Override
    public void removePlayer(long teamId, Player player) {
        Team team = findById(teamId);
        team.removePlayer(player);
    }

    @Override
    public Set<Player> findPlayersByTeamId(long teamId) {
        return teamRepository.findPlayersByTeamId(teamId);
    }

    @Transactional(readOnly = true)
    public boolean existById(long id) {
        return teamRepository.existsById(id);
    }
}
