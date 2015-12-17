package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.PracticeJobDTO;
import com.vitonjob.entities.PracticeJob;

public interface IPracticeJobDAO extends IGenericDao<PracticeJob> {

	List<PracticeJobDTO> getPracticesJobByOffer(Long offerId);

}
