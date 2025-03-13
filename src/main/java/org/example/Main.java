package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EmployeeService service = new EmployeeService();

        try {
            // Insert Employees
            service.save(new Employee("John Doe", "Engineering"));
            service.save(new Employee("Jane Smith", "Marketing"));
            service.save(new Employee("Alice Brown", "HR"));
            service.save(new Employee("John Wick", "Security"));

            // Fetch All
            System.out.println("\nFetching All Employees:");
            List<Employee> employees = service.findAll();
            employees.forEach(System.out::println);

            // Find by Name
            System.out.println("\nFinding Employees by Name 'John Doe':");
            List<Employee> johns = service.findByName("John Doe");
            johns.forEach(System.out::println);

            // Find by Department
            System.out.println("\nFinding Employees in 'HR' Department:");
            List<Employee> hrEmployees = service.findByDepartment("HR");
            hrEmployees.forEach(System.out::println);

            // Find by Name with LIKE
            System.out.println("\nFinding Employees with Name containing 'John':");
            List<Employee> likeJohn = service.findByNameWithLike("John");
            likeJohn.forEach(System.out::println);

        } finally {
            service.close();
        }


    }
}
