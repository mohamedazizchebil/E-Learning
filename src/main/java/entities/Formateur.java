package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Formateur {
	
	@Id @GeneratedValue
	    private int id;

	    private String nom;
	    private String email;
	    private String specialite;

	    @OneToMany(mappedBy = "formateur")
	    private List<Formation> formations;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public List<Formation> getFormations() {
			return formations;
		}

		public void setFormations(List<Formation> formations) {
			this.formations = formations;
		}

		@Override
		public String toString() {
			return "Formateur [id=" + id + ", nom=" + nom + ", formations="  + "]";
		}

		public Formateur(int id, String nom,String email,String specialite) {
			super();
			this.id = id;
			this.nom = nom;
			this.email=email;
			this.specialite=specialite;
			
		}
		
		
		public Formateur(String nom,String email,String specialite) {
			super();
			this.nom = nom;
			this.email=email;
			this.specialite=specialite;
			
		}
		public Formateur() {
			super();
			
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSpecialite() {
			return specialite;
		}

		public void setSpecialite(String specialite) {
			this.specialite = specialite;
		}
		
		

	    // Getters & Setters
	    
	    
	


}
