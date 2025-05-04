package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Formateur;
import entities.Formation;

public class GestionFormateur implements IGestionFormateur {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("platform");
	
	EntityManager em = emf.createEntityManager();
	private GestionFormations gestion = new GestionFormations();

	@Override
	public void addFormateur(Formateur f) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(f);
		et.commit();
		
	}

	//@Override
	/*public void deleteFormateur(int id) {
	    EntityTransaction et = em.getTransaction();
	    try {
	        et.begin();

	        // Récupérer le formateur
	        Formateur f = em.find(Formateur.class, id);
	        if (f != null) {
	            // Déconnecter le formateur de ses formations
	            for (Formation formation : f.getFormations()) {
	                formation.setFormateur(null); // Retirer la référence au formateur
	                em.merge(formation);  // Mettre à jour la formation
	            }

	            // Supprimer le formateur
	            em.remove(f);
	        }
	        
	        et.commit(); // Essayer de valider la transaction

	    } catch (Exception e) {
	        // Si une erreur survient, annuler la transaction
	        if (et.isActive()) {
	            et.rollback();
	        }
	        e.printStackTrace();  // Afficher les détails de l'exception pour le débogage
	        throw new RuntimeException("Erreur lors de la suppression du formateur", e);
	    }
	}*/
	
	
	
	 @Override
	    public void deleteFormateur(int id) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            Formateur c = em.find(Formateur.class, id);
	            if (c != null) {
	                gestion.dissocier(c);
	                em.remove(c);
	            }
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null && tx.isActive()) tx.rollback();
	            throw new RuntimeException("Error", e);
	        } finally {
	            if (em != null) em.close();
	        }
	    }




	@Override
	public List<Formateur> getAllFormateurs() {
		Query q =em.createQuery("select f from Formateur f");
		
		return q.getResultList();
	}

	@Override
	public Formateur getFormateurById(int id) {
		return em.find(Formateur.class, id);
	}

}
