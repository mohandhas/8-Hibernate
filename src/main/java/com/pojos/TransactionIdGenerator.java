package com.pojos;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TransactionIdGenerator implements IdentifierGenerator {
	static long number = 0;
	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyy");
		LocalDate localDate = LocalDate.now();
		number+=1;
		DecimalFormat df = new DecimalFormat("0000000000");
		return "Txn-" + dtf.format(localDate) + "-" + df.format(number);
	}

}
