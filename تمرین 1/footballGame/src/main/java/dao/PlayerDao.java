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

public class PlayerDao extends UserDao{

    public PlayerDao(EntityManager entityManager) {
        super(entityManager);
    }

    // لیست یوزر هایی که بازیکن هستند
    public List<User> players() {
        //main criteria query that return list of users
        CriteriaQuery<User> criteria = getCriteriaBuilder().createQuery(User.class);
        // root for select in user
        Root<User> fromUser = criteria.from(User.class);

        //users that types is player(not coach)
        criteria.select(fromUser).where(getCriteriaBuilder().notEqual(fromUser.type(), Coach.class));

        TypedQuery<User> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }
    //لیست بیشترین دستمزد در هر فصل
    public List<Double> maxSalaryInSeason() {
        //main criteria query that return list salary of players
        CriteriaQuery<Double> criteria = getCriteriaBuilder().createQuery(Double.class);
        // root for select in contract
        Root<Contract> fromContract = criteria.from(Contract.class);

        //max salary where user is player and group by season
        criteria.select(getCriteriaBuilder().max(fromContract.get("salary")))
                .where(fromContract.get("user").in(players()))
                .groupBy(fromContract.get("season"));

        TypedQuery<Double> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    //این متد گران ترین بازیکن هر فصل را نشان میدهد
    public List<Player> expensivePlayerInSeason() {
        //main criteria query that return list of players
        CriteriaQuery<Player> criteria = getCriteriaBuilder().createQuery(Player.class);
        // root for select in players
        Root<Player> fromPlayer = criteria.from(Player.class);
        //join with player and contract
        Join<Player,Contract> contractJoin = fromPlayer.join("contracts");

        //players that salary in max salary
        criteria.select(fromPlayer).where(contractJoin.get("salary").in(maxSalaryInSeason()));

        TypedQuery<Player> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

}
