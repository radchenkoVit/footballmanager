package com.bobocode.radchenko.service.impl;

import com.bobocode.radchenko.entity.Team;
import com.bobocode.radchenko.exceptions.EntityNotFoundException;
import com.bobocode.radchenko.repository.team.TeamRepository;
import com.bobocode.radchenko.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    //TODO
    public Team update(Team team) {
        return null;
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

    //TODO
    @Transactional(readOnly = true)
    public Team findByCaptain(long id) {
        return null;
    }

    @Transactional(readOnly = true)
    public boolean existById(long id) {
        return teamRepository.existsById(id);
    }
}
