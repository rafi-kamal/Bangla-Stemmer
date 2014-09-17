package Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;


public class RuleFileParser {
	
	private ArrayList<String> lines ;
	private ArrayList< ArrayList<String> > pass;
	
	private static final String CURLY_OPEN = "{";
	private static final String CURLY_CLOSE = "}";
	
	private TreeSet<Character> st;
	private TreeSet<String> escape;
	private HashMap<String,String> replaceRule;
	
	public RuleFileParser(String p)
	{
		replaceRule= new HashMap<String,String>();
		dependantCharSetInstallation();
//		escapeOfRuleInstallation();
		Charset charset=Charset.forName("UTF-8");
		Path path=FileSystems.getDefault().getPath(p);
		lines = new ArrayList<String> ();
		pass=new ArrayList< ArrayList<String> >();
		
		try(BufferedReader reader=Files.newBufferedReader(path, charset))
		{
			String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		    	line=whiteSpaceTrim(line);
		    	line=commentTrim(line);
		    	if(line.equals("")) continue;
		    	String replace=extractReplaceRule(line);
		    	line=line.replaceAll("->.*", "");
		    	if(!replace.equals(""))
		    	{
		    		replaceRule.put(line,replace);
		    	}
		    	lines.add(line);
		    }
			
		}
		catch (IOException x)
		{
		    x.printStackTrace();
		}
		
		int cnt=0;
		for(int i=0;i<lines.size();i++)
		{
			if(lines.get(i).equals(CURLY_OPEN))
			{
				pass.add(new ArrayList<String>());
				i++;
				while(i<lines.size() && ! lines.get(i).equals(CURLY_CLOSE))
				{
					pass.get(cnt).add(lines.get(i));
					i++;
				}
				cnt++;
			}
		}
		
	}
	
	private String whiteSpaceTrim(String str)
	{
		return str.replaceAll("[\t' ']+", "");
	}
	
	private String commentTrim(String str)
	{
		return str.replaceAll("#.*", "");
	}
	
	private String extractReplaceRule(String str)
	{
		if(str.matches(".*->.*"))
		{
			String[] l=str.split("->");
			return l[1];
		}
		return "";
	}
	
	public String stemOfWord(String word)
	{
		int i,j;
		
		for(i=0;i<pass.size();i++)
		{
			for(j=0;j<pass.get(i).size();j++)
			{
				String replacePrefix = pass.get(i).get(j);
				
				String matcher = ".*" + replacePrefix + "$";
				if(word.matches(matcher))
				{
					int indx = word.length() - replacePrefix.length();
					if(replaceRule.containsKey(replacePrefix))
					{
						String replaceSuffix = replaceRule.get(replacePrefix);
						StringBuilder builder=new StringBuilder(word);
						int k,l;
						for(k=indx,l=0;k<indx+replaceSuffix.length();k++,l++)
						{
							if(replaceSuffix.charAt(l)!='.')
							{
								builder.setCharAt(k, replaceSuffix.charAt(l));
							}
						}
						word=builder.substring(0, k);
					}
					else if(/* escape.contains(pass.get(i).get(j)) || */ check(word.substring(0, indx)))
					{
						word=word.substring(0, indx);
					}
					
					break;
				}
			}
		}
		
		return word;
	}
	
	private void dependantCharSetInstallation()
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
	
//	private void escapeOfRuleInstallation()
//	{
//		escape=new TreeSet<String>();
//		escape.add("চ্ছি");
//		escape.add("চ্ছিল");
//		escape.add("চ্ছে");
//		escape.add("চ্ছিস");
//		escape.add("চ্ছিলেন");
//		escape.add("টি");
//		escape.add("টা");
//		escape.add("েরটা");
//		escape.add("গুলো");
//	}
	
	private boolean check(String word)
	{
		int i;
		int wordLength = 0;
		
		for(i=0;i<word.length();i++)
		{
			if(st.contains(word.charAt(i))) 
				continue;
			wordLength++;
		}
		
		return wordLength >= 1;
	}
	
}
