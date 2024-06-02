package Springboot.Api.Config;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.annotation.Validated;

@Configuration
@EnableMongoRepositories(basePackages = "Springboot.Api.Repository")
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${connection}")
    private String mongodbUri;

@Override
protected String getDatabaseName()
{
    return "Mydb";
}
@Override
@Bean
//@Override
    public MongoClient mongoClient() {
    ConnectionString connectionString = new ConnectionString(mongodbUri);
//    MongoClientSettings mongoClientSettings=MongoClientSettings.builder().applyConnectionString(connectionString).build();
    return MongoClients.create(connectionString);
}
@Bean
public MongoTemplate mongoTemplate(){

    return new MongoTemplate(mongoClient(),getDatabaseName());
}
}
//@Configuration
//public class MongoConfig{
//    @Value("${spring.data.mongodb.uri}")
//    private String mongodbUri;
//
//}