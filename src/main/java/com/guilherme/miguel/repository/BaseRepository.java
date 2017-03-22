package com.guilherme.miguel.repository;

import com.arangodb.ArangoDatabase;

/**
 * @author Miguel Guilherme
 */
public abstract class BaseRepository<T> {

    private ArangoDatabase arangoDatabase;

    protected BaseRepository(ArangoDatabase arangoDatabase) {
        this.arangoDatabase = arangoDatabase;
    }

    /**
     * The collection to perform operations
     *
     * @return the name of the collection
     */
    public abstract String getCollection();

    /**
     * The document Class
     *
     * @return the document class
     */
    public abstract Class<T> getType();

    /**
     * Creates a new document from the given document
     *
     * @param document A representation of a single document
     */
    public void save(T document) {
        arangoDatabase.collection(getCollection()).insertDocument(document);
    }

    /**
     * Reads a single document
     *
     * @param key the key of the document
     * @return the document identified by the key
     */
    public T get(String key) {
        return arangoDatabase.collection(getCollection()).getDocument(key, getType());
    }
}
