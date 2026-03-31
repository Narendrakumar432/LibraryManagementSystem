package com.library.dao;

import com.library.model.Member;
import com.library.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MemberDAO {

    public void save(Member member) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Member member) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(member);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Member m = em.find(Member.class, id);
            if (m != null) em.remove(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Member findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Member.class, id);
        } finally {
            em.close();
        }
    }

    public List<Member> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Member m ORDER BY m.name", Member.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
