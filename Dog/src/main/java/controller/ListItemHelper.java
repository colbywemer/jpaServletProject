package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.dog;

public class ListItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Dog");
	public void insetItem(dog li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	public List<dog>showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<dog>allItems = em.createQuery("SELECT i FROM dog i").getResultList();
		return allItems;
	}
	public void deleteItem(dog toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<dog>typedQuery = em.createQuery("select li from dog li where li.name =:selectedName and li.owner =:selectedOwner and li.breed =:selectedBreed",dog.class);
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		typedQuery.setParameter("selectedBreed", toDelete.getBreed());
		typedQuery.setMaxResults(1);
		dog result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	public dog searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		dog found = em.find(dog.class, idToEdit);
		em.close();
		return found;
	}
	public void updateItem(dog toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	public List<dog> searchForDogByName(String dogName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<dog>typedQuery = em.createQuery("select li from dog li where li.name =:selectedName",dog.class);
		typedQuery.setParameter("selectedName", dogName);
		List<dog>foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	public List<dog> searchForDogByOwner(String OwnerName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<dog>typedQuery = em.createQuery("select li from dog li where li.owner =:selectedOwner",dog.class);
		typedQuery.setParameter("selectedOwner", OwnerName);
		List<dog>foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	public List<dog> searchForDogByBreed(String BreedName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<dog>typedQuery = em.createQuery("select li from dog li where li.breed =:selectedBreed",dog.class);
		typedQuery.setParameter("selectedBreed", BreedName);
		List<dog>foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	public void cleanUp() {
		emfactory.close();
	}
}
