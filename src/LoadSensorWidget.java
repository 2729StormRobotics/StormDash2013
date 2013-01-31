import edu.wpi.first.smartdashboard.properties.Property;
import javax.swing.JLabel;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.smartdashboard.types.DataType;
import edu.wpi.first.wpilibj.tables.ITable;

public class LoadSensorWidget extends AbstractTableWidget implements NumberBindable {

    public static final DataType[] TYPES = {LoadSensorType.get()};

    JLabel ampLabel = new JLabel();
    JLabel voltLabel = new JLabel();

    @Override
    public void init() {

        add(ampLabel);
        add(voltLabel);

        setNumberBinding("amps",this);
        setNumberBinding("volts",this);
    }

    public void propertyChanged(Property prop) {
    }

    @Override
    public void doubleChanged(ITable source, String key, double value, boolean isNew) {

        //this is what happens when a double is changed
        //you can tell doubles apart using their keys, and access their values with 'value'
        if (key.equals("amperage")) {
            ampLabel.setText("Amperage: "+value);
        } else if (key.equals("voltage")) {
            voltLabel.setText("Voltage: "+value);
        }
    }

    public void setBindableValue(double d) {}
}
