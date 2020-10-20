package md.ddbms.bestsn.config.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

@Configuration
@EnableMongoRepositories(basePackages = "md.ddbms.bestsn.repositories")
public class MongoConfig extends AbstractMongoClientConfiguration {
    private int port = 50001;

    @Override
    protected String getDatabaseName() {
        return "bestsndb";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString =
                new ConnectionString("mongodb://localhost:" + port + "/" + getDatabaseName());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

//    @PostConstruct
    public void requestPort() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter port to connect: ");
        port = scanner.nextInt();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("md.ddbms.bestsn");
    }
}
