package hdli.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Hello world!
 *
 */
public class Indexer {
	private IndexWriter writer;
	
	public static void main(String[] args) throws IOException {
//		if(args.length != 2) {
//			throw new IllegalArgumentException("Usage: java " + Indexer.class.getName() + " <index dir> <data dir>");
//		}
		String indexDir = "E:\\research\\lucene-indexs";
		String dataDir = "E:\\research\\lucene-src";
		
		long start = System.currentTimeMillis();
		Indexer indexer = new Indexer(indexDir);
		int numIndexed = 0;
		try {
			numIndexed = indexer.index(dataDir, new TextFilesFilter());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			indexer.close();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Indexing " + numIndexed + " files took " + (end -start) + " milliseconds");
	}
	
	public Indexer(String indexDir) throws IOException {
		Directory dir = FSDirectory.open(new File(indexDir).toPath());
		IndexWriterConfig conf = new IndexWriterConfig();
		writer = new IndexWriter(dir, conf);
	}
	
	public void close() throws IOException {
		writer.close();
	}
	
	public int index(String dataDir, FileFilter filter) throws IOException {
		File[] files = new File(dataDir).listFiles();
		for(File f: files) {
			if(!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead() && (filter == null || filter.accept(f))) {
				indexFile(f);
			}
		}
		return writer.numDocs();
	}
	
	private static class TextFilesFilter implements FileFilter{
		public boolean accept(File path) {
			return path.getName().toLowerCase().endsWith(".txt");
		}
	}
	
	protected Document getDocument(File f) throws IOException{
		Document doc = new Document();
		
		FieldType contentsFieldType = new FieldType();
		contentsFieldType.setStored(false);
		contentsFieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
		contentsFieldType.setTokenized(true);
		doc.add(new Field("contents", new FileReader(f),  contentsFieldType));
		
		FieldType filenameFieldType = new FieldType();
		filenameFieldType.setStored(true);
		filenameFieldType.setIndexOptions(IndexOptions.NONE);
		filenameFieldType.setTokenized(true);
		doc.add(new Field("filename", f.getName(), filenameFieldType));
		
		FieldType pathFieldType = new FieldType();
		pathFieldType.setStored(true);
		pathFieldType.setIndexOptions(IndexOptions.NONE);
		pathFieldType.setTokenized(true);
		doc.add(new Field("fullpath", f.getCanonicalPath(), filenameFieldType));
		return doc;
	}
    
    private void indexFile(File f) throws IOException {
    	System.out.println("Indexing " + f.getCanonicalPath());
    	Document doc = getDocument(f);
    	writer.addDocument(doc);
    }
}
