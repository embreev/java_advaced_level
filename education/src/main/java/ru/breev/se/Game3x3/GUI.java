package ru.breev.se.Game3x3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.breev.se.Game3x3.Game.*;

public class GUI {
    static final String[] heroes = {"Warrior", "Assasin", "Doctor"};

    public static void createGUI() {
        JFrame window = new JFrame("Game 3x3");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(500, 500));
        window.setResizable(false);
        window.setLayout(new BorderLayout());

        JPanel heroPanel = new JPanel();
        final JComboBox<String> heroTeam1 = new JComboBox(heroes);
        JButton buttonAddHeroTeam1 = new JButton("Add Hero Team1");
        final JComboBox<String> heroTeam2 = new JComboBox(heroes);
        JButton buttonAddHeroTeam2 = new JButton("Add Hero Team2");
        heroPanel.add(heroTeam1);
        heroPanel.add(buttonAddHeroTeam1);
        heroPanel.add(heroTeam2);
        heroPanel.add(buttonAddHeroTeam2);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        JPanel teamPanel = new JPanel();
        final JTextArea team1Area = new JTextArea(10, 20);
        team1Area.setEditable(false);
        final JTextArea team2Area = new JTextArea(10, 20);
        team2Area.setEditable(false);
        teamPanel.add(team1Area);
        teamPanel.add(team2Area);

        JPanel logPanel = new JPanel();
        final JTextArea logArea = new JTextArea(10, 40);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        logPanel.add(scrollPane);

        mainPanel.add(teamPanel);
        mainPanel.add(logPanel);

        JPanel buttonPanel = new JPanel();
        JButton buttonStart = new JButton("Start");
        buttonPanel.add(buttonStart);

        window.add(heroPanel, BorderLayout.NORTH);
        window.add(mainPanel, BorderLayout.CENTER);
        window.add(buttonPanel, BorderLayout.SOUTH);

        ActionListener alHeroTeam1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) heroTeam1.getSelectedItem();
                if (getQuantityHeros(team1) < quantityHeros) {
                    team1Area.append(item + "\n");
                    addHeroTeam1(item);
                }
            }
        };
        buttonAddHeroTeam1.addActionListener(alHeroTeam1);

        ActionListener alHeroTeam2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) heroTeam2.getSelectedItem();
                if (getQuantityHeros(team2) < quantityHeros) {
                    team2Area.append(item + "\n");
                    addHeroTeam2(item);
                }
            }
        };
        buttonAddHeroTeam2.addActionListener(alHeroTeam2);

        ActionListener startGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.setText("");
                int i = 0;
                while (i < 10) {
                    go();
                    logArea.append(status + "\n");
                    i++;
//                    checkWinner();
                }

                logArea.append("=====================\n" +
                        "Statistics game:\n");

                for (Hero t1 : team1) {
                    logArea.append(t1.info() + "\n");
                }
                for (Hero t2 : team2) {
                    logArea.append(t2.info() + "\n");
                }
            }
        };

            buttonStart.addActionListener(startGame);

            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        }
    }
