package storm2013.smartdashboard;

import edu.wpi.first.smartdashboard.properties.Property;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.livewindow.elements.NameTag;
import edu.wpi.first.smartdashboard.types.DataType;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoadSensorWidget extends AbstractTableWidget {

    public static final DataType[] TYPES = {LoadSensorType.get()};

    private final JLabel ampNameTag  = new JLabel("Amperage:");
    private final UneditableNumberField ampDisplay  = new UneditableNumberField();
    private final JLabel voltNameTag = new JLabel("Voltage:");
    private final UneditableNumberField voltDisplay = new UneditableNumberField();

    public LoadSensorWidget() {
        ampNameTag.setHorizontalAlignment(JLabel.RIGHT);
        voltNameTag.setHorizontalAlignment(JLabel.RIGHT);
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridy = 1;
        add(ampNameTag,c);
        c.gridy = 2;
        add(voltNameTag,c);
                
        c.gridy = 0;
        
        c.gridwidth = 3;
        
        nameTag = new NameTag("");
		nameTag.setHorizontalAlignment(JLabel.CENTER);
        add(nameTag, c);
        
        c.gridwidth = 1;
        
        c.gridx = 2;
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridy = 1;
        ampDisplay.setFocusable(false);
        ampDisplay.setText("0.000");
        ampDisplay.setColumns(4);
        setNumberBinding("amperage", ampDisplay);
        add(ampDisplay,c);
        
        c.gridy = 2;
        voltDisplay.setFocusable(false);
        voltDisplay.setText("0.000");
        voltDisplay.setColumns(4);
        setNumberBinding("voltage", voltDisplay);
        add(voltDisplay,c);
        
		setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
        
		revalidate();
		repaint();
    }
    @Override
    public void init() {
        nameTag.setText(getFieldName());
    }

    public void propertyChanged(Property prop) {}
}
