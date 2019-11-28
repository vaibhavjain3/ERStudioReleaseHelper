package main.java.com.erstudio;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ERSUI {

    JLabel labelTSString, labelVersionString, labelNewVersionString, labelGUID;
    JTextField textFieldTs, textVersionString, textNewVersionString, textGUID;
    JButton buttonTS, buttonGenerate, jButton, jButton1;
    ButtonGroup buttonGroup;
    JRadioButton jRadioButton, jRadioButton1;
    JTextArea textArea;
    JFrame frame;
    JTabbedPane tp;
    JPanel panel;

    public JPanel initTeamServerUI() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        labelTSString = new JLabel("Please choose TS main folder");
        textFieldTs = new JTextField();
        textFieldTs.setEditable(false);
        buttonTS = new JButton("Browse");

        buttonTS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPressedTs();
            }
        });
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(labelTSString, c);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 200;
        c.ipady = 10;
        panel.add(textFieldTs, c);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 2;
        c.gridy = 1;
        c.ipadx = 50;
        c.ipady = 0;
        panel.add(buttonTS, c);

        labelVersionString = new JLabel("Version String to be Replaced");
        textVersionString = new JTextField();

        c.gridwidth = 1;
        c.ipadx = 0;
        c.weightx = .5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(labelVersionString, c);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 200;
        c.ipady = 10;
        panel.add(textVersionString, c);

        labelNewVersionString = new JLabel("New Version String");
        textNewVersionString = new JTextField();

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 0;
        c.ipadx = 0;
        c.gridy = 3;
        panel.add(labelNewVersionString, c);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 1;
        c.gridy = 3;
        c.ipadx = 200;
        c.ipady = 10;
        panel.add(textNewVersionString, c);

        labelGUID = new JLabel("GUID");
        textGUID = new JTextField();
        buttonGenerate = new JButton("Generate");

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 0;
        c.ipadx = 0;
        c.gridy = 4;
        panel.add(labelGUID, c);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 1;
        c.gridy = 4;
        c.ipadx = 200;
        c.ipady = 10;
        panel.add(textGUID, c);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 2;
        c.gridy = 4;
        c.ipadx = 50;
        c.ipady = 0;
        panel.add(buttonGenerate, c);

        buttonGroup = new ButtonGroup();
        jRadioButton = new JRadioButton("Full Upgrade");
        jRadioButton1 = new JRadioButton("Patch Upgrade");
        buttonGroup.add(jRadioButton);
        buttonGroup.add(jRadioButton1);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 0;
        c.ipadx = 0;
        c.gridy = 5;
        panel.add(jRadioButton, c);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 1;
        c.gridy = 5;
        panel.add(jRadioButton1, c);

        jButton = new JButton("Change Version Number");
        jButton1 = new JButton("Reset");

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 0;
        c.gridy = 6;
        c.ipadx = 0;
        panel.add(jButton, c);

        c.gridwidth = 1;
        c.weightx = .5;
        c.weighty = .2;
        c.gridx = 1;
        c.gridy = 6;
        c.ipadx = 100;
        panel.add(jButton1, c);

        textArea = new JTextArea();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 7;
        panel.add(textArea, c);

        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        return panel;
    }

    public JPanel initDAUI() {
        JPanel panel = new JPanel();

        return panel;
    }

    public JPanel inithelpUI() {
        JPanel panel = new JPanel();

        return panel;
    }

    public void onPressedTs() {
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


    ERSUI() {
        frame = new JFrame("ERS Studio Release Helper");

        tp = new JTabbedPane();
        tp.setBounds(50, 50, 600, 570);

        JPanel panelTeamServer = initTeamServerUI();
        JPanel panelDAUI = initDAUI();
        JPanel panelHelp = inithelpUI();

        tp.add("TeamServer", panelTeamServer);
        tp.add("DA", panelDAUI);
        tp.add("help", panelHelp);

        frame.add(tp);
        frame.setSize(600, 570);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
