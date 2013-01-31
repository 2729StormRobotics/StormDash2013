import javax.swing.JLabel;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.BooleanProperty;
import edu.wpi.first.smartdashboard.properties.NumberProperty;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.properties.StringProperty;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;

public class ExampleStaticWidget extends StaticWidget {

    //public final <prop type> <prop name> = new <prop type>(this, "<title>", <initial value>);
    public final NumberProperty numProp = new NumberProperty(this, "someTitle", 1234);
    public final BooleanProperty boolProp = new BooleanProperty(this, "hasCheeseburger", true);
    public final StringProperty stringProp = new StringProperty(this, "myTitle", "this is my string");

    JLabel label = new JLabel();

    @Override
    public void init() {
        //called when the widget is created
        add(label); //add visible objects here
        label.setText("Static Widget");

        //putting data into the network table will allow you to use AbstractValueWidgets and AbstractTableWidgets

        //-- AbstractValueWidgets --
        //put single values into the network table using these functions:
        Robot.getTable().putNumber("key1", 4321);
        Robot.getTable().putBoolean("key2", false);
        Robot.getTable().putString("key3", "this is my other string");

        //-- AbstractTableWidgets --
        //to get a subtable in the network table, use:
        ITable subTable = Robot.getTable().getSubTable("key4");
        //this will create a subtable with the given key if it does not already exist, then return it
        //you con then access values in the subtable like you would in the main table
        subTable.putNumber("keyA", 7);
        subTable.putBoolean("keyB", true);
        subTable.putString("keyC", "guacamole");
        //you MUST create a type in the table and link it to a type class
        //use '~TYPE~' as the key, and identify the type by it's 'LABEL' property
        subTable.putString("~TYPE~", "ExampleType");
    }

    public void propertyChanged(Property prop) {
        if (prop==numProp) {
            //do something when 'numProp' is changed
            //access the property with:
            numProp.getValue().doubleValue();
        } else if (prop==boolProp) {
            //same here, but make sure to use '.booleanValue()'
            boolProp.getValue().booleanValue();
        } else if (prop==stringProp) {
            //same here, but omit '.doubleValue()'
            stringProp.getValue();
        }
    }
}
