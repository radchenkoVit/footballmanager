package com.bobocode.radchenko.web.controllers;

import com.bobocode.radchenko.entity.Player;
import com.bobocode.radchenko.service.PlayerService;
import com.bobocode.radchenko.web.ui.response.PlayerDto;
import com.bobocode.radchenko.web.ui.response.PlayerShortDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
    public ResponseEntity<List<PlayerShortDto>> findAll() {
        List<Player> players = playerService.findAll();
        List<PlayerShortDto> playerDto = players.stream().map(this::toDto).collect(toList());
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }

    @GetMapping(path = "/full/all")
    public ResponseEntity<List<PlayerDto>> findFullAll() {
        List<Player> players = playerService.findAll();
        List<PlayerDto> playerDto = players.stream().map(t -> mapper.map(t, PlayerDto.class)).collect(toList());
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlayerShortDto> findById(@PathVariable(name = "id") Long id) {
        Player managedEntity =  playerService.findById(id);
        PlayerShortDto dto = toDto(managedEntity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(path = "/full/{id}")
    public ResponseEntity<PlayerDto> findFullById(@PathVariable(name = "id") Long id) {
        Player managedEntity =  playerService.findById(id);
        PlayerDto dto = mapper.map(managedEntity, PlayerDto.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private PlayerShortDto toDto(Player player) {
        return mapper.map(player, PlayerShortDto.class);
    }
}
