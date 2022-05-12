package org.hibernate.services;

import org.hibernate.models.Department;
import org.hibernate.models.Employee;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentServiceTest {

    static DepartmentService testService;
    static Department testDepartment1;
    static Department testDepartment2;
    static Department testDepartment3;
    @BeforeAll
    static void beforeAll() {
        testService = new DepartmentService();

        testDepartment1 = new Department("Photo Electronics", "Covington");
        testDepartment2 = new Department("Apparel", "Kent");
        testDepartment3 = new Department("Pharmacy", "Covington");
        testService.addDepartment(testDepartment1);
        testService.addDepartment(testDepartment2);
        testService.addDepartment(testDepartment3);

        EmployeeService es = new EmployeeService();
        es.createEmployee(new Employee("Dan", 45000));
        es.createEmployee(new Employee("Jordan", 44999));
        es.addEmployeeToDepartment(es.getEmployeeById(1), 1);
        es.addEmployeeToDepartment(es.getEmployeeById(2), 2);
    }

    @BeforeEach
    void setUp() {

    }

    @Order(3)
    @Test
    void testGetAllDepartments1() {
        assertThat(testService.getAllDepartments())
                .isNotNull()
                .containsExactly(testDepartment1, testDepartment2, testDepartment3);
    }

    @Order(2)
    @Test
    void addDepartment() {
        LinkedHashSet<Employee> employees = new LinkedHashSet<>();
        employees.add(new Employee(1, "Dan", 45000, null, null));
        Department department1 = new Department(1, "Photo Electronics", "Covington",
                employees);
        Department departmentInDatabase = testService.getDepartment(1);
        assertEquals(department1, departmentInDatabase);
        assertFalse(testService.addDepartment(null));
        assertTrue(testService.addDepartment("Microsoft", "Renton"));
        assertFalse(testService.addDepartment(null, null));
    }

    @Order(4)
    @Test
    void testRemoveDepartment() {
        assertThat(testService.removeDepartment(1)).isEqualTo(true);
        assertThat(testService.removeDepartment(testDepartment3)).isNotNull().isExactlyInstanceOf(Department.class)
                        .isEqualTo(testDepartment3);
        assertThrows(RuntimeException.class, () -> testService.removeDepartment(0));
        assertThrows(RuntimeException.class, () -> testService.removeDepartment(null));
    }

    @Order(1)
    @Test
    void getDepartment() {
        assertThat(testService.getDepartment(1)).extracting(id -> id.getId()).isEqualTo(1);
    }
}