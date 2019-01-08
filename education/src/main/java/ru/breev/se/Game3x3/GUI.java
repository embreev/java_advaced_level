package ru.breev.se.Game3x3;

import javax.swing.*;
import java.awt.*;

public class GUI {
    static final String[] heroes = {"Warrior", "Assasin", "Doctor"};
    public static void createGUI()
    {
        JFrame window = new JFrame("Game 3x3");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(600, 600));
        window.setResizable(false);
        window.setLayout(new GridLayout(4,1));

        JPanel heroPanel = new JPanel();
        JComboBox<String> heroTeam1 = new JComboBox(heroes);
        JButton buttonAddHeroTeam1 = new JButton("Add Hero Team1");
        JComboBox<String> heroTeam2 = new JComboBox(heroes);
        JButton buttonAddHeroTeam2 = new JButton("Add Hero Team2");
        heroPanel.add(heroTeam1);
        heroPanel.add(buttonAddHeroTeam1);
        heroPanel.add(heroTeam2);
        heroPanel.add(buttonAddHeroTeam2);

        JPanel teamPanel = new JPanel();
        JTextArea team1Area = new JTextArea(10, 20);
        team1Area.setEditable(false);
        JTextArea team2Area = new JTextArea(10, 20);
        team2Area.setEditable(false);
        teamPanel.add(team1Area);
        teamPanel.add(team2Area);

        JPanel logPanel = new JPanel();
        JTextArea logArea = new JTextArea(30, 40);
        logArea.setEditable(false);
        logPanel.add(logArea);

        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Start");
        buttonPanel.add(button);

        window.add(heroPanel);
        window.add(teamPanel);
        window.add(logPanel);
        window.add(buttonPanel);

        window.pack();
        window.setVisible(true);
    }
}
