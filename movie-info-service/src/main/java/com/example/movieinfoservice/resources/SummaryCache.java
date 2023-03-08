package com.example.movieinfoservice.resources;
import com.example.movieinfoservice.models.MovieSummary;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class SummaryCache {
    private final MongoCollection<Document> cacheCollection;

    public SummaryCache() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/MovieResourceCacheDB");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(mongoClientSettings);


        MongoDatabase database = mongoClient.getDatabase("MovieResourceCacheDB");
        this.cacheCollection = database.getCollection("moviesummary");
    }

    public void put(MovieSummary summary) {
        Document doc = new Document("_id", summary.getId()).append("title", summary.getTitle()).append("overview",summary.getOverview());
        cacheCollection.insertOne(doc);
    }

    public MovieSummary get(String id) {
        Document doc = cacheCollection.find(new Document("_id", id)).first();
        if (doc != null) {
            String overview = doc.get("overview").toString();
            String title = doc.get("title").toString();
            MovieSummary ms = new MovieSummary();
            ms.setId(id);
            ms.setTitle(title);
            ms.setOverview(overview);
            return ms;
        } else {
            return null;
        }
    }


}
