/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author mdashnyam
 */
public class GoL extends JPanel {

    static JFrame frame = new JFrame("Game of Life");
    static JPanel grid = new JPanel();
    static JComboBox cmbSeedPattern = new JComboBox();
    static ArrayList<Cell> currentGeneration = new ArrayList<>();
    static ArrayList<Cell> nextGeneration = new ArrayList<>();
    static int refreshTime = 500; 
    static Timer timer;

    public GoL() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        boolean isAlive;
        for (int row = 0; row < 50; row++) {
            for (int col = 0; col < 50; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                isAlive = currentGeneration.contains(new Cell(col, row));
                Cell cell = new Cell(col, row, isAlive);
                Border border = null;
                if (row < 4) {
                    if (col < 4) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < 4) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cell.setBorder(border);
                add(cell, gbc);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initialize();
    }

    static void initialize() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 850);
        frame.setLayout(new FlowLayout());

        cmbSeedPattern.addItem("Small Explorer");
        cmbSeedPattern.addItem("Glider");
        cmbSeedPattern.addItem("10 cell row");
        cmbSeedPattern.addItem("Tumbler");
        cmbSeedPattern.addItemListener(new itemChangeListener());

        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new btnStartActionListener());
        JButton btnStop = new JButton("Stop");
        btnStop.addActionListener(new btnStopActionListener());

        frame.add(cmbSeedPattern);
        frame.add(btnStart);
        frame.add(btnStop);
        startSeed();
        grid.add(new GoL());
        frame.add(grid);
        frame.setVisible(true);
    }

    static void startSeed() {
        currentGeneration.clear();
        switch (cmbSeedPattern.getSelectedItem().toString()) {
            case "Glider":
                Cell cell1 = new Cell(25, 25);
                Cell cell2 = new Cell(26, 26);
                Cell cell3 = new Cell(26, 27);
                Cell cell4 = new Cell(25, 27);
                Cell cell5 = new Cell(24, 27);
                currentGeneration.add(cell1);
                currentGeneration.add(cell2);
                currentGeneration.add(cell3);
                currentGeneration.add(cell4);
                currentGeneration.add(cell5);
                break;
            case "Small Explorer":
                cell1 = new Cell(25, 25);
                cell2 = new Cell(24, 24);
                cell3 = new Cell(25, 24);
                cell4 = new Cell(26, 24);
                cell5 = new Cell(24, 23);
                Cell cell6 = new Cell(26, 23);
                Cell cell7 = new Cell(25, 22);
                currentGeneration.add(cell1);
                currentGeneration.add(cell2);
                currentGeneration.add(cell3);
                currentGeneration.add(cell4);
                currentGeneration.add(cell5);
                currentGeneration.add(cell6);
                currentGeneration.add(cell7);
                break;
            case "10 cell row":
                cell1 = new Cell(20, 25);
                cell2 = new Cell(21, 25);
                cell3 = new Cell(22, 25);
                cell4 = new Cell(23, 25);
                cell5 = new Cell(24, 25);
                cell6 = new Cell(25, 25);
                cell7 = new Cell(26, 25);
                Cell cell8 = new Cell(27, 25);
                Cell cell9 = new Cell(28, 25);
                Cell cell10 = new Cell(29, 25);
                currentGeneration.add(cell1);
                currentGeneration.add(cell2);
                currentGeneration.add(cell3);
                currentGeneration.add(cell4);
                currentGeneration.add(cell5);
                currentGeneration.add(cell6);
                currentGeneration.add(cell7);
                currentGeneration.add(cell8);
                currentGeneration.add(cell9);
                currentGeneration.add(cell10);
                break;
            case "Tumbler":
                cell1 = new Cell(21, 19);
                cell2 = new Cell(21, 18);
                cell3 = new Cell(21, 17);
                cell4 = new Cell(22, 17);
                cell5 = new Cell(22, 21);
                cell6 = new Cell(22, 22);
                cell7 = new Cell(23, 18);
                cell8 = new Cell(23, 19);
                cell9 = new Cell(23, 20);
                cell10 = new Cell(23, 21);
                Cell cell11 = new Cell(23, 22);

                Cell cell01 = new Cell(27, 19);
                Cell cell02 = new Cell(27, 18);
                Cell cell03 = new Cell(27, 17);
                Cell cell04 = new Cell(26, 17);
                Cell cell05 = new Cell(26, 21);
                Cell cell06 = new Cell(26, 22);
                Cell cell07 = new Cell(25, 18);
                Cell cell08 = new Cell(25, 19);
                Cell cell09 = new Cell(25, 20);
                Cell cell010 = new Cell(25, 21);
                Cell cell011 = new Cell(25, 22);
                currentGeneration.add(cell1);
                currentGeneration.add(cell2);
                currentGeneration.add(cell3);
                currentGeneration.add(cell4);
                currentGeneration.add(cell5);
                currentGeneration.add(cell6);
                currentGeneration.add(cell7);
                currentGeneration.add(cell8);
                currentGeneration.add(cell9);
                currentGeneration.add(cell10);
                currentGeneration.add(cell11);
                currentGeneration.add(cell01);
                currentGeneration.add(cell02);
                currentGeneration.add(cell03);
                currentGeneration.add(cell04);
                currentGeneration.add(cell05);
                currentGeneration.add(cell06);
                currentGeneration.add(cell07);
                currentGeneration.add(cell08);
                currentGeneration.add(cell09);
                currentGeneration.add(cell010);
                currentGeneration.add(cell011);
                break;
        }
    }

    static void determineNextGeneration() {
        for (Cell cell : currentGeneration) {
            int aliveNeighbour = 0;
            for (Cell neighbour : cell.getNeighbours()) {
                boolean isNeighbourAlive;
                if (currentGeneration.contains(neighbour)) {
                    aliveNeighbour += 1;
                    isNeighbourAlive = true; // state of cell's neighbour in current iteration
                } else {
                    isNeighbourAlive = false; // state of cell's neighbour in current iteration
                }
                int aliveNeighbour_neighbour = 0;
                for (Cell neighbour_neighbour : neighbour.getNeighbours()) {
                    if (currentGeneration.contains(neighbour_neighbour)) {
                        aliveNeighbour_neighbour += 1;
                    }
                }
                if (isNeighbourAlive && (aliveNeighbour_neighbour == 2 || aliveNeighbour_neighbour == 3) && !nextGeneration.contains(neighbour)) {
                    nextGeneration.add(neighbour);
                } else if (isNeighbourAlive == false && aliveNeighbour_neighbour == 3 && !nextGeneration.contains(neighbour)) {
                    nextGeneration.add(neighbour);
                }
            }
            if ((aliveNeighbour == 2 || aliveNeighbour == 3) && !nextGeneration.contains(cell)) {
                nextGeneration.add(cell);
            }
        }
        refresh();
    }
    
    static void refresh() {
        currentGeneration.clear();
        currentGeneration.addAll(nextGeneration);
        nextGeneration.clear();
        grid.removeAll();
        grid.add(new GoL());
        frame.setVisible(true);
    }

    static void start() {
        timer = new Timer(refreshTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                determineNextGeneration();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    static void stop() {
        timer.stop();
    }

    static class btnStartActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            start();
        }
    }

    static class btnStopActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            stop();
        }
    }

    static class itemChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                startSeed();
                grid.removeAll();
                grid.add(new GoL());
                frame.setVisible(true);
            }
        }
    }
}
