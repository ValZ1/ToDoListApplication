package ru.todolist.entity.dto;

import ru.todolist.entity.Record;

import java.util.List;

public class RecordsContainerDTO {
    private final List<Record> records;
    private final int numberOfDoneRecords;
    private final int numberOfActiveRecords;

    public RecordsContainerDTO(List<Record> records, int numberOfDoneRecords, int numberOfActiveRecords) {
        this.records = records;
        this.numberOfDoneRecords = numberOfDoneRecords;
        this.numberOfActiveRecords = numberOfActiveRecords;
    }
    public List<Record> getRecords() {
        return records;
    }

    public int getNumberOfDoneRecords() {
        return numberOfDoneRecords;
    }

    public int getNumberOfActiveRecords() {
        return numberOfActiveRecords;
    }
}
