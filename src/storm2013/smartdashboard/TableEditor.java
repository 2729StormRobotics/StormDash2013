package storm2013.smartdashboard;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.*;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import javax.swing.JLabel;

public class TableEditor extends StaticWidget {

    public final NumberProperty speedProp = new NumberProperty(this, "Speed", 0);
    public final BooleanProperty hasBaconProp = new BooleanProperty(this, "HasBacon", false);

    public final NumberProperty d1Prop = new NumberProperty(this, "amperage", 0);
    public final NumberProperty d2Prop = new NumberProperty(this, "voltage", 0);

    private JLabel label = new JLabel();

    public void init() {

        label.setText("Table Editor");
        add(label);
        propertyChanged(speedProp);
        propertyChanged(hasBaconProp);

        propertyChanged(d1Prop);
        propertyChanged(d2Prop);
    }

    public void propertyChanged(Property prop) {

        Robot.getTable().putNumber(speedProp.getName(),speedProp.getValue().doubleValue());
        Robot.getTable().putBoolean(hasBaconProp.getName(),hasBaconProp.getValue().booleanValue());
        
        ITable ddTable = Robot.getTable().getSubTable("qwerty");
        ddTable.putString("~TYPE~", "Load Sensor");
        ddTable.putNumber(d1Prop.getName(),d1Prop.getValue().doubleValue());
        ddTable.putNumber(d2Prop.getName(),d2Prop.getValue().doubleValue());
    }
}
