package DAO;

import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.List;

public class UserDAOHibernate {
    private Session session;

    public UserDAOHibernate(Session session) {
        this.session = session;
    }

    public boolean addUser(String name, String email, String password) {
        try {
            Transaction transaction = session.beginTransaction();
            if (!checkUserExist(name)) {
                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                session.save(user);
                transaction.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editUser(Long id, String name, String email, String password) {
        try {
            User user = (User) session.load(User.class, id);
            if (user != null) {
                Transaction transaction = session.beginTransaction();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                session.save(user);
                transaction.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        try {
            User user = (User) session.load(User.class, id);
            if (user != null) {
                Transaction transaction = session.beginTransaction();
                session.delete(user);
                transaction.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
            session.close();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkUserExist(String name) {
        try {
            Criteria criteria = session.createCriteria(User.class);
            int count = criteria.add(Restrictions.eq("name", name)).list().size();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
