package main.java.com.erstudio;

/**
 * @author akshit.arora
 * for the UI Skeleton
 */

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ERSStudioReleaseHelperUI {

    private JLabel labelTSString;
    private JLabel labelVersionString;
    private JLabel labelNewVersionString;
    private JLabel labelGUID;
    private JTextField textFieldTs;
    private JTextField textVersionString;
    private JTextField textNewVersionString;
    private JTextField textGUID;
    private JButton buttonBrowse;
    private JButton buttonGenerate;
    private JButton buttonChangeVersion;
    private JButton buttonReset;
    private ButtonGroup buttonGroupUpgradeType;
    private JRadioButton radioButtonFullUpgrade;
    private JRadioButton radioButtonPatchUpgrade;
    private JTextArea textAreaStatus;
    private JFrame mainFrame;
    private JTabbedPane tabbedPaneUI;
    private JPanel panelTeamServer;
    private JPanel panelDataArchitect;
    private JPanel panelHelp;

    public JPanel initTeamServerUI() {
        panelTeamServer = new JPanel();
        panelTeamServer.setLayout(new GridBagLayout());

        labelTSString = new JLabel(Constants.TS_MAIN_FOLDER);
        textFieldTs = new JTextField();
        textFieldTs.setEditable(false);
        buttonBrowse = new JButton(Constants.BROWSE);

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
        textFieldTs.setPreferredSize(new Dimension(200, 30));
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
        textVersionString.setPreferredSize(new Dimension(200, 30));
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
        textNewVersionString.setPreferredSize(new Dimension(200, 30));
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
        textGUID.setPreferredSize(new Dimension(200, 30));
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
        radioButtonFullUpgrade = new JRadioButton(Constants.FULL_UPGRADE);
        radioButtonPatchUpgrade = new JRadioButton(Constants.PATCH_UPGRADE);
        buttonGroupUpgradeType.add(radioButtonFullUpgrade);
        buttonGroupUpgradeType.add(radioButtonPatchUpgrade);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridy = 5;
        panelTeamServer.add(radioButtonFullUpgrade, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        panelTeamServer.add(radioButtonPatchUpgrade, gridBagConstraints);

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

        // adding listeners to the components
        addListeners();

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

    public void onPressedBrowse() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // set the selection mode to directories only
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // invoke the showsOpenDialog function to show the save dialog
        int stateFileChooser = fileChooser.showOpenDialog(null);

        if (stateFileChooser == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected directory
            textFieldTs.setText(fileChooser.getSelectedFile().getAbsolutePath());
            // invoke method to validate selected directory path
            validateSelectedDirectory();
        }
    }

    // method to match version with the regex
    public boolean isValidVersionNumber(String tabTitle, String version) {
        if (tabTitle.equals(Constants.TEAM_SERVER)) {
            return Pattern.matches(Constants.ERS_STUDIO_RELEASE_VERSION_PATTERN, version);
        }

        if (tabTitle.equals(Constants.DATA_ARCHITECT)) {
            return false;
        } else {
            return false;
        }
    }

    // method to check if the directory is valid or not
    public boolean isValidDirectory(String tabTitle, String path) {
        File directory = new File(path);

        if (!directory.isDirectory()) {
            return false;
        }

        if (tabTitle.equals(Constants.TEAM_SERVER)) {
            FilenameFilter directoryFilter = new FilenameFilter() {
                public boolean accept(File file, String fileName) {
                    if (file.isDirectory()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            ArrayList<String> directoryList = new ArrayList<>(Arrays.asList(directory.list(directoryFilter)));
            if (directoryList.contains(Constants.ERS_STUDIO_DIRECTORY_CONFIGURATOR) && directoryList.contains(Constants.ERS_STUDIO_DIRECTORY_ERSPORTAL)) {
                return true;
            } else {
                return false;
            }
        }

        if (tabTitle.equals(Constants.DATA_ARCHITECT)) {
            return false;
        } else {
            return false;
        }
    }

    // method to validate directory path
    public void validateSelectedDirectory() {
        if (!isValidDirectory(tabbedPaneUI.getTitleAt(tabbedPaneUI.getSelectedIndex()), textFieldTs.getText())) {
            textFieldTs.setBackground(new Color(255, 153, 153));
        } else {
            textFieldTs.setBackground(tabbedPaneUI.getSelectedComponent().getBackground());
        }
    }

    // method to add all the required listeners
    public void addListeners() {
        // add action listener to browse button
        buttonBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPressedBrowse();
            }
        });

        // add key listener to old version text field
        textVersionString.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent EVT) {
                String versionValue = textVersionString.getText();
                if (!isValidVersionNumber(tabbedPaneUI.getTitleAt(tabbedPaneUI.getSelectedIndex()), versionValue)) {
                    textVersionString.setBackground(new Color(255, 153, 153));
                } else {
                    textVersionString.setBackground(Color.WHITE);
                }
            }
        });

        // add key listener to new version text field
        textNewVersionString.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent EVT) {
                String versionValue = textNewVersionString.getText();
                if (!isValidVersionNumber(tabbedPaneUI.getTitleAt(tabbedPaneUI.getSelectedIndex()), versionValue)) {
                    textNewVersionString.setBackground(new Color(255, 153, 153));
                } else {
                    textNewVersionString.setBackground(Color.WHITE);
                }
            }
        });
    }

    public ERSStudioReleaseHelperUI() {
        mainFrame = new JFrame(Constants.ERS_STUDIO_RELEASE_HELPER);

        tabbedPaneUI = new JTabbedPane();
        tabbedPaneUI.setBounds(50, 50, 600, 570);

        JPanel panelTeamServerUI = initTeamServerUI();
        JPanel panelDataArchitectUI = initDataArchitectUI();
        JPanel panelHelpUI = initHelpUI();

        tabbedPaneUI.add(Constants.TEAM_SERVER, panelTeamServerUI);
        tabbedPaneUI.add(Constants.DATA_ARCHITECT, panelDataArchitectUI);
        tabbedPaneUI.add(Constants.HELP, panelHelpUI);

        mainFrame.add(tabbedPaneUI);
        mainFrame.setSize(600, 570);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
