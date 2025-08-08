package dao;

import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionManager;
import java.util.List;

public class ProductDaoImpl implements ProductDao{

    @Override
    public void addProduct(Product product) {
        Transaction tx = null;
        try (Session session = HibernateSessionManager.getSession()) {
            tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (Session session = HibernateSessionManager.getSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void updateProduct(Product product) {
        Transaction tx = null;
        try (Session session = HibernateSessionManager.getSession()) {
            tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
        Transaction tx = null;
        try (Session session = HibernateSessionManager.getSession()) {
            Product product = session.get(Product.class, id);
            if (product != null) {
                tx = session.beginTransaction();
                session.remove(product);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int id) {
        try (Session session = HibernateSessionManager.getSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
