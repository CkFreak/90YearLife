import java.io.IOException;

public class AnzeigeWerkzeug
{
    static AnzeigeWerkzeugUI _ui;
    
    
    public AnzeigeWerkzeug()
    {
        
    }
    
    public static void main(String[] args) throws IOException
    {
        _ui = new AnzeigeWerkzeugUI(90);
    }
    
    
}
