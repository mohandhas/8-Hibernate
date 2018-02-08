package com.pojos;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customers")
public class Customer {
	int id;
	String name;
	String phone;
	Set<Order> orders;

	public Customer() {
		super();
	}

	public Customer(String name, Set<Order> orders, String phone) {
		super();
		this.name = name;
		this.orders = orders;
		this.phone = phone;
	}

	public Customer(int id, String name, Set<Order> orders, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.orders = orders;
		this.phone = phone;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
