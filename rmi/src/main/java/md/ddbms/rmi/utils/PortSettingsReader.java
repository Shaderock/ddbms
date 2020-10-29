package md.ddbms.rmi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.ddbms.rmi.models.port_settings.ServerPortSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PortSettingsReader {
    private static final String portSettingsFileName = "portSettings.json";

    public static ServerPortSettings getPortSettings() throws FileNotFoundException, JsonProcessingException {
        File portSettingsFile = new File(portSettingsFileName);
        Scanner scanner = new Scanner(portSettingsFile);
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(stringBuilder.toString(), ServerPortSettings.class);
    }
}
