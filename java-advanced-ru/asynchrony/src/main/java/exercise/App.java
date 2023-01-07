package exercise;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;


class App {

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<String> unionFiles(String source1, String source2, String dest) {

        CompletableFuture<String> content1 = CompletableFuture.supplyAsync(() -> {
            String content = "";

            try {
                content = Files.readString(getFullPath(source1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        CompletableFuture<String> content2 = CompletableFuture.supplyAsync(() -> {

            String content = "";
            try {
                content = Files.readString(getFullPath(source2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        return content1.thenCombine(content2, (cont1, cont2) -> {
            String union = cont1 + cont2;
            try {
                Files.writeString(getFullPath(dest), union, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "ok!";

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String path) {

        CompletableFuture<Long> sum = CompletableFuture.supplyAsync(() -> {
            Long size = 0L;
            List<Path> paths = null;
            try {
                paths = Files.walk(Paths.get(path), 1)
                        .filter(file -> Files.isRegularFile(file))
                        .toList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Path file : paths) {
                try {
                    size = size + Files.size(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return size;
        });
        return sum;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles(
                "./src/main/resources/file1.txt",
                "./src/main/resources/file1.txt",
                "./src/main/resources/result.txt");
        // END
    }
}

