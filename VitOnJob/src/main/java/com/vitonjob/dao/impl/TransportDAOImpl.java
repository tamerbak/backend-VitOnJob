package com.vitonjob.dao.impl;

import org.springframework.stereotype.Repository;

import com.vitonjob.dao.ITransportDAO;
import com.vitonjob.entities.Transport;

@Repository("transportDAO")
public class TransportDAOImpl extends GenericDAOImpl<Transport>implements ITransportDAO {

}
