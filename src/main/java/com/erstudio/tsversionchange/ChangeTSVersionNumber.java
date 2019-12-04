package main.java.com.erstudio.tsversionchange;

import main.java.com.erstudio.constants.Constants;
import main.java.com.erstudio.tsversionchange.Exception.VersionChangeException;
import main.java.com.erstudio.tsversionchange.model.ExcelModel;
import main.java.com.erstudio.tsversionchange.model.VersionInputModel;
import main.java.com.erstudio.tsversionchange.model.VersionFormat;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shrey.pasari
 * @description This entity will fetch the inputs provided by user and will process accordingly to update the version
 */
public class ChangeTSVersionNumber {

    ProcessExcel processExcel;

    public ChangeTSVersionNumber(VersionInputModel versionInputModel) {
        // processing excel file
        this.processExcel = new ProcessExcel(Constants.VERSION_HELPER_SHEET);
    }

    public List<String> changeVersion(VersionInputModel versionInputModel) {
        List<String> response = new ArrayList<>();

        // back up all files
        processExcel.getProcessedExcelList().forEach(row -> {
            String directory = row.getFilepath().substring(0, row.getFilepath().lastIndexOf("\\"));
            Path path = Paths.get(versionInputModel.getFilePath() + "\\backup\\" + directory);
            try {
                if (!Files.exists(path))
                    Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String source = versionInputModel.getFilePath() + row.getFilepath();
            String target = versionInputModel.getFilePath() + "\\backup" + row.getFilepath();
            backUpFiles(source, target);
        });

       // processExcel.getProcessedExcelList().forEach(row ->
        for(ExcelModel row:processExcel.getProcessedExcelList())
        {
            VersionFormat oldVersion = MapVersionFormat(versionInputModel.getOldVersion());
            VersionFormat newVersion = MapVersionFormat(versionInputModel.getNewVersion());
            String versionFormat = row.getVersionFormat().getFormat();
            String oldVersionString = versionFormat.replace("Major", oldVersion.getMajorVersion()).replace("Minor", oldVersion.getMinorVersion())
                    .replace("Patch", oldVersion.getRevisionNumber()).replace("BuildNumberUpdate", oldVersion.getBuildNumber());
            String newVersionString = versionFormat.replace("Major", newVersion.getMajorVersion()).replace("Minor", newVersion.getMinorVersion())
                    .replace("Patch", newVersion.getRevisionNumber()).replace("BuildNumberUpdate", newVersion.getBuildNumber());
            String filepath = versionInputModel.getFilePath() + row.getFilepath();
            try {
                replaceVersionInFile(filepath, oldVersionString, newVersionString, response);
            } catch (VersionChangeException e) {
                e.printStackTrace();
                response.add("restoring backup files...");
                restoreBackUpFiles(versionInputModel);
                response.add("all files are rolled back.");
                break;

            }
        }
        return response;
    }

    private VersionFormat MapVersionFormat(String version) {
        String[] versionArr = version.split("\\.");
        return new VersionFormat(versionArr[0], versionArr[1], versionArr[2], versionArr[3]);
    }

    private List<String> replaceVersionInFile(String filepath, String search, String replace, List<String> response) throws VersionChangeException {
        Path path = Paths.get(filepath);
        Charset charset = StandardCharsets.UTF_8;

        String content = null;
        try {
            content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(search, replace);
            Files.write(path, content.getBytes(charset));
            response.add("Successfully changed version in file " + filepath);
        } catch (Exception e) {
            response.add("Error in changing version in file " + filepath);
            throw new VersionChangeException(e.getMessage());
        }
        return response;
    }

    private void backUpFiles(String sourcePath, String targetPath) {
        Path source = Paths.get(sourcePath);
        Path target = Paths.get(targetPath);
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void restoreBackUpFiles(VersionInputModel versionInputModel) {
        this.processExcel.getProcessedExcelList().forEach(row -> {
            String source = versionInputModel.getFilePath() + "\\backup" + row.getFilepath();
            String target = versionInputModel.getFilePath() + row.getFilepath();
            backUpFiles(source, target);
        });
    }
}