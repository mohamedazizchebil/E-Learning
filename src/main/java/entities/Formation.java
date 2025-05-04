package entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
public class Formation {
	
	
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String titre;
	    private String description;

	   @ManyToOne
	   private Formateur formateur;
	   
	   
	   
	   @ManyToMany(mappedBy = "formations")
	    private List<User> users = new ArrayList<>();

		public String getTitre() {
			return titre;
		}

		public void setTitre(String titre) {
			this.titre = titre;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Formateur getFormateur() {
			return formateur;
		}

		public void setFormateur(Formateur formateur) {
			this.formateur = formateur;
		}

		public int getId() {
			return id;
		}
		

		public void setId(int id) {
			this.id = id;
		}

		public Formation(int id, String titre, String description) {
			super();
			this.id = id;
			this.titre = titre;
			this.description = description;
			
		}
		
		public Formation(String titre, String description) {
			super();
			this.titre = titre;
			this.description = description;
			
		}

		public Formation() {
			super();
		}

		@Override
		public String toString() {
			return "Formation [id=" + id + ", titre=" + titre + ", description=" + description + ", formateur="
					+ "]";
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}
		
		
		@Override
		public boolean equals(Object o) {
		    if (this == o) return true;
		    if (o == null || getClass() != o.getClass()) return false;

		    Formation formation = (Formation) o;

		    return id == formation.id;
		}

		@Override
		public int hashCode() {
		    return Integer.hashCode(id);
		}

		
		
		

	

	   
	


}
