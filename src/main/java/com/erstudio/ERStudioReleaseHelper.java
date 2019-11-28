/**
 *
 */
package main.java.com.erstudio;

import main.java.com.erstudio.tsversionchange.ChangeTSVersionNumber;

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
        ChangeTSVersionNumber changeTSVersionNumber = new ChangeTSVersionNumber("D://ERStudioReleaseHelper", "18.0", "18.1", "2567yug", 1);
    }

}
