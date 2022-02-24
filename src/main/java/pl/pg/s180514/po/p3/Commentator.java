package pl.pg.s180514.po.p3;

import pl.pg.s180514.po.p3.organisms.Organism;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.io.Serializable;
import java.util.Vector;

public class Commentator implements Serializable {
    public Vector<String> comments = new Vector<>();
    JTextPane pane;

    public Commentator(JTextPane paneParam) {
        pane = paneParam;
    }

    public void setPane(JTextPane pane) {
        this.pane = pane;
    }

    public void addDeathComment(Organism org) {
        comments.add(org.getName() + " umiera.");
    }

    public void addBirthComment(Organism org) {
        comments.add(org.getName() + " rodzi sie. ");
    }

    public void addPowerComment() {
        comments.add("Człowiek używa całopalenia");
    }

    public void addPowerReadyComment() {
        comments.add("Całopalenie jest gotowe do użycia.");
    }

    public void printComments() {
        pane.setText("");

        Document doc = pane.getStyledDocument();
        try {
            for (String s : comments)
                doc.insertString(doc.getLength(), s + "\n", new SimpleAttributeSet());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        comments.clear();
    }
}
