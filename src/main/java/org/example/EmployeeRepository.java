package org.example;

import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee);

    Employee findById(Long id);

    List<Employee> findAll();

    void delete(Long id);

    // Native Query methods
    List<Employee> findByName(String name);

    List<Employee> findByDepartment(String department);

    List<Employee> findByNameWithLike(String searchTerm);
}
