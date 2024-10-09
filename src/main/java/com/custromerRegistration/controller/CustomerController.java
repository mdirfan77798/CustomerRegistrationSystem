package com.custromerRegistration.controller;

import com.custromerRegistration.dto.CustomerDto;
import com.custromerRegistration.entity.Customer;
import com.custromerRegistration.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customerRegistration")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

   @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDto customerDto,BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }

        CustomerDto customer1 = customerService.addCustomer(customerDto);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteRegistrationbyId(@RequestParam Long id){
        String s = customerService.deleteCustomer(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestParam long id, @RequestBody CustomerDto customerDto){
        CustomerDto dto = customerService.updateCustomer(id, customerDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getAllCustomer(
            @RequestParam(name="pageNo", defaultValue ="5", required = false) int pageNo,
            @RequestParam(name="pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name ="sortBy", defaultValue ="name", required = false) String sortBy,
            @RequestParam(name="sortOrder", defaultValue = "name", required = false) String sortOrder
    ){
        List<CustomerDto> allCustomer = customerService.getAllCustomer(pageNo, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(allCustomer,HttpStatus.OK);
    }
    @GetMapping("byId")
    public ResponseEntity<CustomerDto> findCustomerById(@RequestParam long id){
        CustomerDto byId = customerService.findById(id);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }

}
