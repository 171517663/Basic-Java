package lucene.tokenstream;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class Test {
    public static void main(String[] args) throws IOException {
        //将一个字符串创建成token流,第一个参数---fiedName,是一种标志性参数,可以写空字符串,不建议用null,因为null对于IKAnalyzer会包错
        TokenStream tokenStream = new IKAnalyzer().tokenStream("keywords",new StringReader("思想者,你好再见，hello"));
        //添加单词信息到AttributeSource的map中
        CharTermAttribute attribute = tokenStream.addAttribute(CharTermAttribute.class);
        //重置,设置tokenstream的初始信息
        tokenStream.reset();
        while(tokenStream.incrementToken()) {//判断是否还有下一个Token
            System.out.println(attribute);
        }
        tokenStream.end();
        tokenStream.close();
    }
}
