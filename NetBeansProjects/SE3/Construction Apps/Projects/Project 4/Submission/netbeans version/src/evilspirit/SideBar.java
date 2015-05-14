package evilspirit;

import sun.misc.FloatingDecimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Each wall has a side bar object, this is the graphical representation of its values.
 * Swing components are used to read and write to these values
 * Action listeners for these components are also defined within.
 */
class SideBar extends JPanel {

    private final Quote quote;
    private final Wall wall;
    private final String[] difficulties = {"Normal", "Sandy", "Limestone", "Bluestone"};
    private final JLabel lengthLabel = new JLabel("Length (Meters)");
    private final JTextField lengthField = new JTextField();
    private final JLabel startHLabel = new JLabel("Start Height (Meters)");
    private final JTextField startHField = new JTextField();
    private final JLabel endHLabel = new JLabel("End Height (Meters)");
    private final JTextField endHField = new JTextField();
    private final JLabel angleLabel = new JLabel("Angle (Degrees)");
    private final JTextField angleField = new JTextField();
    private final JLabel localLabel = new JLabel("Locally Built");
    private final JCheckBox localBox = new JCheckBox();
    private final JLabel accessLabel = new JLabel("Accessible");
    private final JCheckBox accessBox = new JCheckBox();
    private final JLabel difficultyLabel = new JLabel("Difficulty");
    private final JComboBox difficultySelector = new JComboBox(difficulties);
    private final JButton applyChangesButton = new JButton("Apply Changes");
    private final JButton addWallButton = new JButton("Add Wall");
    private final JButton removeWallButton = new JButton("Remove Wall");
    private final Dimension TextFieldSize = new Dimension(100, 24);


    private final ActionListener buttonPress = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonName = ((JButton) e.getSource()).getText();        //get text of button that was pressed
            //screw you java for not letting me switch with a string
            if (buttonName.equals("Add Wall")) {
                quote.addWall(getValue(lengthField), getValue(startHField), getValue(endHField), getAngle(angleField));
            } else if (buttonName.equals("Remove Wall")) {
                quote.removeWall(wall);
            } else if (buttonName.equals("Apply Changes")) {
                wall.setLength(getValue(lengthField));
                wall.setStartHeight(getValue(startHField));
                wall.setEndHeight(getValue(endHField));
                wall.setAngle(getValue(angleField));
                wall.update();
                quote.update();

            } else {
                System.out.println("Error: Button: " + buttonName + " has not been assigned an action");
            }
        }
    };


    private final ActionListener difficultyListener = new ActionListener() {  //listener for difficulty selector combobox
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox) e.getSource();
            String dString = (String) box.getSelectedItem();
            if (dString.equals("Normal")) {
                quote.setDifficulty(Difficulty.NORMAL);
            } else if (dString.equals("Sandy")) {
                quote.setDifficulty(Difficulty.SANDY);
            } else if (dString.equals("Limestone")) {
                quote.setDifficulty(Difficulty.LIMESTONE);
            } else if (dString.equals("Bluestone")) {
                quote.setDifficulty(Difficulty.BLUESTONE);
            } else {
                System.out.println("Error: Difficulty selected is invalid");
            }
        }
    };


    private final ActionListener checkBoxListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox box = (JCheckBox) e.getSource();
            if (box == accessBox) {
                quote.setAccessible(box.isSelected());
            } else if (box == localBox) {
                quote.setLocal(box.isSelected());
            } else {
                System.out.println("Error: Checkbox Listener - Unknown box selected");
            }
        }
    };

    private final GridBagConstraints gbc;

    public SideBar(Wall wall, MainGui gui) {
        this.wall = wall;
        SideTabs _sideTabs = gui.getTabs();
        _sideTabs.addTab("Wall ", null, this);   //add this panel as a tab, screw giving them proper names
        quote = gui.getQuote();       //retrieve the quote from the gui
        this.setPreferredSize(_sideTabs.getPreferredSize()); //set to size of parent (SideTabs)
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        initParameters();
        initGrid();
        initListeners();
        initValues();
    }

    double getValue(JTextField input) { //parse a double from a jtextfield
        try {
            double value = FloatingDecimal.parseDouble(input.getText());
            return (value > 0) ? value : -value;        //flip negatives to prevent shenanigans
        } catch (NumberFormatException e) {
            System.out.println("Error: SideBar: Attempted to parse invalid value from: " + input.getName());
        }
        return 0;
    }

    double getAngle(JTextField input) {
        //todo - angle works differently to a normal value, needs to force a wall not to double back on itself

        try {
            return FloatingDecimal.parseDouble(input.getText());
        } catch (NumberFormatException e) {
            System.out.println("Error: SideBar: Attempted to parse invalid angle");
        }
        return 0;
    }

    public void update() { //write wall values to gui elements
        //cant implicitly convert double to string, the right way is with numberformat, this is the wrong way
        System.out.println("Setting gui wall length to: " + wall.getLengthMeters());
        lengthField.setText(Integer.toString((int) wall.getLengthMeters()));
        startHField.setText(Integer.toString((int) wall.getStartHeight()));
        endHField.setText(Integer.toString((int) wall.getEndHeight()));
        angleField.setText(Integer.toString((int) wall.getAngle()));
        localBox.setSelected(quote.getLocal());
        accessBox.setSelected(quote.getAccessible());

    }

    private void initParameters() {
        lengthField.setPreferredSize(TextFieldSize);
        startHField.setPreferredSize(TextFieldSize);
        endHField.setPreferredSize(TextFieldSize);
        angleField.setPreferredSize(TextFieldSize);
        lengthField.setInputVerifier(new FieldDeBastarder());
        startHField.setInputVerifier(new FieldDeBastarder());
        endHField.setInputVerifier(new FieldDeBastarder());
        angleField.setInputVerifier(new FieldDeBastarder());
    }

    private void initListeners() {
        addWallButton.addActionListener(buttonPress);
        removeWallButton.addActionListener(buttonPress);
        applyChangesButton.addActionListener(buttonPress);
        difficultySelector.addActionListener(difficultyListener);
        localBox.addActionListener(checkBoxListener);
        accessBox.addActionListener(checkBoxListener);
    }

    private void initValues() { //really just for testing, loads values from walls into form
        DecimalFormat df = new DecimalFormat("#.#");
        lengthField.setText(df.format(wall.getLengthMeters()));
        startHField.setText(df.format(wall.getStartHeight() * 1000)); //convert it to meters
        endHField.setText(df.format(wall.getEndHeight() * 1000));
        angleField.setText(df.format(wall.getAngle()));
    }

    private void initGrid() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lengthLabel, gbc);
        gbc.gridx = 1;
        this.add(lengthField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(startHLabel, gbc);
        gbc.gridx = 1;
        this.add(startHField, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        this.add(endHLabel, gbc);
        gbc.gridx = 1;
        this.add(endHField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(angleLabel, gbc);
        gbc.gridx = 1;
        this.add(angleField, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        this.add(localLabel, gbc);
        gbc.gridx = 1;
        this.add(localBox, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        this.add(accessLabel, gbc);
        gbc.gridx = 1;
        this.add(accessBox, gbc);
        gbc.gridy = 7;
        gbc.gridx = 0;
        this.add(difficultyLabel, gbc);
        gbc.gridx = 1;
        this.add(difficultySelector, gbc);
        gbc.gridy = 8;
        gbc.gridx = 0;
        this.add(applyChangesButton, gbc);
        gbc.gridy = 10;
        gbc.gridx = 0;
        this.add(addWallButton, gbc);
        gbc.gridx = 1;
        this.add(removeWallButton, gbc);

    }
}
