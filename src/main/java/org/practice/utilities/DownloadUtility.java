package org.practice.utilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadUtility {

        private static final String DOWNLOAD_PATH =
                "src/test/java/org/practice/testData/downloads";

        public static String getDownloadDir() {
            Path path = Paths.get(DOWNLOAD_PATH).toAbsolutePath();
            File dir = path.toFile();

            if (!dir.exists()) {
                dir.mkdirs();
            }
            return path.toString();
        }

        public static void cleanDownloadDir() {
            File dir = new File(getDownloadDir());
            for (File file : dir.listFiles()) {
                file.delete();
            }
        }

    public static File waitForDownloadedFile(
            String downloadDir,
            String expectedFileName,
            int timeoutInSeconds) {

        File file = new File(downloadDir + File.separator + expectedFileName);

        int waited = 0;
        while (waited < timeoutInSeconds) {
            if (file.exists() && file.length() > 0) {
                return file;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
            waited++;
        }
        throw new RuntimeException(
                "File not downloaded: " + expectedFileName
        );
    }

}


