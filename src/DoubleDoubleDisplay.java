import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;
import edu.wpi.first.wpilibj.tables.ITable;
import javax.swing.JLabel;

public class DoubleDoubleDisplay extends AbstractTableWidget implements NumberBindable {

    public static final DataType[] TYPES = {DoubleDoubleType.get()};

    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();

    @Override
    public void init() {

        add(label1);
        add(label2);
        setNumberBinding("d1", this);
        setNumberBinding("d2", this);
    }

    @Override
    public void doubleChanged(ITable source, String key, double value, boolean isNew) {

        if (key.equals("d1")) {
            label1.setText(""+value);
        } else if (key.equals("d2")) {
            label2.setText(""+value);
        }
    }

    public void propertyChanged(Property prprt) {}
    public void setBindableValue(double d) {}
}
