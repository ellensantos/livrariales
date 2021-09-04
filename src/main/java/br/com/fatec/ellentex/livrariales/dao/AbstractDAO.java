package br.com.fatec.ellentex.livrariales.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author EllenTex
 *
 */
public abstract class AbstractDAO implements IDAO {

	protected static EntityManager entityManager;
	protected SessionFactory factory;
	protected Session session;
	protected static boolean ctrlTransacao = true;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria-les");

	protected EntityManager getEntityManager() {

	/*	if (entityManager == null) {
			entityManager = emf.createEntityManager();
		}*/

		if(entityManager == null){
			entityManager = emf.createEntityManager();
		}

		else if((!entityManager.isOpen())){
			entityManager = emf.createEntityManager();
		}

		return entityManager;
	}

	protected void closeEntityManager(){
		entityManager.close();
	}

	protected SessionFactory getSessionHibernate(){
		getEntityManager();
		Session session = entityManager.unwrap(org.hibernate.Session.class);
		factory = session.getSessionFactory();
		return factory;
	}

}
