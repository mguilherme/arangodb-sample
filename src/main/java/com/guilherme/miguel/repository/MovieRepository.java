package com.guilherme.miguel.repository;

import com.arangodb.ArangoDatabase;
import com.guilherme.miguel.domain.Movie;
import org.springframework.stereotype.Repository;

/**
 * @author Miguel Guilherme
 */
@Repository
public class MovieRepository extends BaseRepository<Movie> {

    private static String COLLECTION = "movie";

    protected MovieRepository(ArangoDatabase arangoDatabase) {
        super(arangoDatabase);
    }

    @Override
    public String getCollection() {
        return COLLECTION;
    }

    @Override
    public Class<Movie> getType() {
        return Movie.class;
    }
}
