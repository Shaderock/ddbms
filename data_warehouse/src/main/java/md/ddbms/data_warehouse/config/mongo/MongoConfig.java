package md.ddbms.data_warehouse.config.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

@Configuration
@EnableMongoRepositories(basePackages = "md.ddbms.data_warehouse.repositories")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "bestsndb";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString =
                new ConnectionString("mongodb://localhost:" +
                        DataWarehouseContext.datawarehousePortSettings.getMongoDBPort() + "/" +
                        getDatabaseName());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("md.ddbms.data_warehouse");
    }
}
