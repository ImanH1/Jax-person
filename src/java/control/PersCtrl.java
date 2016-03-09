/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Person;
import exceptions.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Iman H
 */
public class PersCtrl {
    EntityManagerFactory emf;
    EntityManager em;
    
    public PersCtrl(String pu) {
        emf = Persistence.createEntityManagerFactory(pu);
        em = emf.createEntityManager();
    }
    
    Map<Integer, Person> persons = new HashMap() {
        {
            put(1, new Person("edd", "wetmore", "69396969", 1));
            put(2, new Person("mike", "spetz", "42042469", 2));
            put(3, new Person("thomas", "timon", "18769520", 3));
        }
    };
    
    public Person addPerson(Person person) throws InvalidIdException, InvalidPersonException {
        
        EntityTransaction trans = em.getTransaction();

        if (person.getFName() == null || person.getLName() == null) {
            throw new InvalidPersonException("");
        } else {
            try {
        
                trans.begin();
                em.persist(person);
                trans.commit();

                return person;
            
            } catch (Exception e) {
               throw new InvalidIdException("Invalid ID.");
            }
        }
    }

    public Person deletePerson(int id) throws NotFoundException {
       
        EntityTransaction eTrans = em.getTransaction();
        try {
            Person p = em.find(Person.class, id);
            if (p != null) {
                eTrans.begin();
                em.remove(p);
                eTrans.commit();
            }
            
            return p;
            
        } catch (Exception e) {
           throw new NotFoundException("Person not found.");
        }
    }

    public Person getPerson(int id) throws NotFoundException {
       
        try {
            Person p = (Person) em.createNamedQuery("Person.findById").setParameter("id", id).getSingleResult();
            return p;
        } catch (Exception e) {
            throw new NotFoundException("Person not found.");
        }
    }

    public List<Person> getPersons() throws NotFoundException {
        
        try {
            List<Person> persons = em.createNamedQuery("Person.findAll").getResultList();
            return persons;
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Person not found.");
        }
    }

    public Person editPerson(Person person) throws NotFoundException {
        return person;
    }
}
