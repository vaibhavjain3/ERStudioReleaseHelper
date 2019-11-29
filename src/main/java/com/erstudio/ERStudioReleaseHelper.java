/**
 *
 */
package main.java.com.erstudio;

import main.java.com.erstudio.tsversionchange.ChangeTSVersionNumber;
import main.java.com.erstudio.tsversionchange.model.VersionInputModel;

import java.io.IOException;

/**
 * @author vaibhav.jain
 *
 */
public class ERStudioReleaseHelper {

    private ChangeTSVersionNumber changeTSVersionNumber;

    /**
     * Launches ERStudio Helper Application
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // user input model
        VersionInputModel versionInputModel = new VersionInputModel("C:\\Idera\\ERStudio", "18.0", "18.1", "2567yug", 1);
        // change version number
        ChangeTSVersionNumber changeTSVersionNumber = new ChangeTSVersionNumber(versionInputModel);
    }
}
