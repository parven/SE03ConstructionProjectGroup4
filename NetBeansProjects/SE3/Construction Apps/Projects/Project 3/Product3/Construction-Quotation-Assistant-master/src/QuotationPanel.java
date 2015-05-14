////////////////////////////////////////////////////////////////////////////////
//                      Construction Quotation Assistant                      //
//              Chris Antonello, Hayden Bartlett, Curtis Bootle,              //
//                     Damian Goldney, Michael Kitselaar                      //
//                       27.03.15 - Flinders University                       //
////////////////////////////////////////////////////////////////////////////////

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class QuotationPanel extends JPanel {

    private final DrawingGUI gui;

    private final DefaultTableModel tableModel;
    private JTable quote;
    private final JLabel subTotal, access, difficulty, location, accessPremium,
            difficultyPremium, locationPremium, totalPremiums, total, totalSleepers;
    private double accessPremiumDouble, difficultyPremiumDouble,
            locationPremiumDouble, totalPremiumDouble, totalDouble;
    private int sleeperCount;

    public QuotationPanel(DrawingGUI gui) {
        this.gui = gui;
        quote = new JTable();
        Container c = this;
        setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(600, 400));
        sleeperCount = 0;

        //create a table model to chance cells to not allow editing
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //Set the table with the bays and sleeper information
        quote.setModel(tableModel);
        JScrollPane quoteSP = new JScrollPane(quote);
        c.add(quoteSP);

        //Additional Quotation information
        JPanel outerAdditionalQuotationInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints q = new GridBagConstraints();

        //subtotal 
        JLabel subTotalLabel = new JLabel("Sub Total: ");
        Font bold = subTotalLabel.getFont().deriveFont(Font.BOLD);
        subTotalLabel.setFont(bold);
        q.gridx = 2;
        q.gridy = 0;
        q.ipady = 40;
        q.anchor = GridBagConstraints.LINE_START;
        outerAdditionalQuotationInfoPanel.add(subTotalLabel, q);

        subTotal = new JLabel();
        q.gridx = 3;
        q.gridy = 0;
        q.ipady = 40;
        outerAdditionalQuotationInfoPanel.add(subTotal, q);

        //total sleeper 
        JLabel totalSleepersLabel = new JLabel("Total Sleepers: ");
        q.gridx = 2;
        q.gridy = 1;
        q.ipady = 10;
        q.anchor = GridBagConstraints.LINE_START;
        outerAdditionalQuotationInfoPanel.add(totalSleepersLabel, q);

        totalSleepers = new JLabel();
        q.gridx = 3;
        q.gridy = 1;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(totalSleepers, q);

        //built locally
        JLabel locallyLabel = new JLabel("Built Locally: ");
        locallyLabel.setFont(bold);
        q.gridx = 0;
        q.gridy = 2;
        q.ipady = 10;
        q.ipadx = 30;
        outerAdditionalQuotationInfoPanel.add(locallyLabel, q);

        location = new JLabel();
        q.gridx = 1;
        q.gridy = 2;
        q.ipady = 10;
        q.ipadx = 30;
        outerAdditionalQuotationInfoPanel.add(location, q);

        JLabel locationPremiumLabel = new JLabel("Additional Location Cost: ");
        locationPremiumLabel.setFont(bold);
        q.gridx = 2;
        q.gridy = 2;
        q.ipady = 10;
        q.ipadx = 70;
        outerAdditionalQuotationInfoPanel.add(locationPremiumLabel, q);

        locationPremium = new JLabel();
        q.gridx = 3;
        q.gridy = 2;
        q.ipady = 10;
        q.ipadx = 30;
        outerAdditionalQuotationInfoPanel.add(locationPremium, q);

        //access
        JLabel accessLabel = new JLabel("Adequate Access: ");
        accessLabel.setFont(bold);
        q.gridx = 0;
        q.gridy = 3;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(accessLabel, q);

        access = new JLabel();
        q.gridx = 1;
        q.gridy = 3;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(access, q);

        JLabel accessPremiumLabel = new JLabel("Additional Access Cost: ");
        accessPremiumLabel.setFont(bold);
        q.gridx = 2;
        q.gridy = 3;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(accessPremiumLabel, q);

        accessPremium = new JLabel();
        q.gridx = 3;
        q.gridy = 3;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(accessPremium, q);

        //difficulty
        JLabel difficultyLabel = new JLabel("Difficulty: ");
        difficultyLabel.setFont(bold);
        q.gridx = 0;
        q.gridy = 4;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(difficultyLabel, q);

        difficulty = new JLabel();
        q.gridx = 1;
        q.gridy = 4;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(difficulty, q);

        JLabel difficultyPremiumLabel = new JLabel("Additional Difficulty Cost: ");
        difficultyPremiumLabel.setFont(bold);
        q.gridx = 2;
        q.gridy = 4;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(difficultyPremiumLabel, q);

        difficultyPremium = new JLabel();
        q.gridx = 3;
        q.gridy = 4;
        q.ipady = 10;
        outerAdditionalQuotationInfoPanel.add(difficultyPremium, q);

        //TotalPremiums
        JLabel totalPremiumsLabel = new JLabel("Total Additional Costs: ");
        totalPremiumsLabel.setFont(bold);
        q.gridx = 2;
        q.gridy = 5;
        q.ipady = 40;
        outerAdditionalQuotationInfoPanel.add(totalPremiumsLabel, q);

        totalPremiums = new JLabel();
        q.gridx = 3;
        q.gridy = 5;
        q.ipady = 40;
        outerAdditionalQuotationInfoPanel.add(totalPremiums, q);

        //Total
        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setFont(bold);
        q.gridx = 2;
        q.gridy = 6;
        q.ipady = 40;
        outerAdditionalQuotationInfoPanel.add(totalLabel, q);

        total = new JLabel();
        q.gridx = 3;
        q.gridy = 6;
        q.ipady = 40;
        outerAdditionalQuotationInfoPanel.add(total, q);

        c.add(outerAdditionalQuotationInfoPanel);
    }

    public void drawQuote() {
        Wall wall = gui.wall;
        int subtotal = 0;

        Object[][] data = new Object[wall.getNumberOfSegments()][5];

        for (int i = 0; i < wall.getNumberOfSegments(); i++) {
            WallSegment segment = wall.getSegment(i);

            data[i][0] = i + 1; // Segment number
            data[i][1] = segment.getNumberOfBays(); // qty of bays
            data[i][2] = segment.getSleepersInWallSegment(); // qty of sleepers
            data[i][3] = "$" + 170; // base sleeper cost
            data[i][4] = "$" + segment.getSleepersInWallSegment() * 170; // total cost
            subtotal += segment.getSleepersInWallSegment() * 170;
        }

        String[] colTitles = {"Segment", "Qty of Bays", "Qty of Sleepers", "Price Per Sleeper", "Total"};
        tableModel.setDataVector(data, colTitles);
        calculatePremiums(subtotal, wall);
        setColumnWidths();
        setInvoiceAmounts(subtotal, wall);
    }

    private void setInvoiceAmounts(int subtotal, Wall wall) {
        subTotal.setText("$" + subtotal);
        location.setText(wall.getLocal());
        locationPremium.setText("$" + locationPremiumDouble);
        access.setText(wall.getAccess());
        accessPremium.setText("$" + accessPremiumDouble);
        difficulty.setText(wall.getDifficulty());
        difficultyPremium.setText("$" + difficultyPremiumDouble);
        totalPremiums.setText("$" + totalPremiumDouble);
        total.setText("$" + totalDouble);
        sleeperCount = wall.getSleepersInWall();
        totalSleepers.setText(Integer.toString(sleeperCount));
    }

    private void calculatePremiums(int subtotal, Wall wall) {
        totalPremiumDouble = 0;
        locationPremiumDouble = 0;
        accessPremiumDouble = 0;
        difficultyPremiumDouble = 0;
        //location premuim
        if (!wall.getLocalBool()) {
            locationPremiumDouble = subtotal * 0.05;
        }
        //access premium
        if (!wall.getAccessBool()) {
            accessPremiumDouble = subtotal * 0.3;
        }
        //difficulty premium
        switch (wall.getDifficulty()) {
            case "sandy":
                difficultyPremiumDouble = subtotal * 0.1;
                break;
            case "limestone":
                difficultyPremiumDouble = subtotal * 0.15;
                break;
            case "bluestone":
                difficultyPremiumDouble = subtotal * 0.3;
                break;
        }
        totalPremiumDouble = locationPremiumDouble + accessPremiumDouble + difficultyPremiumDouble;
        totalDouble = subtotal + totalPremiumDouble;
    }

    private void setColumnWidths() {
        //set columns in table to be unique sizes
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = quote.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);

            } else {
                column.setPreferredWidth(120);
            }
        }
    }
}
