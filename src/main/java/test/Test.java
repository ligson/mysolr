package test;

import org.apache.lucene.analysis.util.TokenizerFactory;
import java.net.*;

public class Test {
	public static void main(String[] args) {
		String cname = "org.apache.lucene.analysis.cn.smart.HMMChineseTokenizerFactory";
		String expectedType = "org.apache.lucene.analysis.util.TokenizerFactory";
		
		
		try {
			Class.forName(cname, true, Test.class.getClassLoader()).asSubclass(TokenizerFactory.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
