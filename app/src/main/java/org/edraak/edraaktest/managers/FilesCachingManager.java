package org.edraak.edraaktest.managers;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

/**
 * Read / write text
 */
public class FilesCachingManager {

    private Context appContext;
    private String fileName;

    /**
     * Create an instance
     *
     * @param context a context of the application package implementing
     *                this class.
     */
    public FilesCachingManager(Context context) {
        this.appContext = context.getApplicationContext();
    }

    /**
     * Save data to a file
     *
     * @param data data to be saved
     */
    public void saveToFile(String data) {
        try {
            String fullFilePath = getFullFilePath();
            FileWriter fileWriter = new FileWriter(fullFilePath);

            fileWriter.write(data);

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read text from a file
     *
     * @return text extracted
     */
    public String readFile() {
        String data = null;

        try {
            String fullFilePath = getFullFilePath();
            File file = new File(fullFilePath);

            FileInputStream fileInputStream = new FileInputStream(file);
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];

            //noinspection ResultOfMethodCallIgnored
            fileInputStream.read(buffer);

            data = new String(buffer);

            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private String getFullFilePath() {
        return String.format(Locale.ENGLISH, "%s/%s",
                appContext.getFilesDir().getPath(),
                fileName);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
