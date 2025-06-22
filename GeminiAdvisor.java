import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GeminiAdvisor {

    private static String apiKey;

    static {
    try {
        byte[] bytes = Files.readAllBytes(Paths.get("APIKEY.txt"));
        apiKey = new String(bytes).trim();
        System.out.println("Loaded API key: " + apiKey); // DEBUG
    } catch (IOException e) {
      apiKey = "";
      e.printStackTrace(); // So you see exactly what's wrong
      System.err.println("Failed to load API key.");
      }
    }


    public static String getAdvice(String savingGoal, String averageSpending) {
    if (apiKey.isEmpty()) {
        return "API key is missing.";
    }

    try {
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey;

        double avg = Double.parseDouble(averageSpending);
        String promptText = "A user has a saving goal of $" + savingGoal +
    ". Their average monthly spending is $" + String.format("%.2f", avg) +
    ". Give a short (under 100 words) practical financial advice plan to help them reach their goal. Be realistic and avoid fluff.";

String requestBody = "{"
    + "\"prompt\": {"
    +     "\"text\": \"" + promptText.replace("\"", "\\\"") + "\""
    + "},"
    + "\"temperature\": 0.7,"
    + "\"maxOutputTokens\": 256"
    + "}";



        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestBody.getBytes("utf-8"));
        }

        int status = connection.getResponseCode();
        System.out.println("HTTP Status Code: " + status);

        InputStream stream;
        if (status >= 200 && status < 300) {
            stream = connection.getInputStream();
        } else {
            stream = connection.getErrorStream();
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
        }
        System.out.println("Response: " + response.toString());
        System.out.println("Raw API Response: " + response.toString());

        // Extract the 'text' field from the JSON response
        Matcher matcher = Pattern.compile("\"text\":\"(.*?)\"").matcher(response.toString());
        if (matcher.find()) {
            return matcher.group(1).replace("\\n", "\n");
        }

    } catch (Exception e) {
        e.printStackTrace();
        return "Failed to get advice from Gemini.";
    }

    return "No advice available.";
  }
}