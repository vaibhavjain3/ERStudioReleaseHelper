/**
 *
 */
package main.java.com.erstudio;

import main.java.com.erstudio.tsversionchange.ChangeTSVersionNumber;
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
        ERStudioReleaseHelper erStudioReleaseHelper = new ERStudioReleaseHelper();
        erStudioReleaseHelper.readExcel();
    }

    // function to be triggered from UI button
    public void readExcel() {
        // set filepath of excel file
        ChangeTSVersionNumber changeTSVersionNumber = new ChangeTSVersionNumber("C:\\Idera\\ERStudio\\ERStudioReleaseHelper\\ERStudio.xlsx", "18.0", "18.1", "2567yug", 1);
        try {
            changeTSVersionNumber.readExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
