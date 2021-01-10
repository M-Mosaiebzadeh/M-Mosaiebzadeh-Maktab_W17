package dao;

import entities.Competition;
import entities.Team;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TeamDao extends AbstractDao<Integer, Team> {

    public TeamDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Team> getEntityClass() {
        return Team.class;
    }


    // مجموع امتیازی که تیم ها را در سال دلخواه در خانه کسب کرده اند به همراه اسم تیم به صورت مرتب شده بر اساس نام تیم میدهد
    public List<Object[]> homeScoreInYear(Integer year) {
        //main criteria query that return list of arrays
        CriteriaQuery<Object[]> criteria = getCriteriaBuilder().createQuery(Object[].class);
        // root for select in Competition
        Root<Competition> fromCompetition = criteria.from(Competition.class);

        //multiselect => home team name, sum of score in home in special year
        criteria.multiselect(fromCompetition.get("homeTeam").get("teamName")
                , getCriteriaBuilder().sum(fromCompetition.get("homeScore")))
                .where(getCriteriaBuilder().equal(fromCompetition.get("season"), year))
                //if dont group by,query sum of all home team scores(ex:2 ,3 ,1 => 6)
                .groupBy(fromCompetition.get("homeTeam").get("teamName"))
                //order bu team names to can use it for future
                .orderBy(getCriteriaBuilder().asc(fromCompetition.get("homeTeam").get("teamName")));


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    // مجموع امتیازی که تیم ها را در سال دلخواه در زمین حریف کسب کرده اند به همراه اسم تیم به صورت مرتب شده بر اساس نام تیم میدهد
    public List<Object[]> awayScoreInYear(Integer year) {
        //main criteria query that return list of arrays
        CriteriaQuery<Object[]> criteria = getCriteriaBuilder().createQuery(Object[].class);

        // root for select in Competition
        Root<Competition> fromCompetition = criteria.from(Competition.class);

        //multiselect => away team name, sum of score in home in special year
        criteria.multiselect(fromCompetition.get("awayTeam").get("teamName")
                , getCriteriaBuilder().sum(fromCompetition.get("awayScore")))
                .where(getCriteriaBuilder().equal(fromCompetition.get("season"), year))
                //if dont group by,query sum of all away team scores(ex:6 ,2 ,1 => 9)
                .groupBy(fromCompetition.get("awayTeam").get("teamName"))
                //order bu team names to can use it for future
                .orderBy(getCriteriaBuilder().asc(fromCompetition.get("awayTeam").get("teamName")));


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    //این متد مپی از تیم هارو به همراه اسم تیم و امتیاز تیم ها در سال خاصی نشان میدهند
    public Map<String, Long> scoresInYear(Integer year) {
        //list of teams name with they're scores in home and away
        List<Object[]> homeScore = homeScoreInYear(year);
        List<Object[]> awayScore = awayScoreInYear(year);
        // map with team name and (home+away) score
        Map<String, Long> teamScores = new HashMap<>();

        for (int i = 0; i < awayScore.size(); i++) {
            teamScores.put((String) homeScore.get(i)[0], ((Long) awayScore.get(i)[1] + (Long) homeScore.get(i)[1]));
        }
        return teamScores;
    }


    //این متد اسم تیم قهرمان به همراه امتیاز ان را در سال خاصی نشان میدهد
    public void heroOfYear(Integer year) {
        Map<String, Long> teamScores = scoresInYear(year);
        //map that store one element => hero team name, score of it
        Map.Entry<String, Long> max = Collections.max(teamScores.entrySet(), (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        System.out.println("Hero Name: " + max.getKey() + " Score is: " + max.getValue());
    }


}
