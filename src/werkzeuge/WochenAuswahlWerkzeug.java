package werkzeuge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WochenAuswahlWerkzeug
{
    private WochenauswahlWerkzeugUI _ui;
    
    public WochenAuswahlWerkzeug()
    {
        _ui = new WochenauswahlWerkzeugUI();
    }
    
    private void registerActionListener()
    {
        _ui.getSaveButton().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Speichern überlegen
                
            }
        });
        
        _ui.getRestoreButton().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Restore überlegen
                
            }
        });
        
        _ui.getZurueckButton().addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Wie ruft man den parent Frame wieder auf? 
                
            }
        });
    }
}
