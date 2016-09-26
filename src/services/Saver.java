package services;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class Saver
{
    private String _path;

    public Saver(String pathToFile) throws IOException
    {
        _path = pathToFile;
    }

    public void delete(String path)
    {
        try
        {
            File file = new File(_path);

            if (file.delete())
            {
                System.out.println("The File: " + file.getName()
                        + " was deleted successfully!");
            }
            else
            {
                System.out.println(
                        "The File: " + file.getName() + " could no be deleted");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method modifies or rewrites the requested file when called
     * @throws IOException 
     * @throws UnsupportedEncodingException 
     */
    public void write(String input) throws IOException
    {

        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(_path), "utf-8")))
        {
            writer.write(input);
            System.out.println("The File was created at: " + _path);
        }

    }

    /**
     * Reads out all the lines in a textfile.
     */
    public void read() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(_path));
        String line = null;
        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
        }
        br.close();
    }

}
