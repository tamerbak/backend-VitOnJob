package com.vitonjob.dao;

import com.vitonjob.entities.Level;

public interface ILevelDAO extends IGenericDao<Level> {

	Level getLevelByLanguageAndJobyer(Long languageId, Long jobyerId);

	Level getLevelByJobAndJobyer(Long jobId, Long jobyerId);

}
