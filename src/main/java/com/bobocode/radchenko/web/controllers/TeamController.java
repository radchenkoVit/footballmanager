package com.bobocode.radchenko.web.controllers;

import com.bobocode.radchenko.entity.Team;
import com.bobocode.radchenko.service.TeamService;
import com.bobocode.radchenko.web.ui.response.TeamShortDto;
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
@RequestMapping(path = "/team")
public class TeamController {

    private final TeamService teamService;
    private final ModelMapper mapper;

    @Autowired
    public TeamController(TeamService teamService, ModelMapper mapper) {
        this.teamService = teamService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<TeamShortDto>> getAll() {
        List<Team> teams = teamService.findAll();
        List<TeamShortDto> teamsDto = teams.stream().map(this::toDto).collect(toList());
        return new ResponseEntity<>(teamsDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TeamShortDto> getOne(@PathVariable(name = "id") Long id) {
        Team team = teamService.findById(id);
        TeamShortDto teamDto = toDto(team);
        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }

    private TeamShortDto toDto(Team team) {
        return mapper.map(team, TeamShortDto.class);
    }
}
