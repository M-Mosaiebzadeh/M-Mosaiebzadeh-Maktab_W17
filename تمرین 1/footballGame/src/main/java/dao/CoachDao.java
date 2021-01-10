package dao;


import entities.Coach;
import entities.Contract;
import entities.Player;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class CoachDao extends UserDao {

    public CoachDao(EntityManager entityManager) {
        super(entityManager);
    }

    // چون از ارث بری برای بازیکن و مربی استفاده شده،از این متد برای یوزر هایی که فقط مربی هستند استفاده میکنیم
    public List<User> coaches() {
        //main criteria query that return list of users
        CriteriaQuery<User> criteria = getCriteriaBuilder().createQuery(User.class);
        // root for select in user
        Root<User> fromUser = criteria.from(User.class);

        //users that types is coach(not player)
        criteria.select(fromUser).where(getCriteriaBuilder().notEqual(fromUser.type(), Player.class));

        TypedQuery<User> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    // این متد بیشترین قیمت قراردادی که تا به حال برای یک مربی بسته شده است را نشان میدهد
    public List<Double> maxSalary() {
        //main criteria query that return list of coach salary
        CriteriaQuery<Double> criteria = getCriteriaBuilder().createQuery(Double.class);
        // root for select in contract
        Root<Contract> fromContract = criteria.from(Contract.class);

        //extracts max of salary for coach
        criteria.select(getCriteriaBuilder().max(fromContract.get("salary")))
                .where(fromContract.get("user").in(coaches()));

        TypedQuery<Double> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }


    //این متد گرون ترین مربی را نشان میدهد
    public List<Coach> expensiveCoach() {
        //main criteria query that return list of coaches
        CriteriaQuery<Coach> criteria = getCriteriaBuilder().createQuery(Coach.class);
        // root for select in coach
        Root<Coach> fromCoach = criteria.from(Coach.class);
        //join with coach and contract
        Join<Coach, Contract> contractJoin = fromCoach.join("contracts");

        //select coach that salary is equal to (salary of expensive coach)
        criteria.select(fromCoach).where(contractJoin.get("salary").in(maxSalary()));

        TypedQuery<Coach> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }
}
