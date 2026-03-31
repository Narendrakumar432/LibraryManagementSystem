package com.library.dao;

import com.library.model.Book;
import com.library.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class BookDAO {

    public void save(Book book) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Book book) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(book);
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
            Book book = em.find(Book.class, id);
            if (book != null) em.remove(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Book findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public List<Book> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT b FROM Book b ORDER BY b.title", Book.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Book> search(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE LOWER(b.title) LIKE :kw OR LOWER(b.author) LIKE :kw OR b.isbn LIKE :kw",
                Book.class);
            query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
