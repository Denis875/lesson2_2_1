package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private static final String SELECT_USER_BY_CAR = "from User as user inner join fetch user.car as car where car.model=:model and car.series=:series";
    private static final String SELECT_USER = "from User";

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(SELECT_USER);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsersByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_CAR);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList();
    }
}
