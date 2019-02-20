package com.cg.mypaymentapp.beans;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="walletpay")
public class Wallet 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="wal")
	@SequenceGenerator(name="wal",sequenceName="wal1",initialValue=10,allocationSize=1)
	private int wid;

private BigDecimal balance;

public Wallet(BigDecimal amount) {
	this.balance=amount;
}

public BigDecimal getBalance() {
	return balance;
}

public void setBalance(BigDecimal balance) {
	this.balance = balance;
}
public int getWid() {
	return wid;
}

public void setWid(int wid) {
	this.wid = wid;
}
@Override
	public String toString() {
	return " ,balance="+balance;
}

public Wallet() {
	super();
	// TODO Auto-generated constructor stub
}

}
