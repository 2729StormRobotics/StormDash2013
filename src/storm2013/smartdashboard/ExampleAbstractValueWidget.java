package storm2013.smartdashboard;

import javax.swing.JLabel;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.BooleanBindable;
import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.smartdashboard.gui.elements.bindings.StringBindable;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;

public class ExampleAbstractValueWidget extends AbstractValueWidget implements NumberBindable,BooleanBindable,StringBindable {

    //AbstractValueWidgets must implement at least one of: NumberBindable, BooleanBindable and StringBindable
    //this determines what kind of data the widget is recieving from the robot: numbers, booleans or strings

    //the DataType array tells the SmartDashboard what input this widget can take
    public static final DataType[] TYPES = {DataType.NUMBER, DataType.BOOLEAN, DataType.STRING};

    //AbstractValueWidgets can also have properties, like StaticWidgets

    JLabel label = new JLabel();

    @Override
    public void init() {

        add(label); //add visible objects here

        setNumberBinding(this);
        setBooleanBinding(this);
        setStringBinding(this);
    }

    public void propertyChanged(Property prop) {
        //see properties in StaticWidget
    }

    //these functions are called when the binded variable in the table is altered

    public void setBindableValue(double d) {
        label.setText(""+d);
    }

    public void setBindableValue(boolean b) {
        label.setText(""+b);
    }

    public void setBindableValue(String s) {
        label.setText(s);
    }

}
