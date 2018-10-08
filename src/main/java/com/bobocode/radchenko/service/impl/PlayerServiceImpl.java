package com.bobocode.radchenko.service.impl;

import com.bobocode.radchenko.entity.Player;
import com.bobocode.radchenko.exceptions.EntityNotFoundException;
import com.bobocode.radchenko.repository.player.PlayerRepository;
import com.bobocode.radchenko.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override//TODO
    public Player update(Player player) {
        return null;
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
    public Player findByLastName(String lastName) {
        return playerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new EntityNotFoundException("No Player found with lastName: " + lastName));
    }
}
