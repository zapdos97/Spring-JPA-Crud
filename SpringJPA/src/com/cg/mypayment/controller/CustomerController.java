package com.cg.mypayment.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.service.WalletService;

@Controller
public class CustomerController 
{
	@Autowired
	WalletService walletService;
	Customer customer=null;
	@RequestMapping("/register")
	public String showProductform()
	{	
		return "register";
	}

	@RequestMapping("/login")
	public String loginlink() {
		return "login";

	}
	@RequestMapping("/save")
	public ModelAndView create(HttpServletRequest request)
	{
		ModelAndView modelView = new ModelAndView();
		String name=request.getParameter("name");
		String mobile=request.getParameter("mobileNo");
		String balance=request.getParameter("balance");
		BigDecimal amount=new BigDecimal(balance);
		Customer c=walletService.createAccount(name,mobile,amount);
		if(c!=null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("status", "Login successfully!..");
		    modelView.setViewName("login");
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("status", "Register failed..");
		    modelView.setViewName("register");
		}
		return modelView;
	}

	@RequestMapping("/loginvalidation")
	public ModelAndView login(HttpServletRequest request) {
		
		ModelAndView modelView = new ModelAndView();
		
		String mobileno = request.getParameter("mobileNo");
		
		customer=walletService.validate(mobileno);
		
		if (customer!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", customer.getName());
			modelView.setViewName("service");
		} else {
			modelView.addObject("status", "Invalid mobile number!!");
			modelView.setViewName("login");

		}
		return modelView;
	}
	@RequestMapping("/showbalance")
	public String showbalance(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		Customer c=walletService.showBalance(customer.getMobileNo());
		session.setAttribute("balance",c.getWallet().getBalance() );
		session.setAttribute("name",c.getName() );
		return "showbalance";
	}
	@RequestMapping("/fundtransfer")
	public String fund()
	{
		return "fundtransfer";
	}
	@RequestMapping("/fundtrans")
	public ModelAndView fundtransfer(HttpServletRequest request)
	{
		ModelAndView model=new ModelAndView();
		String sourcemobile=customer.getMobileNo();
		String targetmobile=request.getParameter("targetMobile");
		//System.out.println(targetmobile);
		String balance=request.getParameter("amount");
		BigDecimal amount=new BigDecimal(balance);
		Customer c=walletService.fundTransfer(sourcemobile,targetmobile,amount);
		HttpSession session=request.getSession();
		session.setAttribute("balance",c.getWallet().getBalance() );
		model.setViewName("showbalance");
		return model;
	}
	@RequestMapping("/deposit")
	public String deposit()
	{
		return "deposit";
	}
	@RequestMapping("/depo")
	public  ModelAndView  deposit(HttpServletRequest request)
	{
		ModelAndView model=new ModelAndView();
		String mobile=customer.getMobileNo();
		String balance=request.getParameter("amount");
		BigDecimal amount=new BigDecimal(balance);
		Customer c=walletService.depositAmount(mobile, amount);
		HttpSession session=request.getSession();
		session.setAttribute("balance",c.getWallet().getBalance() );
		model.setViewName("showbalance");
		return model;
	}
	@RequestMapping("/withdraw")
	public String withdraw()
	{
		return "withdraw";
	}
	@RequestMapping("/withdraw1")
	public String withdraw(HttpServletRequest request)
	{

		String mobile=customer.getMobileNo();
		String balance=request.getParameter("amount");
		BigDecimal amount=new BigDecimal(balance);
		Customer c=walletService.withdrawAmount(mobile, amount);
		HttpSession session=request.getSession();
		session.setAttribute("balance",c.getWallet().getBalance() );
		return "showbalance";
	}
	@RequestMapping("/viewprofile")
	public String viewprofile(HttpServletRequest request)
	{
		Customer c=walletService.validate(customer.getMobileNo());
		HttpSession session=request.getSession();
		session.setAttribute("mobileNumber",c.getMobileNo());
		session.setAttribute("name",c.getName());
		session.setAttribute("balance",c.getWallet().getBalance());
		return "viewprofile";
	}
	@RequestMapping("/service")
	public String viewprofile()
	{
		return "service";
	}
}
