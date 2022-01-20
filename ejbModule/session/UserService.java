package session;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.IdaoLocal;
import dao.IdaoRemote;
import entities.User;

@Stateless
public class UserService implements IdaoLocal<User>, IdaoRemote<User> {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PermitAll
	public boolean create(User c) {
		entityManager.persist(c);
		return true;
	}

	@Override
	@PermitAll
	public boolean update(User u) {
		entityManager.merge(u);
		return true;
	}

	@Override
	@PermitAll
	public boolean delete(int id) {
		User c = findById(id);
		entityManager.remove(c);
		return true;
	}

	@Override
	@PermitAll
	public User findById(int id) {
		User u = entityManager.find(User.class, id);
		return u;
	}

	@Override
	@PermitAll
	public List<User> findAll() {
		//Query query = entityManager.createQuery("from User");
		Query req = entityManager.createQuery("select u from User u");
		return req.getResultList();
	}

	@Override
	public User findByImei(String imei) {
		return entityManager.find(User.class, imei);
	}

}
