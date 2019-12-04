package main.java.com.erstudio;

/**
 * @author akshit.arora
 * for the UI Skeleton
 */

import main.java.com.erstudio.constants.Constants;
import main.java.com.erstudio.model.UpgradeTypeEnum;
import main.java.com.erstudio.tsversionchange.ChangeTSVersionNumber;
import main.java.com.erstudio.tsversionchange.model.VersionInputModel;
import main.java.com.erstudio.utility.InputUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ERSStudioReleaseHelperUI {

    private JLabel labelTSString;
    private JLabel labelDAString;
    private JLabel labelVersionString;
    private JLabel labelNewVersionString;
    private JLabel labelGUID;
    private JTextField textFieldTS;
    private JTextField textVersionStringTS;
    private JTextField textNewVersionStringTS;
    private JTextField textGUIDTS;
    private JTextField textFieldDA;
    private JTextField textVersionStringDA;
    private JTextField textNewVersionStringDA;
    private JTextField textGUIDDA;
    private JButton buttonBrowse;
    private JButton buttonGenerate;
    private JButton buttonChangeVersion;
    private JButton buttonReset;
    private ButtonGroup buttonGroupUpgradeTypeTS;
    private JRadioButton radioButtonMajorMinorUpgradeTS;
    private JRadioButton radioButtonPatchUpgradeTS;
    private ButtonGroup buttonGroupUpgradeTypeDA;
    private JRadioButton radioButtonMajorMinorDA;
    private JRadioButton radioButtonPatchUpgradeDA;
    private JTextArea textAreaStatusTS;
    private JTextArea textAreaStatusDA;
    private JFrame mainFrame;
    private JTabbedPane tabbedPaneUI;
    private JPanel panelTeamServer;
    private JPanel panelDataArchitect;
    private JPanel panelHelp;

    public JPanel initTeamServerUI() {
        panelTeamServer = new JPanel();
        panelTeamServer.setLayout(new GridBagLayout());

        labelTSString = new JLabel(Constants.TS_MAIN_FOLDER);
        textFieldTS = new JTextField();
        textFieldTS.setEditable(false);
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
        textFieldTS.setPreferredSize(new Dimension(200, 30));
        panelTeamServer.add(textFieldTS, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        panelTeamServer.add(buttonBrowse, gridBagConstraints);

        labelVersionString = new JLabel(Constants.STRING_TO_BE_REPLACED);
        textVersionStringTS = new JTextField();

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
        textVersionStringTS.setPreferredSize(new Dimension(200, 30));
        panelTeamServer.add(textVersionStringTS, gridBagConstraints);

        labelNewVersionString = new JLabel(Constants.NEW_VERSION_STRING);
        textNewVersionStringTS = new JTextField();

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
        textNewVersionStringTS.setPreferredSize(new Dimension(200, 30));
        panelTeamServer.add(textNewVersionStringTS, gridBagConstraints);

        labelGUID = new JLabel(Constants.GUID);
        textGUIDTS = new JTextField();
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
        textGUIDTS.setPreferredSize(new Dimension(200, 30));
        panelTeamServer.add(textGUIDTS, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        panelTeamServer.add(buttonGenerate, gridBagConstraints);

        buttonGroupUpgradeTypeTS = new ButtonGroup();
        radioButtonMajorMinorUpgradeTS = new JRadioButton(Constants.MAJOR_OR_MINOR_UPGRADE);
        radioButtonPatchUpgradeTS = new JRadioButton(Constants.PATCH_UPGRADE);
        buttonGroupUpgradeTypeTS.add(radioButtonMajorMinorUpgradeTS);
        buttonGroupUpgradeTypeTS.add(radioButtonPatchUpgradeTS);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridy = 5;
        panelTeamServer.add(radioButtonMajorMinorUpgradeTS, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        panelTeamServer.add(radioButtonPatchUpgradeTS, gridBagConstraints);

        buttonChangeVersion = new JButton(Constants.CHANGE_VERSION_NUMBER);
        buttonChangeVersion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPressedChangeVersion();
            }
        });

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

        textAreaStatusTS = new JTextArea();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        panelTeamServer.add(textAreaStatusTS, gridBagConstraints);
        textAreaStatusTS.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return panelTeamServer;
    }

    public JPanel initDataArchitectUI() {
        panelDataArchitect = new JPanel();
        panelDataArchitect.setLayout(new GridBagLayout());

        labelDAString = new JLabel(Constants.DA_MAIN_FOLDER);
        textFieldDA = new JTextField();
        textFieldDA.setEditable(false);
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
        panelDataArchitect.add(labelDAString, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        textFieldDA.setPreferredSize(new Dimension(200, 30));
        panelDataArchitect.add(textFieldDA, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        panelDataArchitect.add(buttonBrowse, gridBagConstraints);

        labelVersionString = new JLabel(Constants.STRING_TO_BE_REPLACED);
        textVersionStringDA = new JTextField();

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        panelDataArchitect.add(labelVersionString, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        textVersionStringDA.setPreferredSize(new Dimension(200, 30));
        panelDataArchitect.add(textVersionStringDA, gridBagConstraints);

        labelNewVersionString = new JLabel(Constants.NEW_VERSION_STRING);
        textNewVersionStringDA = new JTextField();

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridy = 3;
        panelDataArchitect.add(labelNewVersionString, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        textNewVersionStringDA.setPreferredSize(new Dimension(200, 30));
        panelDataArchitect.add(textNewVersionStringDA, gridBagConstraints);

        labelGUID = new JLabel(Constants.GUID);
        textGUIDDA = new JTextField();
        buttonGenerate = new JButton(Constants.GENERATE);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridy = 4;
        panelDataArchitect.add(labelGUID, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        textGUIDDA.setPreferredSize(new Dimension(200, 30));
        panelDataArchitect.add(textGUIDDA, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 0;
        panelDataArchitect.add(buttonGenerate, gridBagConstraints);

        buttonGroupUpgradeTypeDA = new ButtonGroup();
        radioButtonMajorMinorDA = new JRadioButton(Constants.MAJOR_OR_MINOR_UPGRADE);
        radioButtonPatchUpgradeDA = new JRadioButton(Constants.PATCH_UPGRADE);
        buttonGroupUpgradeTypeDA.add(radioButtonMajorMinorDA);
        buttonGroupUpgradeTypeDA.add(radioButtonPatchUpgradeDA);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridy = 5;
        panelDataArchitect.add(radioButtonMajorMinorDA, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        panelDataArchitect.add(radioButtonPatchUpgradeDA, gridBagConstraints);

        buttonChangeVersion = new JButton(Constants.CHANGE_VERSION_NUMBER);
        buttonChangeVersion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPressedChangeVersion();
            }
        });

        buttonReset = new JButton(Constants.RESET);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 0;
        panelDataArchitect.add(buttonChangeVersion, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = .5;
        gridBagConstraints.weighty = .2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 100;
        panelDataArchitect.add(buttonReset, gridBagConstraints);

        textAreaStatusDA = new JTextArea();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        panelDataArchitect.add(textAreaStatusDA, gridBagConstraints);
        textAreaStatusDA.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return panelDataArchitect;
    }

    public JPanel initHelpUI() {
        panelHelp = new JPanel();
        return panelHelp;
    }

    public void onPressedChangeVersion() {
        // need to be set from text fields by darpan
        if (validateInput(tabbedPaneUI.getTitleAt(tabbedPaneUI.getSelectedIndex()))) {
            VersionInputModel versionInputModel = new VersionInputModel("C:\\Idera\\ERStudioFiles", "20.2.2.0", "20.2.3.0", "1234", UpgradeTypeEnum.MAJOR_OR_MINOR_UPGRADE);
            ChangeTSVersionNumber changeTSVersionNumber = new ChangeTSVersionNumber(versionInputModel);
            List<String> response = changeTSVersionNumber.changeVersion(versionInputModel);
        }
    }

    public void onPressedBrowse() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // set the selection mode to directories only
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // invoke the showsOpenDialog function to show the save dialog
        int stateFileChooser = fileChooser.showOpenDialog(null);

        if (stateFileChooser == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected directory
            if (tabbedPaneUI.getTitleAt(tabbedPaneUI.getSelectedIndex()).equals(Constants.TEAM_SERVER)) {
                textFieldTS.setText(fileChooser.getSelectedFile().getAbsolutePath());
            } else {
                textFieldDA.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    // method to validate input
    public boolean validateInput(String projectName) {
        boolean validationFlag = true;
        if (projectName.equals(Constants.TEAM_SERVER)) {
            if (!InputUtils.isValidDirectory(projectName, textFieldTS.getText())) {
                textFieldTS.setBackground(new Color(255, 153, 153));
                textFieldTS.setToolTipText(Constants.ERS_STUDIO_DIRECTORY_VALIDATION_ERROR);
                validationFlag = false;
            } else {
                textFieldTS.setBackground(tabbedPaneUI.getSelectedComponent().getBackground());
                textFieldTS.setToolTipText(null);
            }
            if (!InputUtils.isValidVersionNumber(projectName, textVersionStringTS.getText())) {
                textVersionStringTS.setBackground(new Color(255, 153, 153));
                textVersionStringTS.setToolTipText(Constants.ERS_STUDIO_CHANGE_VERSION_VALIDATION_ERROR);
                validationFlag = false;
            } else {
                textVersionStringTS.setBackground(Color.WHITE);
                textVersionStringTS.setToolTipText(null);
            }
            if (!InputUtils.isValidVersionNumber(projectName, textNewVersionStringTS.getText())) {
                textNewVersionStringTS.setBackground(new Color(255, 153, 153));
                textNewVersionStringTS.setToolTipText(Constants.ERS_STUDIO_NEW_VERSION_VALIDATION_ERROR);
                validationFlag = false;
            } else {
                textNewVersionStringTS.setBackground(Color.WHITE);
                textNewVersionStringTS.setToolTipText(null);
            }
        }

        if (projectName.equals(Constants.DATA_ARCHITECT)) {
            if (!InputUtils.isValidDirectory(projectName, textFieldDA.getText())) {
                textFieldDA.setBackground(new Color(255, 153, 153));
                textFieldDA.setToolTipText(Constants.DA_DIRECTORY_VALIDATION_ERROR);
                validationFlag = false;
            } else {
                textFieldDA.setBackground(tabbedPaneUI.getSelectedComponent().getBackground());
                textFieldDA.setToolTipText(null);
            }
            if (!InputUtils.isValidVersionNumber(projectName, textVersionStringDA.getText())) {
                textVersionStringDA.setBackground(new Color(255, 153, 153));
                textVersionStringDA.setToolTipText(Constants.DA_CHANGE_VERSION_VALIDATION_ERROR);
                validationFlag = false;
            } else {
                textVersionStringDA.setBackground(Color.WHITE);
                textVersionStringDA.setToolTipText(null);
            }
            if (!InputUtils.isValidVersionNumber(projectName, textNewVersionStringDA.getText())) {
                textNewVersionStringDA.setBackground(new Color(255, 153, 153));
                textNewVersionStringDA.setToolTipText(Constants.DA_NEW_VERSION_VALIDATION_ERROR);
                validationFlag = false;
            } else {
                textNewVersionStringDA.setBackground(Color.WHITE);
                textNewVersionStringDA.setToolTipText(null);
            }
        }

        return validationFlag;
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
