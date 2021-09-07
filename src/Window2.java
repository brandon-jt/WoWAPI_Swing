import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Window2 extends JPanel {

    // Holds all the components created in the Swing Builder to display the retrieved information.
    private JLabel picLabel0;
    private JLabel picLabel1;
    private JLabel picLabel2;
    private JLabel picLabel3;
    private JLabel picLabel4;
    private JLabel picLabel5;
    private JLabel picLabel6;
    private JButton backButton;
    final int TALENTNUMBER = 7;
    private JPanel card2;
    private JPanel LevelPanel;
    private JPanel TextPanel;
    private JPanel ImagePanel;

    private JLabel TalentLabel0;
    private JLabel TalentLabel1;
    private JLabel TalentLabel2;
    private JLabel TalentLabel3;
    private JLabel TalentLabel4;
    private JLabel TalentLabel5;
    private JLabel TalentLabel6;

    /*
        Called after the get button is clicked in Window 1. Reads all the information retrieved from the database
        to set the talent names, talent descriptions, and talent pictures in the second window.
    */
    void addComponenttoPane2() throws IOException {

        List<URL> urlList = new ArrayList<>();
        List<Image> imageList = new ArrayList<>();
        List<JLabel> picLabelList = new ArrayList<>();
        List<JLabel> TalentLabelList = new ArrayList<>();
        Collections.addAll(picLabelList, picLabel0, picLabel1, picLabel2, picLabel3, picLabel4, picLabel5, picLabel6);
        Collections.addAll(TalentLabelList, TalentLabel0, TalentLabel1, TalentLabel2, TalentLabel3, TalentLabel4, TalentLabel5, TalentLabel6);
        for (int i = 0; i < TALENTNUMBER; i++){
            urlList.add(new URL(Program.MediaList.get(i).replace("\"", "")));
            imageList.add(ImageIO.read(urlList.get(i)));
            picLabelList.get(i).setIcon(new ImageIcon(imageList.get(i)));
            picLabelList.get(i).setToolTipText(Program.DescriptionList.get(i));
            TalentLabelList.get(i).setText(Program.TalentList.get(i));
        }
        // Switches to window2 after all the components are modified.
        Window1.cl.last(Window1.cards);
    }

    /*
        Helper function called from Window1 that adds Window2 as Card2 and adds the back button listener
     */
    static void createWindow2(Window2 window2, Window1 window) throws IOException {

        Window1.cards.add(window2.card2, "Card2");
        window2.backButton.addActionListener(window2.backListener(window));
    }

    // Goes back to Window 1, clears the request to select a specialization if it has been triggered.
    private ActionListener backListener(Window1 window1){
        return e -> {
            Window1.cl.previous(Window1.cards);
            window1.SpecText.setText("");
        };
    }

}

