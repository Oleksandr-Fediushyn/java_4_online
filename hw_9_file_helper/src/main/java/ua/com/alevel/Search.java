package ua.com.alevel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Search {
    private final List<String> selectFile = new ArrayList<>();
    public List<String> getSelectFile(){
        return selectFile;
    }
    public void search(String path, String element) throws IOException {
        Optional<String> maybeSearch = searchElement(path, element);
        if (maybeSearch.isPresent()) {
            if (maybeSearch.get().equals(""))
                System.out.println("Element was not found");
            else
                System.out.println(maybeSearch.get());
        } else {
            System.out.println("Path was not found");
        }
    }

    public Optional<String> searchElement(String path, String element) throws IOException {
        File file = new File(path);
        if (!file.exists()) return Optional.empty();
        if (file.isDirectory()) {
            System.out.println("    >>" + file.getAbsolutePath());
            return Optional.of(selectFileDirectory(path, element));
        }
        return Optional.empty();
    }
    public String selectFileDirectory(String currentDirectoryLocation, String searchElement) throws IOException {
        File directory = new File(currentDirectoryLocation);
        File[] fileList = directory.listFiles();
        assert fileList != null;
        String found = "";
        if (fileList.length != 0) {
            for (File fileElement : fileList) {
                if (!found.isEmpty()) return found;
                if (searchElement.equals(fileElement.getName())) {
                    found = "Element was found\n" + fileElement.getAbsolutePath();
                }
                if (fileElement.isDirectory() && found.isEmpty()) {
                    found = selectFileDirectory(fileElement.getAbsolutePath(), searchElement);
                }
            }
        }
        return found;
    }

    public void selectTextInFilesDirectory(String currentDirectoryLocation, String searchElement) throws IOException {
        TextInFilesDirectory(currentDirectoryLocation, searchElement);
        if (getSelectFile().size()>0) {
            System.out.println("Found entry in file");
            Stream.iterate(0, (i -> i < getSelectFile().size()), (i -> ++i))
                    .forEach(a -> System.out.println(getSelectFile().get(a)));
        } else
            System.out.println("Entry in file is not found");
    }

    private boolean testString(String filePath, String str) {
        BufferedReader br = null;
        File file = new File(filePath);
        boolean result = false;
        if(!file.exists())
            return false;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains(str))  {
                    result = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    private void TextInFilesDirectory(String currentDirectoryLocation, String searchElement) throws IOException {
        File directory = new File(currentDirectoryLocation);
        File[] fileList = directory.listFiles();
        assert fileList != null;
        String found = "";
        if (fileList.length != 0) {
            for (File fileElement : fileList) {
                if (fileElement.isFile()){
                    if (testString(fileElement.getAbsolutePath(), searchElement)) {
                        found = fileElement.getAbsolutePath();
                        selectFile.add(found);
                    }
                }
                if (fileElement.isDirectory()) {
                    TextInFilesDirectory(fileElement.getAbsolutePath(), searchElement);
                }
            }
        }
    }
}

