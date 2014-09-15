package Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.xs.StringList;


public class RuleFileParser {
	
	public RuleFileParser(String p)
	{
		
		Charset charset=Charset.forName("UTF-8");
		Path path=FileSystems.getDefault().getPath(p);
		l=new ArrayList<String> ();
		pass=new ArrayList< ArrayList<String> >();
		try(BufferedReader reader=Files.newBufferedReader(path, charset))
		{
			String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		    	l.add(line);
		    }
			
		}
		catch (IOException x)
		{
		    System.err.format("IOException: %s%n", x);
		}
		int cnt=0;
		for(int i=0;i<l.size();i++)
		{
			System.out.println(i);
			if(l.get(i).equals(curlyOpen))
			{
				i++;
				while(i<l.size() && ! l.get(i).equals(curlyClose))
				{
					System.out.println(l.get(i));
					i++;
				}
			}
		}
	}
	
	private ArrayList<String> l;
	private ArrayList< ArrayList<String> > pass;
	
	String curlyOpen="{";
	String curlyClose="}";
	
	
}
