package werkzeuge;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WochenauswahlWerkzeugUI
{
    private static final int NUMBER_OF_WEEKS_IN_A_YEAR = 52;
    //The initial Frame
    private JDialog _frame;
    //The productivity Panel 
    private JPanel _productivityPanel;
    //The Productivity Checkbox
    private Checkbox _productivityCheckbox;
    //The save and restore Panel
    private JPanel _saveAndRestorePanel;
    //The save Button
    private JButton _save;
    //The restore Button
    private JButton _restore;
    //The Button Pannel for the individual weeks
    private JPanel _buttonPannel;
    //The Panel for the Back Button
    private JPanel _zurueckPanel;
    //The Back Button
    private JButton _zurueckButton;
    //The ArrayList with all the Buttons representing the weeks
    private ArrayList<JButton> _buttonList;
    //An Array with values of 1 and 0 for the color code of the buttons
    private Integer[] _colors;

    public WochenauswahlWerkzeugUI()
    {
        _buttonList = new ArrayList<>();
        _colors = new Integer[NUMBER_OF_WEEKS_IN_A_YEAR];
        init();
        for (int i = 0; i < _colors.length; ++i)
        {
            _colors[i] = 3;
        }
    }

    private void init()
    {
        _frame = new JDialog((JFrame) null,
                "Your 90 Year Life! Have you reached your goals?");
        _frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        _productivityPanel = new JPanel();
        _frame.add(_productivityPanel, BorderLayout.NORTH);

        _productivityCheckbox = new Checkbox("I was productive!");
        _productivityPanel.add(_productivityCheckbox);

        _saveAndRestorePanel = new JPanel();
        _frame.add(_saveAndRestorePanel, BorderLayout.CENTER);

        _save = new JButton("Save");
        _restore = new JButton("Restore");

        _saveAndRestorePanel.add(_save);
        _saveAndRestorePanel.add(_restore);

        _buttonPannel = new JPanel();
        _buttonPannel.setLayout(new GridLayout(10, 5));
        _frame.add(_buttonPannel, BorderLayout.SOUTH);
        
        generateButtons();

        _zurueckPanel = new JPanel();
        _buttonPannel.add(_zurueckPanel, BorderLayout.SOUTH);

        _zurueckButton = new JButton("Zurück");
        _zurueckPanel.add(_zurueckButton);

        _frame.pack();
        _frame.setVisible(true);

    }

    public JDialog getFrame()
    {
        return _frame;
    }

    public JPanel getProductivityPanel()
    {
        return _productivityPanel;
    }

    public Checkbox getProductivityCheckbox()
    {
        return _productivityCheckbox;
    }

    public JPanel getSaveAndRestorePanel()
    {
        return _saveAndRestorePanel;
    }

    public JButton getSaveButton()
    {
        return _save;
    }

    public JButton getRestoreButton()
    {
        return _restore;
    }

    public JPanel getButtonPanel()
    {
        return _buttonPannel;
    }

    public JPanel getZurueckPanel()
    {
        return _zurueckPanel;
    }

    public JButton getZurueckButton()
    {
        return _zurueckButton;
    }
    
    public ArrayList<JButton> getWeeksButtons()
    {
        return _buttonList;
    }

    private void generateButtons()
    {
        for (int i = 0; i <= NUMBER_OF_WEEKS_IN_A_YEAR; ++i)
        {
            _buttonList.add(new JButton("Week " + i));
            JButton temp = _buttonList.get(i);
            temp.addActionListener(new ActionListener()
            {
                
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (_productivityCheckbox.getState())
                    {
                        temp.setBackground(Color.green);
                        temp.setOpaque(true);
                        _colors[_buttonList.indexOf(temp)] = 1;
                    }
                    else
                    {
                        temp.setBackground(Color.red);
                        temp.setOpaque(true);
                        _colors[_buttonList.indexOf(temp)] = 0;
                    }
                    
                }
            });
            
            _buttonPannel.add(temp);
        }
    }
}
