package com.vitonjob.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitonjob.dao.IIndexationEmployeurDAO;
import com.vitonjob.dao.IIndexationJobyerDAO;
import com.vitonjob.dao.IJobDAO;
import com.vitonjob.dao.ILanguageDAO;
import com.vitonjob.dao.IMotCleDAO;
import com.vitonjob.dao.IPaysDAO;
import com.vitonjob.dao.IVilleDAO;
import com.vitonjob.dto.RechercheEmployeurDTO;
import com.vitonjob.dto.RechercheJobyerDTO;
import com.vitonjob.entities.IndexationEmployeur;
import com.vitonjob.entities.IndexationJobyer;
import com.vitonjob.entities.Job;
import com.vitonjob.entities.Language;
import com.vitonjob.entities.MotCle;
import com.vitonjob.entities.Pays;
import com.vitonjob.entities.Ville;
import com.vitonjob.enums.TableIndexationEnum;
import com.vitonjob.utils.CollectionUtils;

@Component
@Path("/public/recherche")
public class RechercheRestService {

	/*
	 * Mots cles à supprimer
	 */
	// private static final String[] todel = { "le", "la", "l'", "les", "de",
	// "du", "des", "d'", "pour", "par", "dans",
	// "je", "tu", "il", "elle", "nous", "vous", "ils", "elles", "besoin" };

	@Autowired
	private IPaysDAO paysDAO;
	@Autowired
	private IVilleDAO villeDAO;
	@Autowired
	private IJobDAO jobDAO;
	@Autowired
	private ILanguageDAO languageDAO;
	@Autowired
	private IIndexationEmployeurDAO indexationEmployeurDAO;
	@Autowired
	private IIndexationJobyerDAO indexationJobyerDAO;
	@Autowired
	private IMotCleDAO motCleDAO;

	@GET
	@Path("/rechercheEmployeur")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<RechercheEmployeurDTO> rechercheEmployeur(@QueryParam("query") String query) {
		Set<RechercheEmployeurDTO> results = new HashSet<>();

		List<String> listConcepts = getListConcepts(query);

		if (CollectionUtils.isNotEmpty(listConcepts)) {
			List<Long> pays = loadPaysIndexes(listConcepts);
			List<Long> villes = loadVillesIndexes(listConcepts);
			List<Long> jobs = loadJobsIndexes(listConcepts);
			List<Long> languages = loadLanguageIndexes(listConcepts);

			List<RechercheEmployeurDTO> indexes;
			if (CollectionUtils.isNotEmpty(pays)) {
				indexes = indexationEmployeurDAO.findIndexationsByIndexes(pays, TableIndexationEnum.PAYS);
				if (CollectionUtils.isNotEmpty(indexes)) {
					results.addAll(indexes);
				}
			}

			if (CollectionUtils.isNotEmpty(villes)) {
				indexes = indexationEmployeurDAO.findIndexationsByIndexes(villes, TableIndexationEnum.VILLE);
				if (CollectionUtils.isNotEmpty(indexes)) {
					results.addAll(indexes);
				}
			}

			if (CollectionUtils.isNotEmpty(jobs)) {
				indexes = indexationEmployeurDAO.findIndexationsByIndexes(jobs, TableIndexationEnum.COMPETENCE);
				if (CollectionUtils.isNotEmpty(indexes)) {
					results.addAll(indexes);
				}
			}

			if (CollectionUtils.isNotEmpty(languages)) {
				indexes = indexationEmployeurDAO.findIndexationsByIndexes(languages, TableIndexationEnum.LANGUE);
				if (CollectionUtils.isNotEmpty(indexes)) {
					results.addAll(indexes);
				}
			}
		}
		return results;
	}

	@GET
	@Path("/rechercheJobyer")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<RechercheJobyerDTO> rechercheJobyer(@QueryParam("query") String query) {
		Set<RechercheJobyerDTO> results = new HashSet<>();

		List<String> listConcepts = getListConcepts(query);

		if (CollectionUtils.isNotEmpty(listConcepts)) {
			List<Long> pays = loadPaysIndexes(listConcepts);
			List<Long> villes = loadVillesIndexes(listConcepts);
			List<Long> jobs = loadJobsIndexes(listConcepts);
			List<Long> languages = loadLanguageIndexes(listConcepts);

			List<RechercheJobyerDTO> indexes;
			if (CollectionUtils.isNotEmpty(pays)) {
				indexes = indexationJobyerDAO.findIndexationsByIndexes(pays, TableIndexationEnum.PAYS);
				if (CollectionUtils.isNotEmpty(indexes)) {
					results.addAll(indexes);
				}
			}

			if (CollectionUtils.isNotEmpty(villes)) {
				indexes = indexationJobyerDAO.findIndexationsByIndexes(villes, TableIndexationEnum.VILLE);
				if (CollectionUtils.isNotEmpty(indexes)) {
					results.addAll(indexes);
				}
			}

			if (CollectionUtils.isNotEmpty(jobs)) {
				indexes = indexationJobyerDAO.findIndexationsByIndexes(jobs, TableIndexationEnum.COMPETENCE);
				if (CollectionUtils.isNotEmpty(indexes)) {
					results.addAll(indexes);
				}
			}

			if (CollectionUtils.isNotEmpty(languages)) {
				indexes = indexationJobyerDAO.findIndexationsByIndexes(languages, TableIndexationEnum.LANGUE);
				if (CollectionUtils.isNotEmpty(indexes)) {
					results.addAll(indexes);
				}
			}
		}
		return results;
	}

