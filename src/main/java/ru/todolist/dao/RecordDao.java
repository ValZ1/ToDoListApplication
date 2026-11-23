package ru.todolist.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.todolist.entity.Record;
import ru.todolist.entity.RecordStatus;

import java.util.List;

@Repository
public class RecordDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Record> findAllRecords(){
        Query query = entityManager.createQuery("SELECT r FROM Record AS r ORDER BY id ASC");
        List<Record> records = query.getResultList();
        return records;
    }

    public void saveRecord(Record record){
        entityManager.persist(record);
    }
    public void updateRecordStatus(int id){
        Query query = entityManager.createQuery("UPDATE Record SET status = :newStatus WHERE id = :id");
        query.setParameter("id",id);
        query.setParameter("newStatus",RecordStatus.DONE);
        query.executeUpdate();
    }
    public void deleteRecord(int id) {
        Query query = entityManager.createQuery("DELETE Record WHERE id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
}
