package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.ITimePerTransportDAO;
import com.vitonjob.entities.TimePerTransport;

@Repository("timePerTransportDAO")
public class TimePerTransportDAOImpl extends GenericDAOImpl<TimePerTransport>implements ITimePerTransportDAO {

	@Override
	public Double getTimePerTansportBetweenTwoAddresses(Long jobyerAddressId, Long entrepriseAddressId,
			Long transportId) {
		Query query = getCurrentSession().createQuery(
				"SELECT timePerTrans.valeur FROM TimePerTransport timePerTrans INNER JOIN timePerTrans.jobyerAddress jAddress INNER JOIN timePerTrans.entrepriseAddress entrAddress INNER JOIN timePerTrans.transport transport WHERE jAddress.id = :jobyerAddressId AND entrAddress.id = :entrepriseAddressId AND transport.id = :transportId");
		query.setParameter("jobyerAddressId", jobyerAddressId);
		query.setParameter("entrepriseAddressId", entrepriseAddressId);
		query.setParameter("transportId", transportId);
		query.setMaxResults(1);
		return (Double) query.uniqueResult();
	}

}
