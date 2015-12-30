package com.vitonjob.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
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
import com.vitonjob.dao.IPaysDAO;
import com.vitonjob.dao.IVilleDAO;
import com.vitonjob.dto.RechercheEmployeurDTO;
import com.vitonjob.dto.RechercheJobyerDTO;
import com.vitonjob.entities.IndexationEmployeur;
import com.vitonjob.entities.IndexationJobyer;
import com.vitonjob.entities.Job;
import com.vitonjob.entities.Language;
import com.vitonjob.entities.Pays;
import com.vitonjob.entities.Ville;
import com.vitonjob.enums.TableIndexationEnum;

@Component
@Path("/public/recherche")
public class RechercheRestService {

	/*
	 * Mots cles à supprimer
	 */
	private static final String[] todel = { "le", "la", "l'", "les", "de", "du", "des", "d'", "pour", "par", "dans",
			"je", "tu", "il", "elle", "nous", "vous", "ils", "elles", "besoin" };

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

	@POST
	@Path("/rechercheEmployeur")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RechercheEmployeurDTO> rechercheEmployeur(@QueryParam("query") String query) {
		List<RechercheEmployeurDTO> results = new ArrayList<RechercheEmployeurDTO>();

		// Tokenize query
		String tq = query.toLowerCase().trim().replaceAll(" ", "#").replaceAll(";", "#").replaceAll(",", "#")
				.replaceAll(".", "#");
		tq = tq.replaceAll("##", "#");
		tq = tq.replaceAll("##", "#");
		tq = tq.replaceAll("##", "#");
		String[] tokens = tq.split("#");
		List<String> listConcepts = new ArrayList<String>();
		for (String t : tokens) {
			boolean found = false;
			for (int i = 0; i < todel.length; i++) {
				if (t.equals(todel[i])) {
					found = true;
					break;
				}
			}

			if (found)
				continue;
			listConcepts.add(t);
		}

		String[] concepts = (String[]) listConcepts.toArray();
		List<Long> pays = loadPaysIndexes(concepts);
		List<Long> villes = loadVillesIndexes(concepts);
		List<Long> jobs = loadJobsIndexes(concepts);
		List<Long> languages = loadLanguageIndexes(concepts);

		List<IndexationEmployeur> indexes = indexationEmployeurDAO.findIndexationsByIndexes(pays,
				TableIndexationEnum.PAYS);
		for (IndexationEmployeur i : indexes) {
			boolean found = false;
			for (RechercheEmployeurDTO e : results) {
				if (e.getId() == i.getEmployeur().getId()) {
					found = true;
					break;
				}
			}
			if (found)
				continue;
			results.add(new RechercheEmployeurDTO(i.getEmployeur()));
		}
		indexes = indexationEmployeurDAO.findIndexationsByIndexes(villes, TableIndexationEnum.VILLE);
		for (IndexationEmployeur i : indexes) {
			boolean found = false;
			for (RechercheEmployeurDTO e : results) {
				if (e.getId() == i.getEmployeur().getId()) {
					found = true;
					break;
				}
			}
			if (found)
				continue;
			results.add(new RechercheEmployeurDTO(i.getEmployeur()));
		}
		indexes = indexationEmployeurDAO.findIndexationsByIndexes(jobs, TableIndexationEnum.COMPETENCE);
		for (IndexationEmployeur i : indexes) {
			boolean found = false;
			for (RechercheEmployeurDTO e : results) {
				if (e.getId() == i.getEmployeur().getId()) {
					found = true;
					break;
				}
			}
			if (found)
				continue;
			results.add(new RechercheEmployeurDTO(i.getEmployeur()));
		}
		indexes = indexationEmployeurDAO.findIndexationsByIndexes(languages, TableIndexationEnum.LANGUE);
		for (IndexationEmployeur i : indexes) {
			boolean found = false;
			for (RechercheEmployeurDTO e : results) {
				if (e.getId() == i.getEmployeur().getId()) {
					found = true;
					break;
				}
			}
			if (found)
				continue;
			results.add(new RechercheEmployeurDTO(i.getEmployeur()));
		}
		return results;
	}

