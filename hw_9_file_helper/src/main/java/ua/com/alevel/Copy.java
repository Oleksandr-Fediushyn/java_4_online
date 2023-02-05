package ua.com.alevel;

import java.io.*;
import java.io.IOException;

public class Copy {

    public void copyToDirectory(File sourceLocation, File targetLocation) throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            String[] subDirectory = sourceLocation.list();
            for (int i = 0; i < subDirectory.length; i++) {
                copyToDirectory(new File(sourceLocation, subDirectory[i]),
                        new File(targetLocation, subDirectory[i]));
            }
        } else {
            readWrite(sourceLocation, targetLocation);
        }
    }

    public void copyFileToDirectory(File sourceLocation, File targetLocation) throws IOException {

        if (sourceLocation.isFile()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdirs();
            }
        }
        File targetLocation1 = new File(targetLocation + "\\"+ sourceLocation.getName());
        readWrite(sourceLocation, targetLocation1);
        System.out.println("Copy finished successfully");
        System.out.println("Source Path >>"+ sourceLocation);
        System.out.println("Destination Path >>"+ targetLocation + "\\"+ sourceLocation.getName());

    }
    private void readWrite(File sourceLocation, File targetLocation1) throws IOException {
        InputStream in = new FileInputStream(sourceLocation);
        OutputStream out = new FileOutputStream(targetLocation1);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}
