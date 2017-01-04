/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author mdashnyam
 */
class Cell extends JPanel {

        private Color defaultBackground;
        private boolean isAlive;
        private int xPos;
        private int yPos;
        
        public Cell(int xpos, int ypos, boolean isalive) {
            xPos = xpos;
            yPos = ypos;
            isAlive = isalive;
            if(isAlive)
            {
                defaultBackground = getBackground();
                setBackground(Color.BLACK);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(15, 15);
        }
        
        public Cell(int xpos, int ypos) {
            xPos = xpos;
            yPos = ypos;
        }
        
        @Override
        public boolean equals(Object object)
        {
            boolean sameSame = false;

            if (object != null && object instanceof Cell)
            {
                sameSame = this.xPos == ((Cell) object).xPos && this.yPos == ((Cell) object).yPos;
            }

            return sameSame;
        }
        public ArrayList<Cell> getNeighbours()
        {
            ArrayList<Cell> neighbours=new ArrayList<>();
            int xpos=this.xPos;
            int ypos=this.yPos;
            for (int i=xpos-1; i<=xpos+1; i++)
                for(int j=ypos-1; j<=ypos+1; j++)
                {
                    if (j!=ypos || i!=xpos) {
                        neighbours.add(new Cell(i,j));
                    }
                }
            return neighbours;
        }
}
