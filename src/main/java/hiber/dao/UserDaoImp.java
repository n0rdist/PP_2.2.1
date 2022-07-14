package hiber.dao;

import hiber.config.AppConfig;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      Session currentSession = sessionFactory.getCurrentSession();
      if (user.getCar() != null) {
         currentSession.save(user.getCar());
      }
      currentSession.save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserWithCar(String model, int series) {
      Session currentSession= sessionFactory.getCurrentSession();
      TypedQuery<User> typedQuery=currentSession.createQuery("select u from User u where u.car.model=:model and u.car.series=:series",User.class);
      typedQuery.setParameter("model",model);
      typedQuery.setParameter("series",series);
      return typedQuery.getSingleResult();
   }


}
