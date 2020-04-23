package msc.ai.ui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import msc.ai.model.VerificationStatus;

/**
 *
 * @author Keshan De Silva
 */
public class ImageRenderer extends DefaultTableCellRenderer
{
    JLabel label = new JLabel();

    ImageIcon validIcon = new ImageIcon(getClass().getResource("/msc/ai/resources/valid.png"));
    ImageIcon invalidIcon = new ImageIcon(getClass().getResource("/msc/ai/resources/invalid.png"));
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column)
    {
        if (value instanceof VerificationStatus)
        {
            VerificationStatus status = (VerificationStatus)value;
            if (status.equals(VerificationStatus.VERIFY_PASS))
            {
                label.setText("Verified");
                label.setIcon(validIcon);
            }
            else if (status.equals(VerificationStatus.VERIFY_FAIL))
            {
                label.setText("Verification Fail");
                label.setIcon(invalidIcon);
            }
            else if (status.equals(VerificationStatus.NOT_VERIFY))
            {
                label.setText(" --- ");
                label.setIcon(null);
            }
        }
        return label;
    }
}
