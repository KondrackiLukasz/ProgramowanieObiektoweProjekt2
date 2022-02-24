package pl.pg.s180514.po.p3;

import pl.pg.s180514.po.p3.organisms.Organism;
import pl.pg.s180514.po.p3.organisms.animals.*;
import pl.pg.s180514.po.p3.organisms.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, MouseListener {
    private World world;
    private String[] organismsToPick = {
            "Antylopa",
            "Cyber owca",
            "Lis",
            "Owca",
            "Żółw",
            "Wilk",
            "Mlecz",
            "Trawa",
            "Guarana",
            "Barszcz Sosnowkiego",
            "Wilcza Jagoda"
    };

    public GamePanel(World world) {
        this.world = world;
        setPreferredSize(new Dimension(Position.maxX * 20, Position.maxY * 20));
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int portionH = this.getHeight() / Position.maxX;
        int portionW = this.getWidth() / Position.maxY;
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        for (Organism org : world.getAllOrganisms()) {
            if (org instanceof Plant) {
                g2d.setColor(new Color(125, 167, 116));
            } else if (org instanceof Human) {
                g2d.setColor(new Color(92, 151, 253));
            } else {
                g2d.setColor(new Color(249, 152, 255));
            }
            g2d.fillRect(org.getPosition().getX() * portionW, portionH * org.getPosition().getY(), portionW, portionH);
            g2d.setColor(new Color(0, 0, 0));
            g2d.drawString(org.getIcon(), org.getPosition().getX() * portionW + portionW / 4, portionH / 2 + portionH * org.getPosition().getY() + portionH / 4);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        var human = world.getHuman();
        if (human != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> human.setCommand('u');
                case KeyEvent.VK_RIGHT -> human.setCommand('r');
                case KeyEvent.VK_LEFT -> human.setCommand('l');
                case KeyEvent.VK_DOWN -> human.setCommand('d');
                case KeyEvent.VK_R -> human.setCommand('s');
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int portionH = this.getHeight() / Position.maxX;
        int portionW = this.getWidth() / Position.maxY;

        int x = e.getX();
        int y = e.getY();

        int xPos = x / portionW;
        int yPos = y / portionH;

        if (xPos >= 0 && yPos >= 0 && xPos < Position.maxX & yPos < Position.maxY) {
            if (world.getOrganism(new Position(xPos, yPos)) == null) {
                String selectedName = (String) JOptionPane.showInputDialog(null, "Organizmy:", "Wybierz organizm",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        organismsToPick,
                        organismsToPick[0]);

                try {
                    Organism newOrg = null;
                    switch (selectedName) {
                        case "Antylopa" -> newOrg = new Antelope(new Position(xPos, yPos), world);
                        case "Cyber owca" -> newOrg = new CyberSheep(new Position(xPos, yPos), world);
                        case "Lis" -> newOrg = new Fox(new Position(xPos, yPos), world);
                        case "Owca" -> newOrg = new Sheep(new Position(xPos, yPos), world);
                        case "Żółw" -> newOrg = new Turtle(new Position(xPos, yPos), world);
                        case "Wilk" -> newOrg = new Wolf(new Position(xPos, yPos), world);
                        case "Mlecz" -> newOrg = new Dandelion(new Position(xPos, yPos), world);
                        case "Trawa" -> newOrg = new Grass(new Position(xPos, yPos), world);
                        case "Guarana" -> newOrg = new Guarana(new Position(xPos, yPos), world);
                        case "Barszcz Sosnowkiego" -> newOrg = new SosnowskiBorscht(new Position(xPos, yPos), world);
                        case "Wilcza Jagoda" -> newOrg = new WolfBerries(new Position(xPos, yPos), world);
                    }

                    if (newOrg != null)
                        world.addOrganism(newOrg, newOrg.getPosition());
                    repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
