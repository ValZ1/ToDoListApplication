package ru.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.todolist.dao.RecordDao;
import ru.todolist.entity.Record;
import ru.todolist.entity.RecordStatus;
import ru.todolist.entity.dto.RecordsContainerDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Transactional(readOnly = true)
    public RecordsContainerDTO findAllRecords(String filterMode){
        List<Record> records = recordDao.findAllRecords();
        int numDoneRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        int numActiveRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();


        if (filterMode == null || filterMode.isBlank()){
            return new RecordsContainerDTO(records,numDoneRecords,numActiveRecords);
        }

        String filterModeToUpperCase = filterMode.toUpperCase();
        List<String> allowedFilterModes = Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if (allowedFilterModes.contains(filterModeToUpperCase)){
            List<Record> filterRecords = records.stream()
                    .filter(record ->record.getStatus() == RecordStatus.valueOf(filterModeToUpperCase))
                    .toList();
            return new RecordsContainerDTO(filterRecords,numDoneRecords,numActiveRecords);
        } else{
            return new RecordsContainerDTO(records,numDoneRecords,numActiveRecords);
        }
    }

    public void saveRecord(String title){
        if (title != null && !title.isBlank()) {
            recordDao.saveRecord(new Record(title));
        }
    }
    public void updateRecordStatus(int id){
        recordDao.updateRecordStatus(id);
    }
    public void deleteRecord(int id){
        recordDao.deleteRecord(id);
    }
}
