package main.java.com.erstudio.tsversionchange;

import main.java.com.erstudio.constants.Constants;
import main.java.com.erstudio.tsversionchange.model.VersionInputModel;
import main.java.com.erstudio.tsversionchange.model.VersionFormat;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        processExcel.getProcessedExcelList().stream().forEach(row ->
        {
            VersionFormat oldVersion = MapVersionFormat(versionInputModel.getOldVersion());
            VersionFormat newVersion = MapVersionFormat(versionInputModel.getNewVersion());
            String versionFormat = row.getVersionFormat().getFormat();
            String oldVersionString = versionFormat.replace("Major", oldVersion.getMajorVersion()).replace("Minor", oldVersion.getMinorVersion())
                    .replace("Patch", oldVersion.getRevisionNumber()).replace("BuildNumberUpdate", oldVersion.getBuildNumber());
            String newVersionString = versionFormat.replace("Major", newVersion.getMajorVersion()).replace("Minor", newVersion.getMinorVersion())
                    .replace("Patch", newVersion.getRevisionNumber()).replace("BuildNumberUpdate", newVersion.getBuildNumber());
            String filepath = versionInputModel.getFilePath() + row.getFilepath();
            replaceVersionInFile(filepath, oldVersionString, newVersionString, response);
        });
        return response;
    }

    public static VersionFormat MapVersionFormat(String version) {
        String[] versionArr = version.split("\\.");
        return new VersionFormat(versionArr[0], versionArr[1], versionArr[2], versionArr[3]);
    }

    public static List<String> replaceVersionInFile(String filepath, String search, String replace, List<String> response) {
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
        }
        return response;
    }
}