	/**
	 * récupére la liste des mot à prendre en compte lors de la recherche après
	 * la suppression des mots clés inutiles.
	 * 
	 * @param query
	 * @return
	 */
	private List<String> getListConcepts(String query) {
		List<String> listConcepts = new ArrayList<String>();

		List<String> wordsToDelete = motCleDAO.getAllMotsCle();

		// Tokenize query
		String tq = query.toLowerCase().trim().replaceAll(" ", "#").replaceAll(";", "#").replaceAll(",", "#")
				.replaceAll("/.", "#");
		tq = tq.replaceAll("##", "#");
		tq = tq.replaceAll("##", "#");
		tq = tq.replaceAll("##", "#");
		String[] tokens = tq.split("#");

		boolean found;
		for (String t : tokens) {
			found = false;
			for (String word : wordsToDelete) {
				if (t.equals(word)) {
					found = true;
					break;
				}
			}

			if (found)
				continue;
			listConcepts.add(t);
		}
		return listConcepts;
	}

	/*
	 * LOAD INDIVIDUAL INDEXES
	 */
	private List<Long> loadPaysIndexes(List<String> listConcepts) {
		List<Long> results = new ArrayList<Long>();

		Pays pays;
		for (String c : listConcepts) {
			pays = paysDAO.findPaysByNom(c);

			if (pays == null || results.contains(pays.getId()))
				continue;

			results.add(pays.getId());
		}

		return results;
	}

	private List<Long> loadVillesIndexes(List<String> listConcepts) {
		List<Long> results = new ArrayList<Long>();

		List<Ville> villes;
		for (String c : listConcepts) {
			villes = villeDAO.findVillesByNom(c);

			for (Ville v : villes) {
				if (results.contains(v.getId()))
					continue;
				results.add(v.getId());
			}
		}

		return results;
	}

	private List<Long> loadJobsIndexes(List<String> listConcepts) {
		List<Long> results = new ArrayList<Long>();

		List<Job> jobs;
		for (String c : listConcepts) {
			jobs = jobDAO.findJobsByLibelle(c);

			for (Job j : jobs) {
				if (results.contains(j.getId()))
					continue;
				results.add(j.getId());
			}
		}

		return results;
	}

	private List<Long> loadLanguageIndexes(List<String> listConcepts) {
		List<Long> results = new ArrayList<Long>();

		List<Language> langs;
		for (String c : listConcepts) {
			langs = languageDAO.findLanguesByLibelle(c);

			for (Language l : langs) {
				if (results.contains(l.getId()))
					continue;
				results.add(l.getId());
			}
		}

		return results;
	}

	public IPaysDAO getPaysDAO() {
		return paysDAO;
	}

	@Autowired
	public void setPaysDAO(IPaysDAO paysDAO) {
		this.paysDAO = paysDAO;
		this.paysDAO.setClazz(Pays.class);
	}

	public IVilleDAO getVilleDAO() {
		return villeDAO;
	}

	@Autowired
	public void setVilleDAO(IVilleDAO villeDAO) {
		this.villeDAO = villeDAO;
		this.villeDAO.setClazz(Ville.class);
	}

	public IJobDAO getJobDAO() {
		return jobDAO;
	}

	@Autowired
	public void setJobDAO(IJobDAO jobDAO) {
		this.jobDAO = jobDAO;
		this.jobDAO.setClazz(Job.class);
	}

	public ILanguageDAO getLanguageDAO() {
		return languageDAO;
	}

	@Autowired
	public void setLanguageDAO(ILanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
		this.languageDAO.setClazz(Language.class);
	}

	public IIndexationEmployeurDAO getIndexationEmployeurDAO() {
		return indexationEmployeurDAO;
	}

	@Autowired
	public void setIndexationEmployeurDAO(IIndexationEmployeurDAO indexationEmployeurDAO) {
		this.indexationEmployeurDAO = indexationEmployeurDAO;
		this.indexationEmployeurDAO.setClazz(IndexationEmployeur.class);
	}

	public IIndexationJobyerDAO getIndexationJobyerDAO() {
		return indexationJobyerDAO;
	}

	@Autowired
	public void setIndexationJobyerDAO(IIndexationJobyerDAO indexationJobyerDAO) {
		this.indexationJobyerDAO = indexationJobyerDAO;
		this.indexationJobyerDAO.setClazz(IndexationJobyer.class);
	}

	public IMotCleDAO getMotCleDAO() {
		return motCleDAO;
	}

	@Autowired
	public void setMotCleDAO(IMotCleDAO motCleDAO) {
		this.motCleDAO = motCleDAO;
		this.motCleDAO.setClazz(MotCle.class);
	}

}
