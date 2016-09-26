package werkzeuge;
import java.io.IOException;

public class JahrAuswahlWerkzeug
{
    static JahrAuswaehlWerkzeugUI _ui;
    
    
    public JahrAuswahlWerkzeug()
    {
        
    }
    
    public static void main(String[] args) throws IOException
    {
        _ui = new JahrAuswaehlWerkzeugUI(90);
    }
    
    
}
