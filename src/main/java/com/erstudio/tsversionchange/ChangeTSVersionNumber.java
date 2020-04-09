package main.java.com.erstudio.tsversionchange;

import main.java.com.erstudio.constants.Constants;
import main.java.com.erstudio.model.UpgradeTypeEnum;
import main.java.com.erstudio.tsversionchange.Exception.VersionChangeException;
import main.java.com.erstudio.tsversionchange.model.ExcelModel;
import main.java.com.erstudio.tsversionchange.model.VersionInputModel;
import main.java.com.erstudio.tsversionchange.model.VersionFormat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author shrey.pasari
 * @description This entity will fetch the inputs provided by user and will process accordingly to update the version
 */
public class ChangeTSVersionNumber {

    ProcessExcel processExcel;

    public ChangeTSVersionNumber(VersionInputModel versionInputModel) throws Exception {
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
        for (ExcelModel row : processExcel.getProcessedExcelList()) {
            //check if current operation is required for the upgrade
            if (!isOperationRequired(row, versionInputModel)) {
                continue;
            }
            //execute each operation
            if(row.getOperationType().equals(Constants.OPERATION_TYPE_VERSION_CHANGE)) {
                VersionFormat oldVersion = MapVersionFormat(versionInputModel.getOldVersion());
                VersionFormat newVersion = MapVersionFormat(versionInputModel.getNewVersion());
                String versionFormat = row.getVersionFormat().getFormat();
                String oldVersionString = versionFormat.replace("Major", oldVersion.getMajorVersion()).replace("Minor", oldVersion.getMinorVersion())
                        .replace("Patch", oldVersion.getRevisionNumber()).replace("BuildNumber", oldVersion.getBuildNumber());
                String newVersionString = versionFormat.replace("Major", newVersion.getMajorVersion()).replace("Minor", newVersion.getMinorVersion())
                        .replace("Patch", newVersion.getRevisionNumber()).replace("BuildNumber", newVersion.getBuildNumber());
                String filepath = versionInputModel.getFilePath() + row.getFilepath();
                try {
                    replaceVersionInFile(filepath, oldVersionString, newVersionString, response);
                } catch (VersionChangeException e) {
                    e.printStackTrace();
                    response.add("restoring backup files...");
                    restoreBackUpFiles(versionInputModel);
                    response.add("all files are rolled back.");
                    return response;
                }
            } else if (row.getOperationType().equals(Constants.OPERATION_TYPE_GUID_UPDATE)) {
                String filepath = versionInputModel.getFilePath() + row.getFilepath();
                String newGUID = versionInputModel.getGUID();
                try {
                    replaceGUIDInFile(filepath, newGUID, response);
                } catch (VersionChangeException e) {
                    e.printStackTrace();
                    response.add("restoring backup files...");
                    restoreBackUpFiles(versionInputModel);
                    response.add("all files are rolled back.");
                    return response;
                }
            } else if (row.getOperationType().equals(Constants.OPERATION_TYPE_ADD_VERSION)) {
            	 VersionFormat oldVersion = MapVersionFormat(versionInputModel.getOldVersion());
                 VersionFormat newVersion = MapVersionFormat(versionInputModel.getNewVersion());
                 String versionFormat = row.getCustomData();
                 String oldVersionString = versionFormat.replace("Major", oldVersion.getMajorVersion()).replace("Minor", oldVersion.getMinorVersion())
                         .replace("Patch", oldVersion.getRevisionNumber()).replace("BuildNumber", oldVersion.getBuildNumber());
                 String newVersionString = versionFormat.replace("Major", newVersion.getMajorVersion()).replace("Minor", newVersion.getMinorVersion())
                         .replace("Patch", newVersion.getRevisionNumber()).replace("BuildNumber", newVersion.getBuildNumber());
                 String filepath = versionInputModel.getFilePath() + row.getFilepath();
                 try {
                	 addNewVersionInFile(filepath, oldVersionString, newVersionString, response);
                 } catch (VersionChangeException e) {
                     e.printStackTrace();
                     response.add("restoring backup files...");
                     restoreBackUpFiles(versionInputModel);
                     response.add("all files are rolled back.");
                     return response;
                 }
            }
        }
        response.add("version changed from " + versionInputModel.getOldVersion() + " --> " + versionInputModel.getNewVersion());
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
            content = content.replaceAll(Pattern.quote(search), replace);
            Files.write(path, content.getBytes(charset));
            response.add("Successfully changed version in file " + filepath);
        } catch (Exception e) {
            response.add("Error in changing version in file " + filepath);
            throw new VersionChangeException(e.getMessage());
        }
        return response;
    }

    private List<String> replaceGUIDInFile(String filepath, String replace, List<String> response) throws VersionChangeException {
        Path path = Paths.get(filepath);
        Charset charset = StandardCharsets.UTF_8;
        String content = null;
        try {
            File file = new File(filepath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            String oldProductId = getTSProductId(document.getDocumentElement());
            if (oldProductId.isEmpty()) {
                response.add("GUID not found in file " + filepath);
                return response;
            }
            content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(Pattern.quote(oldProductId), replace);
            Files.write(path, content.getBytes(charset));
            response.add("Successfully changed GUID in file " + filepath);
        } catch (Exception e) {
            response.add("Error in changing GUID in file " + filepath);
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

    private String getTSProductId(Node node) {
        if (node.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE && node.getNodeValue().startsWith(Constants.ERS_STUDIO_PRODUCT_ID)) {
            return node.getNodeValue().substring(13, node.getNodeValue().length() - 1);
        }
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node currentNode = nodes.item(i);
            String productId = getTSProductId(currentNode);
            if (!productId.isEmpty()) {
                return productId;
            }
        }
        return "";
    }
    
private void addNewVersionInFile(String filepath, String oldVersionString, String newVersionString, List<String> response) throws VersionChangeException {
    	
		Path path = Paths.get(filepath);
		Charset charset = StandardCharsets.UTF_8;
		
		String oldVersion = ".add( \""+oldVersionString+"\" )";
		String newVersion = "\n\t\t\t.add( \""+newVersionString+"\" )";
		String content = null;
		String newContent = null;
		try {
			content = new String(Files.readAllBytes(path), charset);
			int firstIndex = content.indexOf(oldVersion);
			StringBuffer newString = new StringBuffer(content); 
			newString.insert(firstIndex + oldVersion.length(), newVersion); 
	  
			// return the modified String 
			newContent = newString.toString();
			Files.write(path, newContent.getBytes(charset));
		    response.add("Successfully added version in file " + filepath);
		 } catch (Exception e) {
			response.add("Error in adding version in file " + filepath);
			throw new VersionChangeException(e.getMessage());
		 }
	}

    public boolean isOperationRequired(ExcelModel row, VersionInputModel versionInputModel) {
        if ((versionInputModel.getUpgradeType().equals(UpgradeTypeEnum.MAJOR_OR_MINOR_UPGRADE) && row.getFullVersion().equals("Yes"))
                || (versionInputModel.getUpgradeType().equals(UpgradeTypeEnum.PATCH_UPGRADE) && row.getPatchVersion().equals("Yes"))) {
            return true;
        }
        return false;
    }
}