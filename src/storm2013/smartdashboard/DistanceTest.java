/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.smartdashboard;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Joe
 */
public class DistanceTest extends StaticWidget {
    private static final String[] keys = { "Near","Center line","Opponent Auto","Feeder" };
    
    private int _index;
    
    private JButton button;
    
    private void _setIndex(int index) {
        _index = (index%keys.length+keys.length)%keys.length;
        Robot.getTable().putString("Distance", keys[_index]);
    }
    public DistanceTest() {}

    @Override
    public void init() {
        button = new JButton("Increment");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _setIndex(_index+1);
            }
        });
        add(button);
        _setIndex(0);
    }

    public void propertyChanged(Property property) {}
}
