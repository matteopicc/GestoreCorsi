package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {//Classe impostata per la connessione al database
	
	public static Connection getConnection() {
		try {
		String url = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root";
		return DriverManager.getConnection(url);//ritorna una connessione tramite DriverManager e l'URL
		}catch(SQLException e) {
			System.err.println("Errore di connessione!");
			e.printStackTrace();
			return null;
		}
	}

}
