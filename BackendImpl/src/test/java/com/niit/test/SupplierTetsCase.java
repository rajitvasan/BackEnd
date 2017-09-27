package com.niit.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.SupplierDAO;
import com.niit.model.Supplier;

public class SupplierTetsCase 
{

	static SupplierDAO supplierDAO;
	
	@BeforeClass
	public static void initialize()
	{
		

		AnnotationConfigApplicationContext annotationConfigAppContext=new AnnotationConfigApplicationContext();
		annotationConfigAppContext.scan("com.niit");
		annotationConfigAppContext.refresh();
		supplierDAO=(SupplierDAO)annotationConfigAppContext.getBean("supplierDAO");
	}
	
	// Adding new User
//	@Test
	public void addSupplier()
	{
		Supplier supplier =new Supplier();
		supplier.setSupplier_name("FlipKart1");
		assertTrue("Problem in Adding Supplier",supplierDAO.addSupplier(supplier));
	}
	
	//Getting the details
	@Test
	public void getAllSuppliers()
	{
		List<Supplier> listSuppliers=supplierDAO.getAllSuppliers();
		assertTrue("No Suppliers ",listSuppliers.size()>0);
		System.out.println("The total number of suppliers : "+listSuppliers.size());
	}
	
	// Getting the Supplier details
	@Test
	public void getSupplier()
	{
		String supplier_name = "eBay";
		Supplier supplier = supplierDAO.getSupplierByName(supplier_name);
		Assert.assertNotNull("Supplier does NOT exists", supplier);
	}
	
	
	//Delete the record
	@Test
	public void deleteSupplier()
	{
		String supplier_name = "FlipKart1";
		Supplier supplier = supplierDAO.getSupplierByName(supplier_name);
		System.out.println("The supplier to be deleted is : "+supplier.getSupplier_name());
		if(supplier != null)
		{
			supplierDAO.deleteSupplier(supplier);
			assertTrue("Problem in deleting user",supplierDAO.deleteSupplier(supplier));
		}
	}
	
		
}
