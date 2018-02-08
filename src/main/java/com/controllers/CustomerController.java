package com.controllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pojos.Customer;

@RestController("customer")
@RequestMapping("/customer")
@Transactional
public class CustomerController {
    @Autowired
    SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@GetMapping("/")
    public List<Customer> getCustomers() {
	return sessionFactory.getCurrentSession().createQuery("from Customer").list();
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer) {
	try {
	    Session ses = sessionFactory.getCurrentSession();
	    ses.save(customer);
	    return ResponseEntity.status(HttpStatus.CREATED).build();
	} catch (Exception e) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) {
	try {
	    Session ses = sessionFactory.getCurrentSession();
	    ses.update(customer);
	    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	} catch (Exception e) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id) {
	try {
	    Session ses = sessionFactory.getCurrentSession();
	    Customer customer = ses.get(Customer.class, id);
	    if (customer == null) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	    ses.delete(customer);
	    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	} catch (Exception e) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
    }
}
