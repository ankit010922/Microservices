package com.dish.test.mgmt.entity.util;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		if (object instanceof IdentifiableEntity) {
			IdentifiableEntity entity = (IdentifiableEntity) object;
			String prefix = entity.getIdPrefix();
			int randomNumber = (int) (Math.random() * 1000); // Generate a random number (you can adjust the range)
			return prefix + "_" + randomNumber;
		} else {
			throw new HibernateException("Object is not an instance of IdentifiableEntity.");
		}
	}
}