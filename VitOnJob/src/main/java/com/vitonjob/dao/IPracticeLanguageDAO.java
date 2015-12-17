package com.vitonjob.dao;

import java.util.List;

import com.vitonjob.dto.PracticeLanguageDTO;
import com.vitonjob.entities.PracticeLanguage;

public interface IPracticeLanguageDAO extends IGenericDao<PracticeLanguage> {

	List<PracticeLanguageDTO> getPracticeLanguageByOffer(Long offerId);

}
