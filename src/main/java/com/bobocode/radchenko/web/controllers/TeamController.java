package com.bobocode.radchenko.web.controllers;

import com.bobocode.radchenko.entity.Player;
import com.bobocode.radchenko.entity.Team;
import com.bobocode.radchenko.exceptions.EntityNotFoundException;
import com.bobocode.radchenko.service.TeamService;
import com.bobocode.radchenko.web.ui.request.PlayerRequestDto;
import com.bobocode.radchenko.web.ui.request.TeamRequestDto;
import com.bobocode.radchenko.web.ui.response.PlayerShortDto;
import com.bobocode.radchenko.web.ui.response.TeamDto;
import com.bobocode.radchenko.web.ui.response.TeamShortDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

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
        List<TeamShortDto> teamsDto = teams.stream().map(t -> toDto(t, TeamShortDto.class)).collect(toList());
        return new ResponseEntity<>(teamsDto, HttpStatus.OK);
    }

    @GetMapping(path = "/full/all")
    public ResponseEntity<List<TeamDto>> getFullAll() {
        List<Team> teams = teamService.findAllFetchPlayers();
        List<TeamDto> teamsDto = teams.stream().map(t -> toDto(t, TeamDto.class)).collect(toList());
        return new ResponseEntity<>(teamsDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TeamShortDto> getOne(@PathVariable(name = "id") Long id) {
        Team team = teamService.findById(id);
        TeamShortDto teamDto = toDto(team, TeamShortDto.class);
        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }

    @GetMapping(path = "/full/{id}")
    public ResponseEntity<TeamDto> getFullOne(@PathVariable(name = "id") Long id) {
        Team team = teamService.findByIdFetchPlayers(id);
        TeamDto teamDto = toDto(team, TeamDto.class);
        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody TeamRequestDto teamDto) {
        Team newTeam = mapper.map(teamDto, Team.class);
        teamService.save(newTeam);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TeamDto> update(@PathVariable(name = "id") Long id, @RequestBody TeamRequestDto teamDto) {
        if (!Objects.equals(id, teamDto.getId())) {
            throw new RuntimeException("Not the same ID");
        }

        if (!teamService.existById(id)) {
            throw new EntityNotFoundException();
        }

        Team team = mapper.map(teamDto, Team.class);
        Team updated = teamService.update(team);
        TeamDto updatedDto = mapper.map(updated, TeamDto.class);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        if(!teamService.existById(id)) {
            throw new EntityNotFoundException("MESSAGE");
        }

        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/captain")
    public ResponseEntity<TeamShortDto> findTeamByCaptain(@RequestParam(name = "id") Long id) {
        Team team = teamService.findByCaptain(id);
        TeamShortDto returnValue = mapper.map(team, TeamShortDto.class);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/add/player")
    public ResponseEntity addPlayer(@PathVariable(name = "id") Long teamId, @RequestBody PlayerRequestDto playerDto) {
        Player player = mapper.map(playerDto, Player.class);
        teamService.addPlayer(teamId, player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}/remove/player")
    public ResponseEntity removePlayer(@PathVariable(name = "id") Long teamId, @RequestBody PlayerRequestDto playerDto) {
        Player player = mapper.map(playerDto, Player.class);
        teamService.removePlayer(teamId, player);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}/all/player")//TODO: make a Collection?
    public ResponseEntity<Set<PlayerShortDto>> getPlayerByTeam(@PathVariable(name = "id") Long teamId) {
        Set<Player> dtoSet = teamService.findPlayersByTeamId(teamId);
        Set<PlayerShortDto> playerShortDtos = dtoSet.stream().map(player -> mapper.map(player, PlayerShortDto.class)).collect(toSet());
        return new ResponseEntity<>(playerShortDtos, HttpStatus.OK);
    }
    private <T> T toDto(Team team, Class<T> clazz) {
        return mapper.map(team, clazz);
    }
}
