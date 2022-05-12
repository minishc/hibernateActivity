package org.hibernate.services;

import org.hibernate.dao.AddressDAO;
import org.hibernate.models.Address;
import org.hibernate.models.Employee;

import java.util.List;

public class AddressService implements AddressDAO {
    @Override
    public List<Address> getAllAddresses() {
        return null;
    }

    @Override
    public Address getAddressById(int id) {
        return null;
    }

    @Override
    public Address createAddress(String addr1, String addr2, String City, int zip, Employee employee) {
        return null;
    }

    @Override
    public Address createAddress(Address addr) {
        return null;
    }
}
