package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getStudentiByCorso(String codins) {
		String sql ="SELECT s.matricola,s.cognome,s.nome,s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ?";
		
		List<Studente>result = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();//Creazione connessione
			PreparedStatement st = conn.prepareStatement(sql);//Creazione del prepared Statement
			st.setString(1, codins);//definizione del paramentro della query
			ResultSet rs=st.executeQuery();//la classe result set salva il risultato della query
			
			while(rs.next()) {
				
				result.add(new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS")));
			}
			
			rs.close();//chiusura result set
		    st.close();//chiusura statement
		    conn.close();//chusura connessione
		    
		    return result;
			
		}catch(SQLException e) {
			System.err.println("Errore nel DAO!");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Divisione>getDivisioneStudenti(String codins){
		String sql ="SELECT s.CDS,COUNT(*)AS n "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? AND s.CDS<>\"\" "
				+ "GROUP BY s.cds ";
		
		List<Divisione>result = new ArrayList<Divisione>();
		
		try {
			Connection conn = ConnectDB.getConnection();//Creazione connessione
			PreparedStatement st = conn.prepareStatement(sql);//Creazione del prepared Statement
			st.setString(1, codins);//definizione del paramentro della query
			ResultSet rs=st.executeQuery();//la classe result set salva il risultato della query
			
			while(rs.next()) {
				
				result.add(new Divisione(rs.getString("CDS"),rs.getInt("n")));
			}
			
			rs.close();//chiusura result set
		    st.close();//chiusura statement
		    conn.close();//chusura connessione
		    
		    return result;
			
		}catch(SQLException e) {
			System.err.println("Errore nel DAO!");
			e.printStackTrace();
			return null;
		}
		
	}

}
