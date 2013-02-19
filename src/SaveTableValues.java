
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
public class SaveTableValues implements ITableListener {

    private FileWriter _writer;
    private ITable     _table;
    private long       _startTime = System.currentTimeMillis();
    private String     _filePath  = "";
    private String     _key       = "";
    
    public SaveTableValues(ITable table, String filePath, String key){
        _filePath = filePath;
        _key = key;
        _table = table;
        _table.addTableListener(this);
        try {
            _writer = new FileWriter(new File(_filePath), true);
            _writer.write("time," + key + "\n");
            _writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(SaveTableValues.class.getName()).log(Level.SEVERE, "Something went wrong", ex);
        }
    }
    
    @Override
    public void finalize(){
        try {
            super.finalize();
            _writer.flush();
            _writer.close();
        } catch (Throwable ex) {
            Logger.getLogger(SaveTableValues.class.getName()).log(Level.SEVERE, "Could not properly finalize.", ex);
        }
    }

    public void valueChanged(ITable itable, String key, Object newValue, boolean bln) {
        if (!_key.equals(key)) {
            return;
        }
        
        try {
            _writer.write((_startTime - System.currentTimeMillis()) + "," + newValue.toString());
            _writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(SaveTableValues.class.getName()).log(Level.SEVERE, "Something went wrong", ex);
        }
    }
    
}
