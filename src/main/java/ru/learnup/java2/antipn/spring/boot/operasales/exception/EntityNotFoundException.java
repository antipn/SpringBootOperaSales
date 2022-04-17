package ru.learnup.java2.antipn.spring.boot.operasales.exception;

public class EntityNotFoundException extends RuntimeException {

    private String entityName;
    private int entityId;

    public EntityNotFoundException(String entityName, int entityId, String message) {
        super(message);
        this.entityName = entityName;
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public int getEntityId() {
        return entityId;
    }
}