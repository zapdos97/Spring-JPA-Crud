package com.cg.mypaymentapp.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;

@Repository("walletRepo")
public class WalletRepoImpl implements WalletRepo{



	@PersistenceContext
	EntityManager entityManager;
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	@Override
	public boolean save(Customer customer) {
		System.out.println(customer);
		entityManager.persist(customer);
		return false;
	}

	@Transactional	
    @Override
	public Customer findOne(String mobileNo) 
	{	
  
		Query q= entityManager.createQuery("from Customer c where c.mobileNo= ?1");
		q.setParameter(1, mobileNo);
        q.setMaxResults(1);
		List <Customer> list1 = q.getResultList();
		
		Customer c1 = null;
		if(list1.size()>0) {
		   c1=list1.get(0);
		}
		System.out.println(c1);
		if(c1!=null)
		{
			return c1;
		}
		
		return null;
	}
	@Transactional
    @Override
public boolean update(Customer c) 
{
    entityManager.merge(c);
    return true;
}
}
