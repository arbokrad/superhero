package com.tessco.starkc.superhero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.UUID;

public class Superhero {
	
	private UUID id;
	private String name;
	private Date debut;
	private int numVillainsFought;
	private short numAlterEgos;
	private boolean masked;
	private boolean female;
	private boolean retired;

	private String LOAD_SUPERHERO_SQL = "select s.id, s.name, s.debut, s.numVillainsFought, s.numAlterEgos, s.masked, s.female, s.retired from SUPERHERO_INFO s where s.id = ?";
	
	// constructor with all attributes mandatory
	public Superhero( String name, Date debut, int numVillainsFought, short numAlterEgos, boolean masked, boolean female, boolean retired ) {
		setName(name);
		setDebut(debut);
		setNumVillainsFought(numVillainsFought);
		setNumAlterEgos(numAlterEgos);
		setMasked(masked);
		setFemale(female);
		setRetired(retired);
	}
	
	// utility to save the superhero info
	public void saveSuperhero( Superhero hero ) {
		Connection conn = getConnection();
		PreparedStatement stmt = null;

		try{
			// populate prepared statement
			stmt = conn.prepareStatement("");

			// execute query
			stmt.executeUpdate();
			
			// commit
			conn.commit();
			
		} catch( SQLException sqe ) {
			System.out.println( "SQL Exception: " + sqe.getMessage() );
			throw new RuntimeException( sqe );
		} finally {
			close( stmt );
			close( conn );
		}
	}
	
	// utility to load a superhero based on unique ID 
	public void loadSuperhero( UUID heroId ) {
		
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		try{
			// populate prepared statement
			stmt.setString( 0, heroId.toString() );
			stmt = conn.prepareStatement(LOAD_SUPERHERO_SQL);

			// execute query
			rs = stmt.executeQuery();
			
			// translate/load results
			
		} catch( SQLException sqe ) {
			System.out.println( "SQL Exception: " + sqe.getMessage() );
			throw new RuntimeException( sqe );
		} finally {
			close( stmt );
			close( conn );
		}
	}
	
	private Connection getConnection() {
		Connection conn = null;
		
		// create the connection
		
		return conn;
	}
	
	private void close( Connection conn ) {
		try {
			if( conn != null ) {
				conn.close();
			}
		} catch( SQLException sqe ) {
			System.out.println( "Unable to close connection!" );
		}
	}
	
	private void close( Statement stmt ) {
		try {
			if( stmt != null ) {
				stmt.close();
			}
		} catch( SQLException sqe ) {
			System.out.println( "Unable to close statement" );
		}
	}
	
	private void close( ResultSet rs ) {
		try {
			if( rs != null ) {
				rs.close();
			}
		} catch( SQLException sqe ) {
			System.out.println( "Unable to close resultset!" );
		}
	}
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	
	public int getNumVillainsFought() {
		return numVillainsFought;
	}
	public void setNumVillainsFought(int numVillainsFought) {
		this.numVillainsFought = numVillainsFought;
	}
	
	public short getNumAlterEgos() {
		return numAlterEgos;
	}
	public void setNumAlterEgos(short numAlterEgos) {
		this.numAlterEgos = numAlterEgos;
	}
	
	public boolean isMasked() {
		return masked;
	}
	public void setMasked(boolean masked) {
		this.masked = masked;
	}
	
	public boolean isFemale() {
		return female;
	}
	public void setFemale(boolean female) {
		this.female = female;
	}
	
	public boolean isRetired() {
		return retired;
	}
	
	public void setRetired(boolean retired) {
		this.retired = retired;
	}
}