package storm2013.smartdashboard;

import javax.swing.JLabel;

import edu.wpi.first.smartdashboard.gui.elements.bindings.*;
import edu.wpi.first.smartdashboard.properties.*;
import edu.wpi.first.smartdashboard.types.*;

public class LabelSpeedometer extends AbstractValueWidget implements NumberBindable {

    public static final DataType[] TYPES = {DataType.NUMBER};

    JLabel label = new JLabel();

    @Override
    public void init() {
        add(label);
        setNumberBinding(this);
    }

    public void propertyChanged(Property prprt) {
    }

    public void setBindableValue(double d) {
        label.setText(""+d);
    }
}
