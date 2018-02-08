package com.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "orders")
public class Order {
    String id;
    Customer customer;
    double amount;

    public Order() {
	super();
    }

    public Order(String id, Customer customer, double amount) {
	super();
	this.id = id;
	this.customer = customer;
	this.amount = amount;
    }

    public Order(Customer customer, double amount) {
	super();
	this.customer = customer;
	this.amount = amount;
    }

    @Id
    @GenericGenerator(name = "transaction-id", strategy = "com.hibernate.model.TransactionIdGenerator")
    @GeneratedValue(generator = "transaction-id")
    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
	return customer;
    }

    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

    public double getAmount() {
	return amount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

}
