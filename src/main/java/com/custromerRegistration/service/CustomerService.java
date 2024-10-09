package com.custromerRegistration.service;

import com.custromerRegistration.dto.CustomerDto;
import com.custromerRegistration.entity.Customer;

import java.util.List;

public interface CustomerService {
    public CustomerDto addCustomer(CustomerDto customerDto);
    public String deleteCustomer(long id);
    public CustomerDto updateCustomer(long id,CustomerDto customerDto);


    List getAllCustomer(int pageSize, int pageNo,String sortBy, String sortOrder);


    public CustomerDto findById(long id);
}
