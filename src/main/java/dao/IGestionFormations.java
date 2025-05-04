package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Formation;

public interface IGestionFormations {
	
	
	public void addFormation(Formation f);
	public List<Formation> getAllFormations();
	public List<Formation> getAllFormationsByMC(String mc);
	public Formation getFormation(int id) ;
	public void deleteFormation(int id);
	public void updateFormation(Formation f);

}
