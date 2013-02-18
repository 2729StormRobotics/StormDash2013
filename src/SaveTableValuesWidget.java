
import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.properties.StringProperty;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evan1026
 */
public class SaveTableValuesWidget extends StaticWidget implements ITableListener {

    private ArrayList<String> _keys     = new ArrayList<String>();
    private FileWriter        _writer;
    private Scanner           _scanner;
    private ITable            _table;
    
    public final StringProperty filePath = new StringProperty(this, "File Path",  "networkTableValues.csv");
    public final StringProperty keyProp  = new StringProperty(this, "Table Keys", "");
    
    public SaveTableValuesWidget(ITable table){
        super();
        _table = table;
    }
    public SaveTableValuesWidget(ArrayList<String> keys, ITable table){
        super();
        _keys = keys;
        _table = table;
    }
    private void tryToCreateWriterAndReader(){
        try {
            _writer = new FileWriter(new File(filePath.getValue()), false);
            _scanner = new Scanner(new File(filePath.getValue()));
        } catch (IOException ex) {
            Logger.getLogger(SaveTableValuesWidget.class.getName()).log(Level.SEVERE, "Something went wrong", ex);
        }
    }
    public void addKey(String key){
        if (!_keys.contains(key)) {
            _keys.add(key);
        }
    }
    public void removeKey(String key){
        _keys.add(key);
    }
    
    @Override
    public void finalize(){
        try {
            super.finalize();
            _writer.flush();
            _writer.close();
            _scanner.close();
        } catch (Throwable ex) {
            Logger.getLogger(SaveTableValuesWidget.class.getName()).log(Level.SEVERE, "Could not properly finalize.", ex);
        }
    }
    
    @Override
    public void init() {
        tryToCreateWriterAndReader();
        _table.addTableListener(this);
    }

    public void propertyChanged(Property prop) {
        if (prop==keyProp){
            _keys = new ArrayList(Arrays.asList(keyProp.getValue().split(",")));
        }
        else if(prop==filePath){
            try {
                _writer.flush();
                _writer.close();
                _scanner.close();
            } catch (IOException ex) {
                Logger.getLogger(SaveTableValuesWidget.class.getName()).log(Level.SEVERE, "Couldn't change file path.", ex);
            }
        }
    }

    public void valueChanged(ITable itable, String key, Object newValue, boolean bln) {
        if (!_keys.contains(key)) {
            return;
        }
        
        ArrayList<String> lines = new ArrayList<String>();
        while (_scanner.hasNextLine()){
            lines.add(_scanner.nextLine());
        }
        try {
            boolean written = false;
            for (String line : lines){
                if (line.startsWith(key)){
                    line += "," + newValue.toString();
                    written = true;
                    break;
                }
            }
            if (!written){
                lines.add(key + "," + newValue.toString());
            }
            
            for(String line : lines){
                _writer.write(line + "\n");
            }
            
            _writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(SaveTableValuesWidget.class.getName()).log(Level.SEVERE, "Something went wrong", ex);
        }
    }
    
}
