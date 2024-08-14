package dao;

import entity.Perro;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class PerroDAO {

    public void guardarPoliPerro(Perro perro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(perro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para actualizar un producto en la base de datos ORM
    public void actualizarPoliPerro(Perro perro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(perro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para eliminar un producto de la base de datos
    public void eliminarPoliPerro(Perro perro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(perro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para obtener todos los perros de la base de datos
    public List<Perro> obtenerPoliPerro() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Perro> query = session.createQuery("FROM Perro ", Perro.class);
            return query.list();
        }
    }

    // Método para buscar perros en la base de datos
    public List<Perro> buscarPoliPerros(String filtro, String terminoBusqueda) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Perro WHERE " + filtro + " LIKE :termino";
            Query<Perro> query = session.createQuery(hql, Perro.class);
            query.setParameter("termino", "%" + terminoBusqueda + "%");
            return query.list();
        }
    }

    // Método para obtener un producto por su ID
    public Perro obtenerProductoPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Perro.class, id);
        }
    }
}
