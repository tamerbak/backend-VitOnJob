package com.vitonjob.dao;

import com.vitonjob.entities.TimePerTransport;

public interface ITimePerTransportDAO extends IGenericDao<TimePerTransport> {

	/**
	 * Gets the time per tansport between two addresses.
	 *
	 * @param jobyerAddressId
	 *            the jobyer address id
	 * @param entrepriseAddressId
	 *            the entreprise address id
	 * @param transportId
	 *            the transport id
	 * @return the time per tansport between two addresses
	 */
	Double getTimePerTansportBetweenTwoAddresses(Long jobyerAddressId, Long entrepriseAddressId, Long transportId);
}
