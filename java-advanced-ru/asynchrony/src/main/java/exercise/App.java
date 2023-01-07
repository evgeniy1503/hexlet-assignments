package exercise;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

class App {

    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static CompletableFuture<String> unionFiles(String fileOne, String fileTwo, String unionFile) throws ExecutionException, InterruptedException {


        CompletableFuture<String> futureFileOne = CompletableFuture.supplyAsync(() -> {
            Path paths = Paths.get(fileOne);
            String content = null;
            try {
                return content = Files.readString(paths);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return content;
            }

        });


        CompletableFuture<String> futureFileTwo = CompletableFuture.supplyAsync(() -> {
            Path paths = Paths.get(fileTwo);
            String content = null;
            try {
                return content = Files.readString(paths);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return content;
            }
        });

        CompletableFuture<String> unionContent = futureFileOne.thenCombine(futureFileTwo, (content1, content2) -> {
            Path path = Paths.get(unionFile);
            try {
                Files.writeString(path, content1);
                Files.writeString(path, content2, StandardOpenOption.APPEND);
                return "!!!";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("NoSuchFileException");
            return "ERROR";
        });
        return unionContent;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles(
                "./src/main/resources/file1.txt",
                "nonExistingFile",
                "./src/main/resources/result.txt");
        // END
    }
}

