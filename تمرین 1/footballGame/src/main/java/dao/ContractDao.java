package dao;

import entities.Contract;

import javax.persistence.EntityManager;

public class ContractDao extends AbstractDao<Integer, Contract> {

    public ContractDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Contract> getEntityClass() {
        return Contract.class;
    }
}
