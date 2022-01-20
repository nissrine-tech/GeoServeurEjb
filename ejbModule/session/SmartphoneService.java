package session;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.IdaoLocal;
import dao.IdaoRemote;
import entities.Smartphone;
import entities.User;

@Stateless
public class SmartphoneService implements IdaoLocal<Smartphone>, IdaoRemote<Smartphone> {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PermitAll
	public boolean create(Smartphone s) {
		//entityManager.merge(s);
		entityManager.persist(s);
		System.out.println(s);
		return true;
	}

	@Override
	@PermitAll
	public boolean update(Smartphone s) {
		entityManager.merge(s);
		return true;
	}

	@Override
	@PermitAll
	public boolean delete(int id) {
		Smartphone c = findById(id);
		entityManager.remove(c);
		return true;
	}

	@Override
	@PermitAll
	public Smartphone findById(int id) {
		return entityManager.find(Smartphone.class, id);
	}

	@Override
	@PermitAll
	public List<Smartphone> findAll() {
		Query query = entityManager.createQuery("from Smartphone");
		return query.getResultList();
	}

	@Override
	public Smartphone findByImei(String imei) {
		return entityManager.find(Smartphone.class, imei);
	}

}
