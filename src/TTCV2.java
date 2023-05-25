import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TTCV2 {
    private static final String FILE_PATH = "./usernames/tiktok.txt";
    private static final String HITS_FILE_PATH = "./usernames/tiktok_hits.txt";
    // ANSI escape codes for console colors
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        createDirectory("./usernames");
        createFile("./usernames/tiktok.txt");
        createFile("./usernames/tiktok_hits.txt");
        printPattern();
        System.out.println("\nWelcome to TTC V2!");
        System.out.println("Rewritten version of TTC in Java. JDK: 20.0.1");
        System.out.println();

        List<String> usernames = readUsernamesFromFile();
        checkUsernames(usernames);

        // Delay before exiting the program
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void checkUsernames(List<String> usernames) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Adjust the number of threads as needed

        for (String username : usernames) {
            executor.execute(() -> {
                try {
                    URL url = new URL("https://www.tiktok.com/@" + username);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setInstanceFollowRedirects(false);
                    int statusCode = connection.getResponseCode();

                    if (statusCode == HttpURLConnection.HTTP_OK) {
                        System.out.print("[TTC V2]: " + ANSI_RED + "[UNAVAILABLE] " + ANSI_RESET);
                        System.out.println("https://www.tiktok.com/@" + username);
                    } else {
                        System.out.print("[TTC V2]: " + ANSI_GREEN + "[AVAILABLE OR BANNED] " + ANSI_RESET);
                        System.out.println("https://www.tiktok.com/@" + username);
                        writeUsernameToFile(username);
                    }
                } catch (IOException e) {
                    System.out.print("[TTC V2]: " + ANSI_GREEN + "[AVAILABLE OR BANNED] " + ANSI_RESET);
                    System.out.println("https://www.tiktok.com/@" + username);
                    writeUsernameToFile(username);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readUsernamesFromFile() {
        List<String> usernames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String username;
            while ((username = reader.readLine()) != null) {
                usernames.add(username);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usernames;
    }

    private static void writeUsernameToFile(String username) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HITS_FILE_PATH, true))) {
            writer.write(username);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPattern() {
        System.out.println("#### ##  #### ##   ## ##            ### ###  ## ##");
        System.out.println("# ## ##  # ## ##  ##   ##            ##  ##  ##  ##");
        System.out.println("  ##       ##     ##                 ##  ##      ##");
        System.out.println("  ##       ##     ##                 ##  ##     ## ");
        System.out.println("  ##       ##     ##                 ### ##    ##  ");
        System.out.println("  ##       ##     ##   ##             ###     #   ##");
        System.out.println(" ####     ####     ## ##               ##    ######");
    }

    private static void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("[TTC V2]: Directory created: " + directoryPath);
            } else {
                System.out.println("[TTC V2]: Failed to create directory: " + directoryPath);
            }
        }
    }

    private static void createFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("[TTC V2]: File created: " + filePath);
                } else {
                    System.out.println("[TTC V2]: Failed to create file: " + filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}