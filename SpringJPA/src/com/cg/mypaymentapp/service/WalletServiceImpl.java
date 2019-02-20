package com.cg.mypaymentapp.service;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;

@Service("walletService")
public class WalletServiceImpl implements WalletService {

	@Autowired
WalletRepo walletRepo;

	Boolean status = false;

	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) {
		
		Wallet w = new Wallet(amount);
		Customer c = new Customer(name, mobileno, w);
		status = walletRepo.save(c);
		if (status) {
			return c;
		} else {
			return null;
		}
	}

	@Override
	public Customer showBalance(String mobileno)
	{

		Customer c1=walletRepo.findOne(mobileno);
       if(c1!=null)
       {	
		return c1;
		}
		else
		{
			throw new InvalidInputException("Invalid mobileno");
		}
		
	}


	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
	{
		Customer c1=null,c2=null;
		BigDecimal walletbal1, newbalsource, newbaltarget,walletbal2;
		c1=walletRepo.findOne(sourceMobileNo);
		c2=walletRepo.findOne(targetMobileNo);
	System.out.println(c1);
		 if(c1!=null&&c2!=null)
		 {
		walletbal1 = c1.getWallet().getBalance();
		int res=walletbal1.compareTo(amount);
		if(res==1)
		{
		newbalsource = walletbal1.subtract(amount);
		Wallet w1=new Wallet(newbalsource);
		c1.setWallet(w1);
		status=walletRepo.update(c1);
		walletbal2 = c2.getWallet().getBalance();
		newbaltarget = walletbal2.add(amount);
		Wallet w2=new Wallet(newbaltarget);
		c2.setWallet(w2);
		status=walletRepo.update(c2);
		return c1;
		}
		else
	       {
			throw new InsufficientBalanceException("Insufficient Balance");
	       }
	       }
	       else
	       {
	    	   throw new InvalidInputException("Invalid mobile number");
	       }
	}
	

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) 
	{
		Customer c1=null;
		BigDecimal walletbal,newbal;
		c1=walletRepo.findOne(mobileNo);
       if(c1!=null)
       {
		walletbal = c1.getWallet().getBalance();
		newbal = walletbal.add(amount);
		Wallet w=new Wallet(newbal);
		c1.setWallet(w);
		status=walletRepo.update(c1);
		return c1;
       }
       else
       {
    	   throw new InvalidInputException("Invalid mobile number");
       }
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) 
	{
		Customer c1=null;
		BigDecimal walletbal,newbal;
		c1= walletRepo.findOne(mobileNo);
       if(c1!=null)
       {
		walletbal = c1.getWallet().getBalance();
		int res=walletbal.compareTo(amount);
		if(res==1)
		{
		newbal = walletbal.subtract(amount);
		Wallet w=new Wallet(newbal);
		c1.setWallet(w);
		status=walletRepo.update(c1);
		return c1;
       }
       else
       {
    	   throw new InsufficientBalanceException("Insufficient Balance");
       }
       }
       else
       {
    	   throw new InvalidInputException("Invalid mobile number");
       }
       
	}

	public Customer validate(String mobileno) {
		Customer c = walletRepo.findOne(mobileno);
		return c;
	}

}
