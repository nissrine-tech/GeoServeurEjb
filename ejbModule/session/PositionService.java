package session;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.IdaoLocal;
import dao.IdaoRemote;
import entities.Position;
import entities.User;

@Stateless
public class PositionService implements IdaoLocal<Position>, IdaoRemote<Position> {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PermitAll
	public boolean create(Position p) {
		entityManager.persist(p);
		return true;
	}

	@Override
	@PermitAll
	public boolean update(Position p) {
		entityManager.merge(p);
		return true;
	}

	@Override
	public boolean delete(int id) {
		Position c = findById(id);
		entityManager.remove(c);
		return false;
	}

	@Override
	@PermitAll
	public Position findById(int id) {
		return entityManager.find(Position.class, id);
	}

	@Override
	@PermitAll
	public List<Position> findAll() {
		javax.persistence.Query query = entityManager.createQuery("from Position");
		return query.getResultList();
	}

	@Override
	public Position findByImei(String imei) {
		return entityManager.find(Position.class, imei);
	}

}
