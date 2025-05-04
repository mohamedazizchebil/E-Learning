package dao;

import java.util.List;
import javax.persistence.*;

import entities.Formateur;
import entities.Formation;
import entities.User;

public class GestionFormations implements IGestionFormations {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("platform");

    @Override
    public void addFormation(Formation f) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(f);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Formation> getAllFormations() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select f from Formation f");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Formation> getAllFormationsByMC(String mc) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select f from Formation f where f.titre like :x");
            q.setParameter("x", "%" + mc + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Formation getFormation(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Formation.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteFormation(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Formation f = em.find(Formation.class, id);
            if (f != null) {
                // Détacher les utilisateurs inscrits
                for (User u : f.getUsers()) {
                    u.getFormations().remove(f); // côté inverse
                    em.merge(u); // mettre à jour la relation
                }

                em.remove(f);
            }
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void updateFormation(Formation f) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(f);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    
    
    public void dissocier(Formateur formateur) {
        formateur.getFormations().forEach(f -> {
            f.setFormateur(null);
        });
    }
}
