package org.hibernate.dao;

import org.hibernate.models.Address;
import org.hibernate.models.Employee;

import java.util.List;

public interface AddressDAO {
    List<Address> getAllAddresses();
    Address getAddressById(int id);
    Address createAddress(String addr1, String addr2, String City, int zip, Employee employee);
    Address createAddress(Address addr);
}