	@POST
	@Path("/rechercheJobyer")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RechercheJobyerDTO> rechercheJobyer(@QueryParam("query") String query) {
		List<RechercheJobyerDTO> results = new ArrayList<RechercheJobyerDTO>();

		// Tokenize query
		String tq = query.toLowerCase().trim().replaceAll(" ", "#").replaceAll(";", "#").replaceAll(",", "#")
				.replaceAll(".", "#");
		tq = tq.replaceAll("##", "#");
		tq = tq.replaceAll("##", "#");
		tq = tq.replaceAll("##", "#");
		String[] tokens = tq.split("#");
		List<String> listConcepts = new ArrayList<String>();
		for (String t : tokens) {
			boolean found = false;
			for (int i = 0; i < todel.length; i++) {
				if (t.equals(todel[i])) {
					found = true;
					break;
				}
			}

			if (found)
				continue;
			listConcepts.add(t);
		}

		String[] concepts = (String[]) listConcepts.toArray();
		List<Long> pays = loadPaysIndexes(concepts);
		List<Long> villes = loadVillesIndexes(concepts);
		List<Long> jobs = loadJobsIndexes(concepts);
		List<Long> languages = loadLanguageIndexes(concepts);

		List<IndexationJobyer> indexes = indexationJobyerDAO.findIndexationsByIndexes(pays, TableIndexationEnum.PAYS);
		for (IndexationJobyer i : indexes) {
			boolean found = false;
			for (RechercheJobyerDTO e : results) {
				if (e.getId() == i.getJobyer().getId()) {
					found = true;
					break;
				}
			}
			if (found)
				continue;
			results.add(new RechercheJobyerDTO(i.getJobyer()));
		}
		indexes = indexationJobyerDAO.findIndexationsByIndexes(villes, TableIndexationEnum.VILLE);
		for (IndexationJobyer i : indexes) {
			boolean found = false;
			for (RechercheJobyerDTO e : results) {
				if (e.getId() == i.getJobyer().getId()) {
					found = true;
					break;
				}
			}
			if (found)
				continue;
			results.add(new RechercheJobyerDTO(i.getJobyer()));
		}
		indexes = indexationJobyerDAO.findIndexationsByIndexes(jobs, TableIndexationEnum.COMPETENCE);
		for (IndexationJobyer i : indexes) {
			boolean found = false;
			for (RechercheJobyerDTO e : results) {
				if (e.getId() == i.getJobyer().getId()) {
					found = true;
					break;
				}
			}
			if (found)
				continue;
			results.add(new RechercheJobyerDTO(i.getJobyer()));
		}
		indexes = indexationJobyerDAO.findIndexationsByIndexes(languages, TableIndexationEnum.LANGUE);
		for (IndexationJobyer i : indexes) {
			boolean found = false;
			for (RechercheJobyerDTO e : results) {
				if (e.getId() == i.getJobyer().getId()) {
					found = true;
					break;
				}
			}
			if (found)
				continue;
			results.add(new RechercheJobyerDTO(i.getJobyer()));
		}
		return results;
	}

	/*
	 * LOAD INDIVIDUAL INDEXES
	 */
	private List<Long> loadPaysIndexes(String[] concepts) {
		List<Long> results = new ArrayList<Long>();

		for (String c : concepts) {
			Pays pays = paysDAO.findPaysByNom(c);

			if (pays == null || results.contains(pays.getId()))
				continue;

			results.add(pays.getId());
		}

		return results;
	}

	private List<Long> loadVillesIndexes(String[] concepts) {
		List<Long> results = new ArrayList<Long>();

		for (String c : concepts) {
			List<Ville> villes = villeDAO.findVillesByNom(c);

			for (Ville v : villes) {
				if (results.contains(v.getId()))
					continue;
				results.add(v.getId());
			}
		}

		return results;
	}

	private List<Long> loadJobsIndexes(String[] concepts) {
		List<Long> results = new ArrayList<Long>();

		for (String c : concepts) {
			List<Job> jobs = jobDAO.findJobsByLibelle(c);

			for (Job j : jobs) {
				if (results.contains(j.getId()))
					continue;
				results.add(j.getId());
			}
		}

		return results;
	}

	private List<Long> loadLanguageIndexes(String[] concepts) {
		List<Long> results = new ArrayList<Long>();

		for (String c : concepts) {
			List<Language> langs = languageDAO.findLanguesByLibelle(c);

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

}
