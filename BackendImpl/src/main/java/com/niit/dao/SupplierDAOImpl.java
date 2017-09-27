package com.niit.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Supplier;
import com.niit.model.User;
import com.niit.dao.SupplierDAO;


@Transactional
@Repository("supplierDAO")

public class SupplierDAOImpl implements SupplierDAO
{

	@Autowired
	SessionFactory sessionFactory;
	
	public SupplierDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	// Adding a User
	@Transactional
	@Override
	public boolean addSupplier (Supplier supplier) 
	{
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(supplier);
			System.out.println("Supplier added successfully!!!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Error in adding Supplier!!!");
			System.out.println(e);
			return false;
		}
	}

	// Getting all suppliers created
	@Override
	public List<Supplier> getAllSuppliers() 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Supplier");
		List<Supplier> listSuppliers=query.list();
		System.out.println("Fetched all Suppliers successfully!!!");
		return listSuppliers;
	}

	// Getting supplier by Name
	@Override
		public Supplier getSupplierByName(String supplier_name)
		{
			Supplier supplier=null;
			try
			{
				Session session = sessionFactory.getCurrentSession();
				supplier = (Supplier)session.get(Supplier.class, supplier_name);
				System.out.println("Fetching the Supplier details success!!!");
				return (supplier);
			}
			catch(Exception e)
			{
				System.out.println("Error fetching the Supplier details!!!");
				System.out.println(e);
				return (supplier);
			}
		}
		
	// Deleting a Supplier record
	@Transactional
	@Override
	public boolean deleteSupplier (Supplier supplier) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(supplier);
			System.out.println("Supplier deleted Successfully !!!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Error in deleting Supplier !!!");	
			System.out.println(e);
			return false;
		}
	}
	
}
