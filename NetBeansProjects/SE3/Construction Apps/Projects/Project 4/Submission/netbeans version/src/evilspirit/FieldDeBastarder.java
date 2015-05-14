package evilspirit;

import sun.misc.FloatingDecimal;

import javax.swing.*;
import java.text.DecimalFormat;

/**
 * An input verifier to check entered values on the fly
 */
class FieldDeBastarder extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        DecimalFormat df = new DecimalFormat("#.##");
        try {
            double value = FloatingDecimal.parseDouble(text);
            value = (value > 0) ? value : -value;   //remove negatives
            ((JTextField) input).setText(df.format(value));
            return (true);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}