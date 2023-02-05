package ua.com.alevel;

import java.io.*;
import java.io.IOException;
import java.util.Optional;

public class DeleteFileOrDirectory {

    public void deleteFileFolder(String currentDirectoryLocation, String tempElementName) throws IOException {
        File directory = new File(currentDirectoryLocation + "\\" + tempElementName);
        Search search = new Search();
        Optional<String> maybeSearch = search.searchElement(currentDirectoryLocation, tempElementName);
        if (maybeSearch.isPresent()) {
            if (maybeSearch.get().equals(""))
                System.out.println("Element was not found");
            else {
                if (!directory.isFile()) {
                    deleteFolder(directory.getAbsolutePath());
                }
                if (directory.delete()) {
                    System.out.println("Element deleted successfully");
                }
            }
        } else {
            System.out.println("Path was not found");
        }
    }

    private void deleteFolder(String tempCurrentDirectory) throws IOException {
            File directory = new File(tempCurrentDirectory);
            File[] fileList = directory.listFiles();
            assert fileList != null;
            if (fileList.length != 0) {
                for (File fileElement : fileList) {
                    if (fileElement.isFile())
                        fileElement.delete();
                    else if (fileElement.isDirectory()) {
                        deleteFolder(fileElement.getAbsolutePath());
                        fileElement.delete();
                    }
                }
            }
    }
}




