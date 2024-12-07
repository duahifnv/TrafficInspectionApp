package dstu.trafficinspection.service;

import dstu.trafficinspection.entity.Driver;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final SessionFactory sessionFactory;
    public List<Driver> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Driver> query = session.createQuery("from Driver", Driver.class);
            return query.list();
        }
    }
    public Optional<Driver> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Driver> query = session.createQuery("from Driver where id=:id", Driver.class);
            query.setParameter("id", id);
            return query.stream().findAny();
        }
    }
    /*public Optional<List<Driver>> findByRegistrationCode(String registrationCode) {
        try (Session session = sessionFactory.openSession()) {
            Query<Driver> query = session.createQuery("from Driver where id=:id", Driver.class);
            query.setParameter("id", id);
            return query.stream().findAny();
        }
    }*/
}
