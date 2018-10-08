package com.bobocode.radchenko.repository.team.custom;

import com.bobocode.radchenko.entity.Player;
import com.bobocode.radchenko.entity.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
@Transactional
public class TeamCustomRepositoryImpl implements TeamCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public Set<Player> findPlayersByTeamId(long id) {
        Team team = em.createQuery("Select t From Team t join fetch t.players where t.id =:id", Team.class)
                .setParameter("id", id)
                .getSingleResult();
        return team.getPlayers();
    }
}
