import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;

/**
 * 
 * @author Denis Migol
 *
 */
public class Test {

	public static final String html = "<html><head></head><body></body></html>";
	
	public static final String readFile(File file) throws IOException {
		StringBuilder ret = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = br.readLine()) != null) {
			ret.append(line + "\n");
		}
		return ret.toString();
	}

	public static void main(String[] args) throws Throwable {
		Parser parser = new Parser();
		parser.setInputHTML(readFile(new File("addnews.txt")));
		NodeList nl = parser.parse(null);
		NodeList nl1 = nl.extractAllNodesThatMatch(new HasAttributeFilter("name", "entryform"), true);
		System.out.println(nl);
		System.out.println(nl1);
	}
}
