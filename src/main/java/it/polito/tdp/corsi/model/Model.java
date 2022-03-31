package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;//classe corsoDAO importata
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;// creazione dell'oggetto corso dao
	private StudenteDAO studenteDAO;// creazione dell'oggetto studente dao
	
	public Model() {
		this.corsoDAO= new CorsoDAO();
		this.studenteDAO= new StudenteDAO();
		
	}
	public List<Corso>getCorsiByPeriodo(int periodo){
		return this.corsoDAO.getCorsiByPeriodo(periodo);
	}
	
	public Map<Corso,Integer> getIscritti(int periodo){
		return this.corsoDAO.getIscritti(periodo);
	}
	
	public List<Studente> getStudentiByCorso(String codins){
		return this.studenteDAO.getStudentiByCorso(codins);
	}
	
	public List<Divisione>getDivisioneStudenti(String codins){
		return this.studenteDAO.getDivisioneStudenti(codins);
	}
}
