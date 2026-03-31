package com.library.dao;

import com.library.model.BorrowRecord;
import com.library.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class BorrowRecordDAO {

    public void save(BorrowRecord record) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(record);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(BorrowRecord record) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(record);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public BorrowRecord findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(BorrowRecord.class, id);
        } finally {
            em.close();
        }
    }

    public List<BorrowRecord> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                "SELECT r FROM BorrowRecord r JOIN FETCH r.book JOIN FETCH r.member ORDER BY r.borrowDate DESC",
                BorrowRecord.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<BorrowRecord> findActive() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                "SELECT r FROM BorrowRecord r JOIN FETCH r.book JOIN FETCH r.member WHERE r.status = 'BORROWED'",
                BorrowRecord.class).getResultList();
        } finally {
            em.close();
        }
    }

    /** Mark overdue records automatically */
    public void updateOverdueStatus() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery(
                "UPDATE BorrowRecord r SET r.status = 'OVERDUE' WHERE r.status = 'BORROWED' AND r.dueDate < :today")
              .setParameter("today", LocalDate.now())
              .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
