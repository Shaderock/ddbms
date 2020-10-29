package md.ddbms.data_warehouse.utils;

import md.ddbms.rmi.models.port_settings.DatawarehousePortSettings;
import md.ddbms.rmi.models.port_settings.ServerPortSettings;

import java.util.Scanner;

public class DataWarehouseContext {
    public static DatawarehousePortSettings datawarehousePortSettings;

    public static void setup(ServerPortSettings serverPortSettings) {
        System.out.print("Enter order number of this warehouse (1-based int): ");
        int number = new Scanner(System.in).nextInt();
        datawarehousePortSettings =
                serverPortSettings.getDatawarehousePortSettings().get(--number);
    }
}
