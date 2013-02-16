package storm2013.smartdashboard;

import edu.wpi.first.smartdashboard.properties.Property;
import javax.swing.JLabel;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.BooleanBindable;
import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.smartdashboard.gui.elements.bindings.StringBindable;
import edu.wpi.first.smartdashboard.types.DataType;
import edu.wpi.first.wpilibj.tables.ITable;

public class ExampleAbstractTableValue extends AbstractTableWidget implements NumberBindable,BooleanBindable,StringBindable {

    //specify which types the subtable can bind to, similar to AbstractValueTables
    //when using custom types, use '<type name>.get()'
    public static final DataType[] TYPES = {ExampleType.get()};

    JLabel label = new JLabel();

    double val1 = 0;
    boolean val2 = false;
    String val3 = "";
    
    @Override
    public void init() {

        add(label);

        //this binds to propeties WITHIN THE SUBTABLE, similar to AbstractValueWidgets
        //the difference is that a key must be specified
        setNumberBinding("keyA",this);
        setBooleanBinding("keyB",this);
        setStringBinding("keyC", this, "");
    }

    public void propertyChanged(Property prop) {
        //see AbstractValueWidget or StaticWidget
    }

    @Override
    public void doubleChanged(ITable source, String key, double value, boolean isNew) {

        //this is what happens when a double is changed
        //you can tell doubles apart using their keys, and access their values with 'value'
        if (key.equals("keyA")) {
            val1 = value;
        }
        updateLabel();
    }

    @Override
    public void booleanChanged(ITable source, String key, boolean value, boolean isNew) {

        //this is what happens when a double is changed
        //you can tell doubles apart using their keys, and access their values with 'value'
        if (key.equals("keyB")) {
            val2 = value;
        }
        updateLabel();
    }

    @Override
    public void stringChanged(ITable source, String key, String value, boolean isNew) {

        //this is what happens when a double is changed
        //you can tell doubles apart using their keys, and access their values with 'value'
        if (key.equals("keyC")) {
            val3 = value;
        }
        updateLabel();
    }

    public void updateLabel() {

        label.setText(val1+" | "+val2+" | "+val3);
    }

    //I'm actually not sure what these are used for...
    //but you need them
    public void setBindableValue(double d) {}
    public void setBindableValue(boolean b) {}
    public void setBindableValue(String string) {}

}
