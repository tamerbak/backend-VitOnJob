package com.vitonjob.dto;

public class PracticeLanguageDTO {

	private Long pricticeLanguageId;

	private String language;

	private String level;

	public PracticeLanguageDTO() {
	}

	public PracticeLanguageDTO(Long pricticeLanguageId, String language, String level) {
		super();
		this.pricticeLanguageId = pricticeLanguageId;
		this.language = language;
		this.level = level;
	}

	public Long getPricticeLanguageId() {
		return pricticeLanguageId;
	}

	public void setPricticeLanguageId(Long pricticeLanguageId) {
		this.pricticeLanguageId = pricticeLanguageId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
