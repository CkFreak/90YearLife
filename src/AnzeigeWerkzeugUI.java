import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This is the class that represents the applications main window with all the buttons. 
 * @author Marc Blaesche
 * @version 16.6.2016
 *
 */
public class AnzeigeWerkzeugUI
{
    //The main JFrame that displays the Window of the application
    private JFrame _frame;
    //The Panel that holds the Buttons
    private JPanel _buttonPanel;
    //A List that holds all the Buttons of the view
    private ArrayList<JButton> _buttons;
    //The Panel that holds the Checkboxes
    private JPanel _checkboxPanel;
    //The productivity checkbox
    private Checkbox _checkbox;
    //The Pannel for Save and Restore
    private JPanel _saveAndRestorePanel;
    //The save Button
    private JButton _save;
    //The Restore Button
    private JButton _restore;
    //The TextFile Saver class
    private Saver _saver;
    //The String with savable data
    private String _data;
    //An Array with values of 1 and 0 for the color code of the buttons
    private Integer[] _colors;

    /**
     * Initializes the Window
     * @param amount Number of Buttons
     * @throws IOException 
     */
    public AnzeigeWerkzeugUI(int amount) throws IOException
    {
        _buttons = new ArrayList<>();
        init(amount);
        _colors = new Integer[amount + 1];
        _saver = new Saver("/Users/Timbo/Desktop/90YearLife.txt");
        _data = "Nothing in here yet";
        for (int i = 0; i < _colors.length; ++i)
        {
            _colors[i] = 0;
        }
    }

    /**
     * Initializes the Frame and sets the number of buttons
     * @param amount The number of Buttons
     */
    private void init(int amount)
    {
        _frame = new JFrame("Your 90 Year Life! Have you reached your goals?");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _checkbox = new Checkbox("Productive");

        _checkboxPanel = new JPanel();
        _checkboxPanel.add(_checkbox);

        _frame.add(_checkboxPanel, BorderLayout.NORTH);

        _buttonPanel = new JPanel();
        _buttonPanel.setLayout(new GridLayout(10, 100));

        _frame.add(_buttonPanel, BorderLayout.SOUTH);
        
        _saveAndRestorePanel = new JPanel();
        _frame.add(_saveAndRestorePanel, BorderLayout.CENTER);

        _save = new JButton("Save");
        _save.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    _data = prepareString(_colors);
                    _saver.write(_data);
                }
                catch (IOException e1)
                {
                    System.out.println("Computer sagt nein!");
                }

            }
        });

        _saveAndRestorePanel.add(_save);
        
        _restore = new JButton("Restore");
        _restore.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                restoreSavedData(getFile());
                
            }
        });
        
        _saveAndRestorePanel.add(_restore);

        createButtons(amount);

        _frame.pack();
        _frame.setVisible(true);
    }

    protected String getFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setVisible(true);
        fileChooser.setDialogTitle("Choose your saveFile");
        fileChooser.setCurrentDirectory(null);
        fileChooser.showOpenDialog(_frame);
        String path = fileChooser.getSelectedFile().getAbsolutePath();
        return path;
    }

    /**
     * Creates the wanted amount of Buttons and adds it to the local ArrayList with an ActionListener
     * @param amount The wanted amount of Buttons
     */
    private void createButtons(int amount)
    {
        for (int i = 0; i < amount; ++i)
        {
            _buttons.add(new JButton("Button" + i));
            JButton temp = _buttons.get(i);
            _buttons.get(i)
                .addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (_checkbox.getState())
                        {
                            temp.setBackground(Color.green);
                            temp.setOpaque(true);
                            _colors[_buttons.indexOf(temp)] = 1;
                        }
                        else
                        {
                            temp.setBackground(Color.red);
                            temp.setOpaque(true);
                            _colors[_buttons.indexOf(temp)] = 0;
                        }
                    }
                });
            _buttonPanel.add(_buttons.get(i));
        }

    }

    /**
     * This method prepared a String, so it can be saved to a Textfile
     */
    private String prepareString(Integer[] ints)
    {
        String temp = "";
        for (int i = 0; i <= ints.length - 1; ++i)
        {
            int kurz = ints[i];
            temp += String.valueOf(kurz);
        }
        return temp;
    }

    private void restoreSavedData(String filepath)
    {
        try
        {
            List<String> saveData = Files.readAllLines(Paths.get(filepath));
            ArrayList<Character> data = new ArrayList<>();
            
            for(int i = 0; i < saveData.get(0).length() - 1; ++i)
            {
                data.add(saveData.get(0).charAt(i));
            }
            
            int zaehler =  0;
            
            for (JButton button : _buttons)
            {
               switch (data.get(zaehler))
            {
            case '0':
                button.setBackground(Color.red);
                button.setOpaque(true);
                break;

            case '1':
                button.setBackground(Color.green);
                button.setOpaque(true);
                break;
            default:
                button.setBackground(Color.blue);
                button.setOpaque(true);
                break;
            } 
              ++zaehler; 
            }
        }
        catch (IOException e)
        {
            System.out.println(
                    "Computer sagt nein! Diese Datei gibt es nicht, sie ist schreibgeschützt oder etwas ist furchtbar schief gelaufen");
            e.printStackTrace();
        }
        
    }

}
