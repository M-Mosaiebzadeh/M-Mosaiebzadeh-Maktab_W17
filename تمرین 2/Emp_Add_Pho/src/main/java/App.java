import com.github.javafaker.Faker;

import util.MyQuery;

import static util.Factory.*;

import javax.persistence.EntityManager;


public class App {
    static Faker faker = new Faker();
    public static void main(String[] args) {
        EntityManager entityManager = createEntityManagerFactory().createEntityManager();
        MyQuery myQuery = new MyQuery(entityManager);
        entityManager.getTransaction().begin();

        //شهر با بیشترین درآمد
//        myQuery.mostSalaryByCity()
//                .forEach(objects -> System.out.println("city: "+ objects[1] + " salary: " + objects[0]));

        //بیشترین درآمد کارمندان هر شهر
//        myQuery.empMostSalaryByCity()
//                .forEach(objects -> System.out.println(objects[0] + " city: " + objects[1]));

        //جست و جوی کارمند براساس کدپستی
//        System.out.println(myQuery.searchByPostalCode("212343"));

        //جست وجوی کارمند بر اساس شماره تلفن
//        System.out.println(myQuery.searchByTelNumber("124200"));
//

        entityManager.getTransaction().commit();
        entityManager.close();
        shutdown();
    }
}
