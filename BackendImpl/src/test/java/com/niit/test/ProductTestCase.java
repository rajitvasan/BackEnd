package com.niit.test;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import javax.naming.Context;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.model.User;
import com.niit.dao.UserDAO;

import com.niit.model.Category;
import com.niit.dao.CategoryDAO;

import com.niit.model.Product;
import com.niit.dao.ProductDAO;

import com.niit.model.Supplier;
import com.niit.dao.SupplierDAO;

public class ProductTestCase 
{
	static ProductDAO productDAO;
	@Autowired
	static CategoryDAO categoryDAO;
	@Autowired
	static SupplierDAO supplierDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext annotationConfigAppContext=new AnnotationConfigApplicationContext();
		annotationConfigAppContext.scan("com.niit");
		annotationConfigAppContext.refresh();
		
		productDAO=(ProductDAO)annotationConfigAppContext.getBean("productDAO");
		System.out.println("ProductDAO initiated");
		categoryDAO=(CategoryDAO) annotationConfigAppContext.getBean("categoryDAO");
		System.out.println("CategoryDAO initiated");
		supplierDAO=(SupplierDAO) annotationConfigAppContext.getBean("supplierDAO");
		System.out.println("SupplierDAO initiated");
		
	}
	
	// Adding new Category
	@Test
	public void saveProduct()
	{
		Category c =  new Category();
		c=categoryDAO.getCategoryByName("Formal Shoes");
		
		Supplier s = new Supplier();
		s=supplierDAO.getSupplierByName("FlipKart");
		
		if(c!=null && s!=null)
		{
			Product p =new Product();
			p.setProduct_name("TestProd1");
			p.setPrice("7500");
			p.setBrand("Derby Shoes");
			p.setDescription("Formal Brown Shoes from Derby");
			p.setSupplier(s);
			p.setCategory(c);
			assertTrue("Problem in Adding Product",productDAO.saveProduct(p));
		}
		else
		{
			System.out.println("Category Not Available");
		}
	}
	
	//Getting the Products by Category
	@Test
	public void getProductsByCategory()
	{
		List<Product> productLists=productDAO.getProductsByCategory("Formal Shoes");
		assertTrue("No Products in the selected category",productLists.size()>0);
		System.out.println("The number of products in the category is : " +productLists.size());
	}
		
	
	//Get Product by Name
	@Test
	public void getProductByName()
	{
		String name = "Red Rose";
		Product p = productDAO.getProductByName(name);
		Assert.assertNotNull("Product does NOT exists", p);
	}
		
	//Getting the Category list
	@Test
	public void getProducts()
	{
		List<Product> listProducts=productDAO.getProducts();
		assertTrue("No Products",listProducts.size()>0);
	}
	
	//Updating the Product details
	@Test
	public void updateProduct()
	{
		String description = "Red Rose Stylish Red Casusual Shoes for Men";
		Product p = productDAO.getProductByName("Red Rose");
		if(p != null)
		{
			p.setDescription(description);
			productDAO.updateProduct(p);
			assertTrue("Problem in updating Product",productDAO.updateProduct(p));
		}
	}

	
	//Delete the Product
	@Test
	public void deleteProduct()
	{
		String name = "TestProd1";
		Product p = productDAO.getProductByName(name);
		if(p != null)
		{
			productDAO.deleteProduct(p);
			assertTrue("Problem in deleting product",productDAO.deleteProduct(p));
		}
	}
	
}