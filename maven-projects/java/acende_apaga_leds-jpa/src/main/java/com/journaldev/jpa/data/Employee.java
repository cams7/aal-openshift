package com.journaldev.jpa.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

import br.com.cams7.jpa.domain.BaseEntity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Employee extends BaseEntity<String> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;

	private String name;

	private Date hireDate;

	private double salary;

	public Employee() {
		super();
	}

	public Employee(String id) {
		super(id);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[id = " + getId() + ", name = "
				+ getName() + ", hireDate = " + getHireDate() + ", salary = "
				+ getSalary() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
