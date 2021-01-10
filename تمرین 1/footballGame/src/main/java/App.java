import com.github.javafaker.Faker;
import dao.*;
import entities.*;

import javax.persistence.EntityManager;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static entities.enums.MatchEventType.INJURY;
import static entities.enums.MatchEventType.YELLOW_CARD;
import static entities.enums.MatchEventType.RED_CARD;
import static util.EntityManagerFactoryUtil.createEntityManagerFactory;
import static util.EntityManagerFactoryUtil.shutdown;


public class App {
    static UserDao userDao;
    static PlayerDao playerDao;
    static CoachDao coachDao;
    static CityDao cityDao;
    static CompetitionDao competitionDao;
    static ContractDao contractDao;
    static MatchEventDao matchEventDao;
    static StadiumDao stadiumDao;
    static TeamDao teamDao;
    static Faker faker = new Faker();

    public static void main(String[] args) {
        EntityManager entityManager = createEntityManagerFactory().createEntityManager();
        daoInitialization(entityManager);
        entityManager.getTransaction().begin();

        // گرانترین مربی به همراه دستمزد آن
//        coachDao.expensiveCoach().forEach(System.out::println);

        // فهرست گران ترین بازیکن در هر فصل
//        playerDao.expensivePlayerInSeason().forEach(System.out::println);

        //فهرست شهر ها و تعداد تیم های آن ها
//        cityDao.numberOfTeam();

        //برای سال 2020 مجموع امتیازهای هر تیم در خانه
//        teamDao.homeScoreInYear(2020)
//                .forEach(objects -> System.out.println("team:" + objects[0] +" score: " + objects[1]));

        //برای سال 2020 مجموع امتیازهای هر تیم در خانه حریف
//        teamDao.awayScoreInYear(2020)
//                .forEach(objects -> System.out.println("team:" + objects[0] +" score: " + objects[1]));

        // برای سال 2020 مجموع امتیاز های هر تیم
//        teamDao.scoresInYear(2020).entrySet()
//                .forEach(entrySet -> System.out.println("team: "+entrySet.getKey() +"  score: "+entrySet.getValue()));

        //قهرمان سال 2020
//        teamDao.heroOfYear(2020);


        entityManager.getTransaction().commit();
        entityManager.close();
        shutdown();
    }

    public static void daoInitialization(EntityManager entityManager) {
        userDao = new UserDao(entityManager);
        playerDao = new PlayerDao(entityManager);
        coachDao = new CoachDao(entityManager);
        competitionDao = new CompetitionDao(entityManager);
        cityDao = new CityDao(entityManager);
        contractDao = new ContractDao(entityManager);
        matchEventDao = new MatchEventDao(entityManager);
        stadiumDao = new StadiumDao(entityManager);
        teamDao = new TeamDao(entityManager);
    }
}
