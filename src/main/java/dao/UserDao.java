package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Formation;
import entities.User;

public class UserDao {
	
	 private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("platform");
	  private EntityManager em;
	  public UserDao() {
	        em = emf.createEntityManager();
	    }

	

	    public void createUtilisateur(User utilisateur) {
	        em.getTransaction().begin();
	        em.persist(utilisateur);
	        em.getTransaction().commit();
	    }

	    public User authentifier(String username, String password) {
	        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
	        query.setParameter("username", username);
	        List<User> utilisateurs = query.getResultList();
	        
	        if (utilisateurs.isEmpty()) {
	            return null;
	        }

	        User utilisateur = utilisateurs.get(0);
	        
	        if (utilisateur.getPassword().equals(password)) {
	            return utilisateur;
	        } else {
	            return null;
	        }
	    }
	    public User getUserById(Long id) {
	        EntityManager em = emf.createEntityManager();
	        try {
	            return em.createQuery(
	                "SELECT u FROM User u LEFT JOIN FETCH u.formations WHERE u.id = :id", User.class)
	                .setParameter("id", id)
	                .getSingleResult();
	        } finally {
	            em.close();
	        }
	    }

	    public void registerForCourse(Long userId, int formationId) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();

	        try {
	            tx.begin();

	            User user = em.find(User.class, userId);
	            Formation formation = em.find(Formation.class, formationId);

	            if (!user.getFormations().contains(formation)) {
	                user.getFormations().add(formation);
	                formation.getUsers().add(user); // si bidirectionnel
	            }

	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null && tx.isActive()) tx.rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }


	    public List<Formation> getUserCourses(Long userId) {
	        EntityManager em = emf.createEntityManager();
	        try {
	            User user = em.createQuery(
	                    "SELECT u FROM User u LEFT JOIN FETCH u.formations WHERE u.id = :id", User.class)
	                    .setParameter("id", userId)
	                    .getSingleResult();

	            return user.getFormations();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            em.close();
	        }
	    }

	    
	 

	    
	} 


