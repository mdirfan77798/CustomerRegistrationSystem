package com.custromerRegistration.service;

import com.custromerRegistration.dto.CustomerDto;
import com.custromerRegistration.entity.Customer;
import com.custromerRegistration.exception.ResourceNotFound;
import com.custromerRegistration.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer save = customerRepository.save(customer);
        CustomerDto dto = mapToDto(save);
          dto.setMessage("registration is saved");
        return dto;
    }

    @Override
    public String deleteCustomer(long id) {
        customerRepository.deleteById(id);
        return "Registration deleted";
    }

    @Override
    public CustomerDto updateCustomer(long id, CustomerDto customerDto) {
        Optional<Customer> byId = customerRepository.findById(id);
        Customer customer = byId.get();
        customer.setName(customerDto.getName());
        customer.setCity(customerDto.getCity());
        customer.setEmail(customerDto.getEmail());
        customer.setMobile(customerDto.getMobile());
        Customer save = customerRepository.save(customer);
        CustomerDto dto = mapToDto(customer);
        dto.setMessage("registration updated");
        return dto;
    }

    @Override
    public List getAllCustomer(int pageNo, int pageSize, String sortBy, String sortOrder) {
        //List<Customer> all = customerRepository.findAll()
        Sort orders = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        //sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);

        PageRequest request = PageRequest.of(pageNo, pageSize, orders);

        Page<Customer> all = customerRepository.findAll(request);
        List<Customer> content = all.getContent();
        List<CustomerDto> collect = content.stream().map(s -> mapToDto(s)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public CustomerDto findById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Registration not found with id:" + id)
        );

        CustomerDto customerDto = mapToDto(customer);
        customerDto.setMessage("registration found");
        return customerDto;
    }


    Customer mapToEntity(CustomerDto dto){
         Customer entity = new Customer();
         entity.setName(dto.getName());
         entity.setEmail(dto.getEmail());
         entity.setMobile(dto.getMobile());
         entity.setCity(dto.getCity());
         return entity;

     }
     CustomerDto mapToDto(Customer customer){
         CustomerDto dto = new CustomerDto();
         dto.setId(customer.getId());
         dto.setName(customer.getName());
         dto.setCity(customer.getCity());
         dto.setEmail(customer.getEmail());
         dto.setMobile(customer.getMobile());
         return dto;

     }


}
