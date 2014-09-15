package Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import com.sun.org.apache.xerces.internal.xs.StringList;


public class RuleFileParser {
	
	public RuleFileParser(String p)
	{
		dependantCharSetInstallation();
		Charset charset=Charset.forName("UTF-8");
		Path path=FileSystems.getDefault().getPath(p);
		l=new ArrayList<String> ();
		pass=new ArrayList< ArrayList<String> >();
		try(BufferedReader reader=Files.newBufferedReader(path, charset))
		{
			String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		    	line=whiteSpaceTrim(line);
		    	line=commentTrim(line);
		    	if(line.equals("")) continue;
		    	
		    	
		    	
		    	l.add(line);
		    }
			
		}
		catch (IOException x)
		{
		    System.err.format("IOException: %s%n", x);
		}
		int cnt=0;
		int i;
		for(i=0;i<l.size();i++)
		{
			if(l.get(i).equals(curlyOpen))
			{
				pass.add(new ArrayList<String>());
				i++;
				while(i<l.size() && ! l.get(i).equals(curlyClose))
				{
					pass.get(cnt).add(l.get(i));
					i++;
				}
				cnt++;
			}
		}
		
		
	}
	
	public String whiteSpaceTrim(String str)
	{
		return str.replaceAll("[\t' ']+", "");
	}
	
	public String commentTrim(String str)
	{
		return str.replaceAll("#.*", "");
	}
	
	public String extractReplaceRule(String x)
	{
		if(x.matches("[.*->.*]"))
		{
			String[] l=x.split("->");
			return l[1];
		}
		return "";
	}
	
	public  String stemOfWord(String word)
	{
		int i,j;
		
		for(i=0;i<pass.size();i++)
		{
			for(j=0;j<pass.get(i).size();j++)
			{
				String matcher=".*";
				matcher+=pass.get(i).get(j);
				if(word.matches(matcher))
				{
					int indx=word.length() - pass.get(i).get(j).length();
					if(check(word.substring(0, indx)))
					{
						word=word.substring(0, indx);
						System.out.println(word+" "+indx);
					}
				}
			}
		}
		return word;
	}
	
	public void dependantCharSetInstallation()
	{
		st=new TreeSet<Character>();
		st.add('া');
		st.add('ি');
		st.add('ী');
		st.add('ে');
		st.add('ু');
		st.add('ূ');
		st.add('ো');
	}
	
	public boolean check(String x)
	{
		int i;
		int ln=0;
		
		for(i=0;i<x.length();i++)
		{
			if(st.contains(x.charAt(i))) continue;
			ln++;
		}
		
		return ln>1;
	}
	
	
	
	private ArrayList<String> l;
	private ArrayList< ArrayList<String> > pass;
	
	String curlyOpen="{";
	String curlyClose="}";
	
	private TreeSet<Character> st;
	private HashMap<String,String> replaceRule;
	
	
}
