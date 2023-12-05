package org.example.IOstreams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Implement the downloadFile(String urlString, Path downloadDirectory) method, the input of which is a link to
 * download the file and the folder where you want to download the file.
 *
 * All links look like:
 * https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png
 * http://toogle.com/files/rules.txt
 * https://pacemook.com/photos/image1.jpg
 *
 * The method must create a URL object and download the contents of the file to the local disk.
 * Download to a temporary directory first, so that if the download is unsuccessful, there are no undownloaded files
 * left in your directory.
 *
 * Then move the file to the user directory. Take the name for the file from the link.
 * Use only classes and methods from the java.nio package.
 */
public class SavingFileFromWeb {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/SecretFolder"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL downPass = new URL(urlString);
        InputStream is = downPass.openStream();

        Path downTemp = Files.createTempFile(null, null);
        Files.copy(is, downTemp, StandardCopyOption.REPLACE_EXISTING);

        Path copiedFilePath = downloadDirectory.resolve(downPass.getFile()).getFileName();
        Files.move(downTemp, copiedFilePath, StandardCopyOption.REPLACE_EXISTING);

        return copiedFilePath;
    }
}
