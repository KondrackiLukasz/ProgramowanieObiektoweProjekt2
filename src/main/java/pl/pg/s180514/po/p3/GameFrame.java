package pl.pg.s180514.po.p3;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private World world = new World();
    private JTextPane jText = new JTextPane();
    private GamePanel panel = new GamePanel(world);
    private Commentator commentator = new Commentator(jText);

    public GameFrame() {
        super("PO Projekt 2 180514");

        Menu menu = new Menu("Opcje");
        MenuItem save = new MenuItem("Zapisz");
        MenuItem load = new MenuItem("Wczytaj");
        menu.add(save);
        menu.add(load);

        MenuBar menuBar = new MenuBar();
        menuBar.add(menu);
        this.setMenuBar(menuBar);

        world.setCommentator(commentator);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        setLayout(new GridLayout(1, 2));
        add(panel);

        JPanel uiPanel = new JPanel();
        uiPanel.setLayout(new GridLayout(2, 1));
        add(uiPanel);

        JButton nextTurn = new JButton("nowa tura!");
        nextTurn.addActionListener(e -> {
            world.makeTurn();
            commentator.printComments();
            panel.repaint();
            panel.requestFocus();
        });
        uiPanel.add(nextTurn);

        jText.setEnabled(false);
        uiPanel.add(jText);

        save.addActionListener(e -> world.save());

        load.addActionListener(e -> {
            world.load();
            remove(uiPanel);
            remove(panel);

            panel = new GamePanel(world);

            add(panel);
            add(uiPanel);

            panel.invalidate();
            panel.updateUI();
            panel.repaint();
            repaint();
            commentator.setPane(jText);
            world.setCommentator(commentator);
        });

        pack();
        setVisible(true);
    }
}
