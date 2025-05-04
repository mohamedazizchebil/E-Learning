package dao;

import java.util.List;

import entities.Formateur;

public interface IGestionFormateur {
	
	void addFormateur(Formateur f);
	void deleteFormateur(int id);
	List<Formateur>getAllFormateurs();
	Formateur getFormateurById(int id);

}
