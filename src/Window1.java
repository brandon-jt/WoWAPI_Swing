import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

public class Window1 extends JPanel implements ItemListener {
    static JFrame frame;
    static CardLayout cl;
    static JPanel cards;
    private Window2 window2;
    private JList<String> list1;
    private JList<String> list2;
    private JButton getButton;
    private JPanel card1;
    JLabel SpecText;

    DefaultListModel<String> model = new DefaultListModel<>();
    DefaultListModel<String> model2 = new DefaultListModel<>();
    // List that will hold the class names on the left hand side
    private static final String[] classNames = {"Death Knight", "Demon Hunter", "Druid", "Hunter",
            "Mage", "Monk", "Paladin", "Priest", "Rogue", "Shaman", "Warlock", "Warrior"};

    // Holds the class and spec selected from the two lists
    private String selectedClass;
    private String selectedSpec;

    private String[] SpecList;
    boolean isWindow2Created = false;

    /* Initializes the first window which will show the classes and specializations and wait for user input.
        the getListener() attached to the getButton will pass the selectedClass and selectedSpec to Program
     */
    public static void createAndShowGUI(JFrame frame) throws IOException {
        Window1 window = new Window1();

        window.list1.setModel(window.model);
        for (String className : classNames) {
            window.model.addElement(className);
        }

        window.list1.addListSelectionListener(getSpecsListener(window));
        window.list2.addListSelectionListener(getSpecListener(window));
        window.list2.setModel(window.model2);

        window.getButton.addActionListener(getListener(window));
        window.addComponentToPane(frame.getContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();



    }
    // Using cardLayout to switch between the two Windows to ensure new windows aren't constantly created
    // when using the back button to run the code multiple times.
    public void addComponentToPane(Container pane) {
        cards = new JPanel(new CardLayout());
        cards.add(card1, "Card1");
        pane.add(cards);
        cl = (CardLayout) (cards.getLayout());
    }

    // Below sets the theme for the Swing application and calls the createAndShowGUI to build the first Window
    public static void main(String[] args) {
        frame = new JFrame("WoWAPI");
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI(frame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        cl = (CardLayout) (cards.getLayout());
    }

    /*
        Called after a class is selected in the first list. Ensures the list is empty before adding new specs to the list.
        Calls the helper function SpecHolder.getSpecs() with the selected class to return the corresponding
        specializations and adds them to List2.
     */
    private static ListSelectionListener getSpecsListener(Window1 window) {

        return e -> {
            window.model2.removeAllElements();
            window.selectedSpec = "";
            if (!e.getValueIsAdjusting()) {
                @SuppressWarnings("unchecked")
                JList<String> listChange = (JList<String>) e.getSource();   // Must have this to get the correct state!
                Object selectedClassO = listChange.getSelectedValue();
                window.selectedClass = selectedClassO.toString();

                window.SpecList = SpecHolder.getSpecs(window.selectedClass);
                for (int i = 0; i < window.SpecList.length; i++) {
                    ((DefaultListModel<String>) window.list2.getModel()).add(i, window.SpecList[i]);
                }
            }
        };
    }
    // Waits for the specialization from list2 to be selected and assigns it to selectedSpec
    private static ListSelectionListener getSpecListener(Window1 window) {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                @SuppressWarnings("unchecked")
                JList<String> listChange = (JList<String>) e.getSource();
                if (listChange.getSelectedValue() != null) {
                    window.selectedSpec = listChange.getSelectedValue();

                }
            }
        };
    }
    /*
        Called with the get button, makes sure a specialization (which naturally makes sure a Class is selected too)
        is selected then executed the code required to retrieve all the information. Creates window2 if not created
        already and calls the function to begin building/altering it.
     */
    private static ActionListener getListener(Window1 window) {

        return e -> {
            if (window.list2.isSelectionEmpty()) {
                window.SpecText.setText("Please select a specialization");
                frame.pack();
                return;
            }
            try {

                Program.beginCodeExecution(window.selectedClass, window.selectedSpec);
                if (!window.isWindow2Created) {
                    window.window2 = new Window2();
                    Window2.createWindow2(window.window2, window);
                    window.isWindow2Created = true;
                }
                window.window2.addComponenttoPane2();

            } catch (IOException | InterruptedException | SQLException e1) {
                // TODO Auto-generated catch block
                System.out.println("Thread was interrupted");
                e1.printStackTrace();
            }
        };
    }
}
