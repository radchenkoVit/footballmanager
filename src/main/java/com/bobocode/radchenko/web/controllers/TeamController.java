package com.bobocode.radchenko.web.controllers;

import com.bobocode.radchenko.entity.Team;
import com.bobocode.radchenko.service.TeamService;
import com.bobocode.radchenko.web.ui.response.TeamShortDto;
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
    @ResponseStatus(HttpStatus.OK)
    public List<TeamShortDto> getAll() {
        List<Team> teams = teamService.findAll();
        return teams.stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamShortDto getOne(@PathVariable(name = "id") Long id) {
        Team managed = teamService.findById(id);
        return toDto(managed);
    }

    private TeamShortDto toDto(Team team) {
        return mapper.map(team, TeamShortDto.class);
    }
}
