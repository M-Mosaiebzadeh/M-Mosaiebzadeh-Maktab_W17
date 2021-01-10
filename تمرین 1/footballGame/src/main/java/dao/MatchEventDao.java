package dao;

import entities.MatchEvent;

import javax.persistence.EntityManager;

public class MatchEventDao extends AbstractDao<Integer, MatchEvent>{

    public MatchEventDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<MatchEvent> getEntityClass() {
        return MatchEvent.class;
    }
}
