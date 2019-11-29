package main.java.com.erstudio;

/**
 * @author akshit.arora
 * for the UI Skeleton
 */
import main.java.com.erstudio.constants.Constants;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ERSStudioReleaseHelperUI {

    JLabel labelTSString, labelVersionString, labelNewVersionString, labelGUID;
    JTextField textFieldTs, textVersionString, textNewVersionString, textGUID;
    JButton buttonBrowse, buttonGenerate, buttonChangeVersion, buttonReset;
    ButtonGroup buttonGroupUpgradeType;
    JRadioButton jRadioButtonFullUpgrade, jRadioButtonPatchUpgrade;
    JTextArea textAreaStatus;
    JFrame mainFrame;
    JTabbedPane tabbedPaneUI;
    JPanel panelTeamServer, panelDataArchitect, panelHelp;

    public JPanel initTeamServerUI() {
        panelTeamServer = new JPanel();
        panelTeamServer.setLayout(new GridBagLayout());

        labelTSString = new JLabel(Constants.TS_MAIN_FOLDER);
        textFieldTs = new JTextField();
        textFieldTs.setEditable(false);
        buttonBrowse = new JButton(Constants.BROWSE);

        buttonBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPressedBrowse();
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        panelTeamServer.add(labelTSString, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 10;
        panelTeamServer.add(textFieldTs, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        panelTeamServer.add(buttonBrowse, gridBagConstraints);

        labelVersionString = new JLabel(Constants.STRING_TO_BE_REPLACED);
        textVersionString = new JTextField();

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        panelTeamServer.add(labelVersionString, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 10;
        panelTeamServer.add(textVersionString, gridBagConstraints);

        labelNewVersionString = new JLabel(Constants.NEW_VERSION_STRING);
        textNewVersionString = new JTextField();

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridy = 3;
        panelTeamServer.add(labelNewVersionString, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 10;
        panelTeamServer.add(textNewVersionString, gridBagConstraints);

        labelGUID = new JLabel(Constants.GUID);
        textGUID = new JTextField();
        buttonGenerate = new JButton(Constants.GENERATE);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridy = 4;
        panelTeamServer.add(labelGUID, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 10;
        panelTeamServer.add(textGUID, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        panelTeamServer.add(buttonGenerate, gridBagConstraints);

        buttonGroupUpgradeType = new ButtonGroup();
        jRadioButtonFullUpgrade = new JRadioButton(Constants.FULL_UPGRADE);
        jRadioButtonPatchUpgrade = new JRadioButton(Constants.PATCH_UPGRADE);
        buttonGroupUpgradeType.add(jRadioButtonFullUpgrade);
        buttonGroupUpgradeType.add(jRadioButtonPatchUpgrade);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridy = 5;
        panelTeamServer.add(jRadioButtonFullUpgrade, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        panelTeamServer.add(jRadioButtonPatchUpgrade, gridBagConstraints);

        buttonChangeVersion = new JButton(Constants.CHANGE_VERSION_NUMBER);
        buttonReset = new JButton(Constants.RESET);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 0;
        panelTeamServer.add(buttonChangeVersion, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 100;
        panelTeamServer.add(buttonReset, gridBagConstraints);

        textAreaStatus = new JTextArea();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        panelTeamServer.add(textAreaStatus, gridBagConstraints);
        textAreaStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return panelTeamServer;
    }

    public JPanel initDataArchitectUI() {
        panelDataArchitect = new JPanel();
        return panelDataArchitect;
    }

    public JPanel initHelpUI() {
        panelHelp = new JPanel();
        return panelHelp;
    }

    //on click the of browse button
    public void onPressedBrowse() {
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // set the selection mode to directories only
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // invoke the showsOpenDialog function to show the save dialog
        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected directory
            textFieldTs.setText(j.getSelectedFile().getAbsolutePath());
        }
    }

    public ERSStudioReleaseHelperUI() {
        mainFrame = new JFrame(Constants.ERS_STUDIO_RELEASE_HELPER);

        tabbedPaneUI = new JTabbedPane();
        tabbedPaneUI.setBounds(50, 50, 600, 570);

        JPanel panelTeamServerUI = initTeamServerUI();
        JPanel panelDataArchitectUI = initDataArchitectUI();
        JPanel panelHelpUI = initHelpUI();

        tabbedPaneUI.add(Constants.TEAM_SERVER, panelTeamServerUI);
        tabbedPaneUI.add(Constants.DATA_HELPER, panelDataArchitectUI);
        tabbedPaneUI.add(Constants.HELP, panelHelpUI);

        mainFrame.add(tabbedPaneUI);
        mainFrame.setSize(600, 570);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
