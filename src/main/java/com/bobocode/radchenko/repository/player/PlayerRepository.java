package com.bobocode.radchenko.repository.player;

import com.bobocode.radchenko.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByLastName(String lastName);
}
