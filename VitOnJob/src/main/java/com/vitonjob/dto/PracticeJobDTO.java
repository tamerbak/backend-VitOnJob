package com.vitonjob.dto;

public class PracticeJobDTO {

	private Long pricticeJobId;

	private String job;

	private String level;

	public PracticeJobDTO() {
	}

	public PracticeJobDTO(Long pricticeJobId, String job, String level) {
		super();
		this.pricticeJobId = pricticeJobId;
		this.job = job;
		this.level = level;
	}

	public Long getPricticeJobId() {
		return pricticeJobId;
	}

	public void setPricticeJobId(Long pricticeJobId) {
		this.pricticeJobId = pricticeJobId;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
