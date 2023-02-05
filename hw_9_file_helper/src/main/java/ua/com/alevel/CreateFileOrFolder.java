package ua.com.alevel;

import java.io.File;
import java.io.IOException;

public class CreateFileOrFolder {
    public void createNewFile( String currentDirectoryLocation, String enteredFileName) throws IOException {
        String newFileName = currentDirectoryLocation + "\\" + enteredFileName;
        File newFile = new File(newFileName);
        if(newFile.exists()) {
            System.out.println("File already exist at path :\n" + newFile.getPath());
            System.out.println("Unable to Create File");
        }
        else {
            boolean created = newFile.createNewFile();
            if (created)
                System.out.println("File successfully created at path :\n" + newFile.getPath());
        }
    }

    public void createNewDirectory( String currentDirectoryLocation, String enteredDirectoryName) throws IOException {
        File newDirectory = new File(currentDirectoryLocation+"\\"+enteredDirectoryName);
        if(newDirectory.exists()) {
            System.out.println("Directory already exist at path :\n"+newDirectory.getPath());
            System.out.println("Unable to Create Directory");
        }
        else {
            boolean created = newDirectory.mkdirs();
            if (created)
                System.out.println("Directory successfully created at path :\n"+newDirectory.getPath());
        }
    }
}
