package ua.com.alevel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PrintFolderTree {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void printTree(String path){
        Optional<String> maybePrint = tree(path);
        if (maybePrint.isPresent()) {
            System.out.println(maybePrint.get());
        } else {
            System.out.println("Element was not found");
        }
    }
    private Optional<String> tree(String path) {
        File file = new File(path);
        if ( !file.exists()) return Optional.empty();
        if ( file.isFile()) {
            System.out.println("    >>"+file.getAbsolutePath());
            return Optional.of(ANSI_YELLOW + file.getName() + " " + file.length() + " bytes" + ANSI_RESET);
        }
        if (file.isDirectory()) {
            System.out.println("    >>"+file.getAbsolutePath());
            return Optional.of(directoryTree(file, new ArrayList<>()));
        }
        return Optional.empty();
    }
    private String directoryTree(File folder, List<Boolean> lastFolders) {
        StringBuilder directory = new StringBuilder();
        if (lastFolders.size() != 0)
            directory.append(!(lastFolders.get(lastFolders.size() - 1)) ? "|-> " : "L-- ");
        directory.append(folder.getName()).append(" ").append(folderSize(folder));

        File[] files = folder.listFiles();
        assert files != null;
        int count = files.length;
        files = sortFiles(files);
        for (int i = 0; i < count; i++) {
            directory.append("\n");
            for (Boolean lastFolder : lastFolders) {
                if (lastFolder) {
                    directory.append("   ");
                } else {
                    directory.append("|  ");
                }
            }
            if (files[i].isFile()) {
                directory.append(i + 1 == count ? "L" : "|-").append("- ").append(ANSI_YELLOW).append(files[i].getName()).append(" ").append(files[i].length()).append(" bytes").append(ANSI_RESET);

            } else {
                ArrayList<Boolean> list = new ArrayList<>(lastFolders);
                list.add(i+1 == count);
                directory.append(directoryTree(files[i], list));
            }
        }
        return directory.toString();
    }
    private long getFolderSize(File folder) {
        long size = 0;
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                size += file.length();
            } else {
                size += getFolderSize(file);
            }
        }
        return size;
    }
    private String folderSize(File folder) {
        return getFolderSize(folder) + " bytes";
    }
    private File[] sortFiles(File[] folder) {
        Arrays.sort(folder);
        List<File> sorted = new ArrayList<>();
        for (File value : folder) {
            if (value.isDirectory()) sorted.add(value);
        }
        for (File file : folder) {
            if (file.isFile()) sorted.add(file);
        }
        return sorted.toArray(new File[0]);
    }
}