package dao;

import entities.Competition;

import javax.persistence.EntityManager;

public class CompetitionDao extends AbstractDao<Integer, Competition>{

    public CompetitionDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Competition> getEntityClass() {
        return Competition.class;
    }

}
