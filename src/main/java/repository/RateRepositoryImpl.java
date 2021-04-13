package repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.RateEntity;
import model.CurrencyCode;

public class RateRepositoryImpl implements RateRepository {

	EntityManager em;
	
	private EntityManager getEntityManager() {
		return EntityManagerFacotryUtil.getFactory().createEntityManager();
	}
	
	@Override
	public RateEntity getRateById(Long id) {
		em = getEntityManager();
		RateEntity rate = em.find(RateEntity.class, id);
		em.close();
		return rate;
	}

	@Override
	public List<RateEntity> getRatesByCurrencyCode(CurrencyCode code) {
		em = getEntityManager();
		TypedQuery<RateEntity> q = em.createQuery("SELECT r FROM RateEntity r JOIN r.currency c WHERE c.code = :code", RateEntity.class);
		q.setParameter("code", code);
		List<RateEntity> rate = q.getResultList();
		em.close();
		return rate;
	}

	@Override
	public List<RateEntity> getRatesByDate(LocalDate date) {
		em = getEntityManager();
		TypedQuery<RateEntity> q = em.createQuery("SELECT r FROM RateEntity r WHERE r.effectiveDate = :date", RateEntity.class);
		q.setParameter("date", date);
		List<RateEntity> rate = q.getResultList();
		em.close();
		return rate;
	}
	
	@Override
	public void saveRate(RateEntity rate) {
		em = getEntityManager();
		em.getTransaction().begin();
		if(rate.getId() == null) {
			em.persist(rate);
		} else {
			em.merge(rate);
		}
		em.getTransaction().commit();
		em.close();
	}
}