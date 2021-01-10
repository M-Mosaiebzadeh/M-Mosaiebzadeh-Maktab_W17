package util;

import entities.Address;
import entities.Employee;
import entities.Phone;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

public class MyQuery {
    private EntityManager entityManager;
    private CriteriaBuilder builder;

    public MyQuery(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.builder = entityManager.getCriteriaBuilder();
    }

    //این متد بیشترین در امد بر حسب شهر را بر میگرداند
    public List<Object[]> mostSalaryByCity() {
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Employee> fromEmployee = criteria.from(Employee.class);
        Join<Employee, Address> employeeAddressJoin = fromEmployee.join("addresses");

        //multiselect => max salary, city of it => group by city
        criteria.multiselect(builder.max(fromEmployee.get("salary"))
                ,employeeAddressJoin.get("city"))
                .groupBy(employeeAddressJoin.get("city"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    //این متد کارمندنی که بیشترین درآمد را در هر شهر دارند برمیگرداند
    public List<Object[]> empMostSalaryByCity() {
        //extract max salary for employees in city
        List<Double> maxSalary = mostSalaryByCity()
                .stream()
                .map(objects -> (Double)objects[0])
                .collect(Collectors.toList());

        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Employee> fromEmployee = criteria.from(Employee.class);
        Join<Employee, Address> employeeAddressJoin = fromEmployee.join("addresses");

        //multiselect employee ,city where salary is maximum
        criteria.multiselect(fromEmployee,employeeAddressJoin.get("city"))
                .where(fromEmployee.get("salary").in(maxSalary));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();

    }

    //این متد کارمند را بر اساس کدپستی آن پیدا میکند
    public Employee searchByPostalCode(String postalCode) {
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> fromEmployee = criteria.from(Employee.class);
        Join<Employee,Address> employeeAddressJoin = fromEmployee.join("addresses");

        criteria.select(fromEmployee).where(builder.equal(employeeAddressJoin.get("postalCode"),postalCode));

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getSingleResult();
    }

    //این متد کارمند را بر اساس شماره تلفن پیدا میکند
    public Employee searchByTelNumber(String telNumber) {
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> fromEmployee = criteria.from(Employee.class);
        Join<Employee,Address> employeeAddressJoin = fromEmployee.join("addresses");
        Join<Address, Phone> addressPhoneJoin = employeeAddressJoin.join("phones");

        criteria.select(fromEmployee).where(builder.equal(addressPhoneJoin.get("telNumber"),telNumber));

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getSingleResult();
    }

}
