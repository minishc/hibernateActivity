package org.hibernate.services;

import org.hibernate.models.Address;
import org.hibernate.models.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddressServiceTest {

    static AddressService testService;

    static Address testAddress1;
    static Address testAddress2;

    @BeforeAll
    static void beforeAll() {
        testService = new AddressService();

        EmployeeService service = new EmployeeService();
        service.createEmployee(new Employee("Dan", 45000));

        testAddress1 = new Address("123 st", "", "Shelton", 98584,
                service.getEmployeeById(1));

        testAddress2 = new Address("900 block", "Apartment 144", "Someplace",
                60652, service.getEmployeeById(1));

        if(testService.createAddress(testAddress1)) testAddress1.setId(1);
        if(testService.createAddress(testAddress2)) testAddress2.setId(2);
    }

    @Order(1)
    @Test
    void getAllAddresses() {
        assertThat(testService.getAllAddresses()).isNotNull().hasSize(2)
                .containsOnly(testService.getAddressById(1), testService.getAddressById(2));
    }

    @Test
    void getAddressById() {
        assertThat(testService.getAddressById(1)).isNotNull().isEqualTo(testAddress1);
        assertThat(testService.getAddressById(0)).isNull();
    }

    @Order(2)
    @Test
    void createAddress() {
        EmployeeService es = new EmployeeService();
        es.createEmployee(new Employee("Chris", 41001));
        assertThat(testService.createAddress("1234 main st", "", "Covington", 98042, es.getEmployeeById(2)))
                .isEqualTo(new Address ("1234 main st", "", "Covington", 98042, es.getEmployeeById(2)));
        assertThat(testService.createAddress(new Address("993 1st st", "", "Shelton", 98584, es.getEmployeeById(2))))
                .isEqualTo(true);
        assertThat(testService.getAllAddresses()).isNotNull().hasSize(4).contains(testService.getAddressById(3), testService.getAddressById(4));
        assertThat(testService.createAddress(null)).isEqualTo(false);
        assertThat(testService.createAddress(null, null, null, 0, null)).isEqualTo(null);
    }
}