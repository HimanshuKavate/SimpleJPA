package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeeService implements EmployeeRepository{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("employeePU");
    EntityManager entityManager = emf.createEntityManager();

    @Override
    public void save(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Employee> findByName(String name) {
        String query = "SELECT * FROM Employee WHERE name = :name";
        return entityManager.createNativeQuery(query, Employee.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Employee> findByDepartment(String department) {
        String query = "SELECT * FROM Employee WHERE dept = :department";
        return entityManager.createNativeQuery(query, Employee.class)
                .setParameter("department", department)
                .getResultList();
    }

    @Override
    public List<Employee> findByNameWithLike(String searchTerm) {
        String query = "SELECT * FROM Employee WHERE name LIKE :searchTerm";
        return entityManager.createNativeQuery(query, Employee.class)
                .setParameter("searchTerm", "%" + searchTerm + "%")
                .getResultList();
    }

    public void close() {
        entityManager.close();
        emf.close();
    }
}
