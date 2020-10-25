package md.ddbms.data_warehouse;

import lombok.RequiredArgsConstructor;
import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.Collections;
import java.util.Scanner;

@RequiredArgsConstructor
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class DataWarehouseApplication {

    public static void main(String[] args) {
        System.out.print("Enter DataWarehouse server port: ");
        int port = new Scanner(System.in).nextInt();
        DataWarehouseContext.serverPort = port;

        SpringApplication app = new SpringApplication(DataWarehouseApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", String.valueOf(port)));
        app.run(args);
    }

}
