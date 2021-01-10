package dao;

import entities.City;
import entities.Team;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityDao extends AbstractDao<Integer, City> {

    public CityDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }

    //تعداد تیم های هر شهر را به همراه نام شهر چاپ میکند
    public void numberOfTeam() {
        CriteriaQuery<Object[]> criteria = getCriteriaBuilder().createQuery(Object[].class);
        Root<Team> fromTeam = criteria.from(Team.class);
        //multiselect => city name, count(team of city) => group by city
        criteria.multiselect(fromTeam.get("city").get("cityName"),getCriteriaBuilder().count(fromTeam))
        .groupBy(fromTeam.get("city").get("cityName"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteria);
        typedQuery.getResultList().forEach(objects -> System.out.println("city: "+objects[0]+", NumOfTeam: "+ objects[1]));
    }

}
