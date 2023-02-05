package ua.com.alevel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileHelper {

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your options");
        String select;
        menu();
        while ((select = reader.readLine()) != null) {
            fileHelperOperations(reader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want see all files in directory, please enter 1");
        System.out.println("If you want create file, please enter 2");
        System.out.println("If you want create directory, please enter 3");
        System.out.println("If you want delete file, please enter 4");
        System.out.println("If you want delete directory, please enter 5");
        System.out.println("If you want copy file to directory, please enter 6");
        System.out.println("If you want copy directory to directory, please enter 7");
        System.out.println("If you want find file, please enter 8");
        System.out.println("If you want find directory, please enter 9");
        System.out.println("If you want find text content in files in same location, please enter 10");
        System.out.println("If you want close application, please enter 11");
        System.out.println();
    }

    private void fileHelperOperations(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : printFolderTree(reader); break;
            case "2" : createFile(reader); break;
            case "3" : createDirectory(reader); break;
            case "4" : deleteFile(reader); break;
            case "5" : deleteDirectory(reader); break;
            case "6" : copyFileToDirectory(reader); break;
            case "7" : copyDirectoryToDirectory(reader); break;
            case "8" : findFile(reader); break;
            case "9" : findDirectory(reader); break;
            case "10" : findTextContentInDirectory(reader); break;
            case "11" : stop(); break;
        }
        menu();
    }

    private void printFolderTree(BufferedReader reader) throws IOException {
        System.out.println("Print directory tree  >>");
        System.out.println("Please enter the folder location");
        String pathToFolder = reader.readLine();
        PrintFolderTree printFolderTree = new PrintFolderTree();
        System.out.println("Directory tree  >>");
        printFolderTree.printTree(pathToFolder);
    }

    private void createFile(BufferedReader reader) throws IOException {
        System.out.println("Create a new File  >>");
        System.out.println("Please enter the folder location");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter a new file name and it extension after dot");
        String nameNewFile = reader.readLine();
        CreateFileOrFolder createFile = new CreateFileOrFolder();
        createFile.createNewFile(pathToFolder, nameNewFile);
    }
    private void createDirectory(BufferedReader reader) throws IOException {
        System.out.println("Create a new Directory  >>");
        System.out.println("Please enter the folder location");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter a new directory name");
        String nameNewDirectory = reader.readLine();
        CreateFileOrFolder createDirectory = new CreateFileOrFolder();
        createDirectory.createNewDirectory(pathToFolder, nameNewDirectory);
    }
    private void deleteFile(BufferedReader reader) throws IOException {
        System.out.println("Delete File  >>");
        System.out.println("Please enter the folder location");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter a file name and it extension after dot");
        String nameDeleteFile = reader.readLine();
        DeleteFileOrDirectory deleteFile = new DeleteFileOrDirectory();
        deleteFile.deleteFileFolder(pathToFolder, nameDeleteFile);
    }

    private void deleteDirectory(BufferedReader reader) throws IOException {
        System.out.println("Delete Directory  >>");
        System.out.println("Please enter the folder location");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter a directory name");
        String nameDeleteDirectory = reader.readLine();
        DeleteFileOrDirectory deleteDirectory = new DeleteFileOrDirectory();
        deleteDirectory.deleteFileFolder(pathToFolder, nameDeleteDirectory);
    }

    private void copyFileToDirectory(BufferedReader reader) throws IOException {
        System.out.println("Copy File To Directory  >>");
        System.out.println("Please enter the source location");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter a directory name");
        String nameDirectory = reader.readLine();
        Copy copyFile = new Copy();
        File source = new File(pathToFolder);
        File target = new File(nameDirectory);
        copyFile.copyFileToDirectory(source, target);
    }
    private void copyDirectoryToDirectory(BufferedReader reader) throws IOException {
        System.out.println("Copy Directory To Directory  >>");
        System.out.println("Please enter the source directory location");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter the destination directory location");
        String nameDirectory = reader.readLine();
        Copy copyDirectory = new Copy();
        File source = new File(pathToFolder);
        File target = new File(nameDirectory);
        if (!target.exists()) {
            target.mkdirs();
        }
        String dest = nameDirectory + "\\"+ source.getName();
        File destination = new File(dest);
        copyDirectory.copyToDirectory(source, destination);
        System.out.println("Copy finished successfully");
        System.out.println("Source Path >>"+ pathToFolder);
        System.out.println("Destination Path >>"+ dest);
    }
    private void findFile(BufferedReader reader) throws IOException {
        System.out.println("Find File  >>");
        System.out.println("Please enter the folder location for search");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter a file name and it extension after dot");
        String nameFile = reader.readLine();
        Search searchFile = new Search();
        searchFile.search(pathToFolder, nameFile);
    }
    private void findDirectory(BufferedReader reader) throws IOException {
        System.out.println("Find Directory  >>");
        System.out.println("Please enter the folder location for search");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter a directory name");
        String nameDirectory = reader.readLine();
        Search searchDirectory = new Search();
        searchDirectory.search(pathToFolder, nameDirectory);
    }

    private void findTextContentInDirectory(BufferedReader reader) throws IOException {
        System.out.println("Find Text Content In Directory  >>");
        System.out.println("Please enter the folder location for search");
        String pathToFolder = reader.readLine();
        System.out.println("Please enter a text for search");
        String text = reader.readLine();
        Search searchText = new Search();
        searchText.selectTextInFilesDirectory(pathToFolder, text);
    }
    private void stop() {
        System.exit(0);
    }

}
