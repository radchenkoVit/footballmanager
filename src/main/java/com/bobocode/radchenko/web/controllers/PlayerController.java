package com.bobocode.radchenko.web.controllers;

import com.bobocode.radchenko.entity.Player;
import com.bobocode.radchenko.service.PlayerService;
import com.bobocode.radchenko.web.ui.response.PlayerShortDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {

    private final PlayerService playerService;
    private final ModelMapper mapper;

    @Autowired
    public PlayerController(PlayerService playerService, ModelMapper mapper) {
        this.playerService = playerService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerShortDto> getAll() {
        List<Player> players = playerService.findAll();
        return players.stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerShortDto getOne(@PathVariable(name = "id") Long id) {
        Player managedEntity =  playerService.findById(id);
        return toDto(managedEntity);
    }

    private PlayerShortDto toDto(Player player) {
        return mapper.map(player, PlayerShortDto.class);
    }
}
