package storm2013.smartdashboard;

import edu.wpi.first.smartdashboard.properties.Property;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.smartdashboard.livewindow.elements.NameTag;
import edu.wpi.first.smartdashboard.properties.FileProperty;
import edu.wpi.first.smartdashboard.types.DataType;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class LoadSensorWidget extends AbstractTableWidget {

    public static final DataType[] TYPES = {LoadSensorType.get()};
    
    public final FileProperty logFileProperty = new FileProperty(this, "Log file","");
    
    private long _startTime = System.currentTimeMillis();
    private FileWriter _logFile = null;

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
        setNumberBinding("amperage", new NumberBindable() {
            public void setBindableValue(double value) {
                if(_logFile != null) {
                    try {
                        _logFile.write("Amperage," + (System.currentTimeMillis()-_startTime)/1.0e3 + "," + value + "\n");
                    } catch (IOException ex) {}
                }
                ampDisplay.setBindableValue(value);
            }
        });
        add(ampDisplay,c);
        
        c.gridy = 2;
        voltDisplay.setFocusable(false);
        voltDisplay.setText("0.000");
        voltDisplay.setColumns(4);
        setNumberBinding("voltage", new NumberBindable() {
            public void setBindableValue(double value) {
                if(_logFile != null) {
                    try {
                        _logFile.write("Voltage," + (System.currentTimeMillis()-_startTime)/1.0e3 + "," + value + "\n");
                    } catch (IOException ex) {}
                }
                voltDisplay.setBindableValue(value);
            }
        });
        add(voltDisplay,c);
        
		setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
        
		revalidate();
		repaint();
    }
    @Override
    public void init() {
        nameTag.setText(getFieldName());
        _startTime = System.currentTimeMillis();
    }

    public void propertyChanged(Property prop) {
        if(prop == logFileProperty) {
            if(_logFile != null) {
                try {
                    _logFile.close();
                } catch (IOException ex) {}
            }
            _logFile = null;
            try {
                _logFile = new FileWriter(logFileProperty.getValue());
                _logFile.write("Time,Voltage,Amperage\n");
            } catch (IOException ex) {
                Logger.getLogger(LoadSensorWidget.class.getName()).log(Level.SEVERE, "File \"" + logFileProperty.getValue() + "\" not found!", ex);
                _logFile = null;
            }
        }
    }
}
