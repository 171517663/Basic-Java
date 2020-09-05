package lucene.search2;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

public class SearchTest {
    public static void serch() throws IOException, ParseException {
        //创建索引查询对象
        DirectoryReader indexReader =
                DirectoryReader.open(FSDirectory.open(new File("D:\\open").toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //此处使用的分词器一样要和写入索引使用的分词器保持一致
        Analyzer analyzer = new StandardAnalyzer();
        //查询解析器
        QueryParser parser = new QueryParser("content", analyzer);
        Query query = parser.parse("中国");
        //查询
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);

        //返回当前文档集合
        ScoreDoc[] docs = topDocs.scoreDocs;
        //返回的总数量
        TotalHits totalHits = topDocs.totalHits;

        for (ScoreDoc doc : docs) {
            // 此处的id值是lucene自己维护的id值
            int docId = doc.doc;
            //当前这个文档的命中率
            float score = doc.score;
            //根据给定的id  查询对应的文档内容
            Document document = indexSearcher.doc(docId);
            String id = document.get("id");
            String content = document.get("content");
            String title = document.get("title");
            System.out.println("索引ID:" + docId + ",命中率:" + score);
            System.out.println("id:" + id + ",content:" + content + ",title:" + title);
        }
    }
}
