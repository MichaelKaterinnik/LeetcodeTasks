package org.example.IOstreams;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Advanced file search
 * Let's implement a custom search for files in a directory.
 * Review the java.nio.file.FileVisitor interface and its base implementation java.nio.file.SimpleFileVisitor.
 * While searching through a file tree using the Files.walkFileTree(Path start,
 * FileVisitor<? super Path> visitor) method we use the FileVisitor object to perform the necessary operations
 * on the found Path objects.
 *
 * Our search class will be called SearchFileVisitor and extend SimpleFileVisitor.
 *
 * The search can be performed using the following criteria:
 * - expression found in the file name (String);
 * - an expression found in the file content (String);
 * - maximum and minimum file size (int).
 * You can specify either one or several search criteria at once.
 *
 * I wrote a code in main that uses the ready-made SearchFileVisitor to search for files, you are left with a very
 * easy task - to implement it.
 */
public class WalkFileTree {
    public static void main(String[] args) throws IOException {
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();

        searchFileVisitor.setPartOfName("amigo");
        searchFileVisitor.setPartOfContent("programmer");
        searchFileVisitor.setMinSize(50);
        searchFileVisitor.setMaxSize(10000);

        Files.walkFileTree(Paths.get("D:/SecretFolder"), searchFileVisitor);

        List<Path> foundFiles = searchFileVisitor.getFoundFiles();
        for (Path file : foundFiles) {
            System.out.println(file);
        }
    }

    public static class SearchFileVisitor extends SimpleFileVisitor<Path> {
        private String partOfName;
        private String partOfContent;
        private int minSize;
        private int maxSize;
        private List<Path> foundFiles = new ArrayList<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            byte[] content = Files.readAllBytes(file); // размер файла: content.length
            boolean needToAdd = true;

            if (minSize > 0 && !checkMinSize(minSize, content)) needToAdd = false;
            if (maxSize > 0 && !checkMaxSize(maxSize, content)) needToAdd = false;
            if (partOfName != null && !checkName(partOfName, file)) needToAdd = false;
            if (partOfContent != null && !checkContent(partOfContent, file)) needToAdd = false;

            if (needToAdd) foundFiles.add(file);

            return super.visitFile(file, attrs);
        }

        private boolean checkMinSize(int minSize, byte[] content) {
            return content.length > minSize;
        }
        private boolean checkMaxSize(int maxSize, byte[] content) {
            return content.length < maxSize;
        }
        private boolean checkName(String partOfName, Path file) {
            return file.getFileName().toString().contains(partOfName);
        }
        private boolean checkContent(String partOfName, Path file) throws IOException {
            List<String> fileLines = Files.readAllLines(file);
            for (String s : fileLines) {
                if (s.contains(partOfName)) {
                    return true;
                }
            }
            return false;
        }

        public void setPartOfName(String partOfName) {
            this.partOfName = partOfName;
        }

        public void setPartOfContent(String partOfContent) {
            this.partOfContent = partOfContent;
        }

        public void setMinSize(int minSize) {
            this.minSize = minSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public List<Path> getFoundFiles() {
            return foundFiles;
        }
    }
}
