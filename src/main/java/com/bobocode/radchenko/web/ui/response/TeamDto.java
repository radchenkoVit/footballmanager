package com.bobocode.radchenko.web.ui.response;

import java.util.Set;

public class TeamDto {
    private Long id;
    private String name;
    private Long captainId;
    private Set<PlayerShortDto> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaptainId() {
        return captainId;
    }

    public void setCaptainId(Long captainId) {
        this.captainId = captainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlayerShortDto> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerShortDto> players) {
        this.players = players;
    }
}
