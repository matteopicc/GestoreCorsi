package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;//importo le liste
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;//importo la classe corso

public class CorsoDAO {//conterrà tutti i metodi per interfacciarci con la tabella corso
	
	public List<Corso> getCorsiByPeriodo(int periodo) {
		String sql = "select * "
				+ "from corso "   //TESTO DELLA QUERY
				+ "where pd =? ";
		List<Corso>result = new ArrayList<Corso>(); //CONTENITORE DEI RISULTATI
		
		try {
			Connection conn = ConnectDB.getConnection();//Creazione connessione
			PreparedStatement st = conn.prepareStatement(sql);//Creazione del prepared Statement
			st.setInt(1, periodo);//definizione del paramentro della query
			ResultSet rs=st.executeQuery();//la classe result set salva il risultato della query
			
			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				result.add(c);
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
	
	//Seconda query!!!!
	
	public Map<Corso,Integer> getIscritti(int periodo){
		String sql = "SELECT c.codins,c.crediti,c.nome,c.pd,COUNT(*)AS n "
				+ "FROM corso c,iscrizione i "
				+ "WHERE c.codins = i.codins AND c.pd=? "
				+ "GROUP BY c.codins,c.crediti,c.nome,c.pd";
		
		Map<Corso,Integer>result = new HashMap<Corso,Integer>();
		
		try {
			Connection conn = ConnectDB.getConnection();//Creazione connessione
			PreparedStatement st = conn.prepareStatement(sql);//Creazione del prepared Statement
			st.setInt(1, periodo);//definizione del paramentro della query
			ResultSet rs=st.executeQuery();//la classe result set salva il risultato della query
			
		while (rs.next()) {
			
		    result.put(new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd")), rs.getInt("n"));	
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
