package org.APIAutomation.utility;



import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtility {
    public static JSONObject readJsonFile(String filePath) {
        try {
            // Read JSON file content
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Convert String to JSONObject
            return new JSONObject(jsonContent);
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON file: " + filePath, e);
        }
    }
}
