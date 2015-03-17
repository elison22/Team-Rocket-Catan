package handler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import main.FileUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class SwaggerHandler {
	
	public abstract static class BaseFile implements HttpHandler {
		
		protected String rootPath;
		
		public BaseFile(String rootPath) {
			this.rootPath = rootPath;
		}
		
		protected String getRequestPath(HttpExchange exchange) {
			return exchange.getRequestURI().getPath().substring(1);
		}
		
		protected void sendFile(HttpExchange exchange, String filePath) throws IOException {
			try {
				byte[] response = FileUtils.readFile(filePath);
				ArrayList<String> mimeTypes = new ArrayList<String>();
				mimeTypes.add(FileUtils.getMimeType(filePath));
				
				exchange.getResponseHeaders().put("Contenttype", mimeTypes);
				exchange.sendResponseHeaders(200, response.length);
				
				OutputStream os = exchange.getResponseBody();
				os.write(response);
				os.close();
				
			} catch(IOException ioe) {
				exchange.sendResponseHeaders(404, 1);
				OutputStream os = exchange.getResponseBody();
				os.close();
				
				System.out.println("Couldn't find the file " + new File(filePath).getAbsolutePath());
			}
		}
	}
	
	public static class BasicFile extends BaseFile {
		
		public BasicFile(String rootPath) {
			super(rootPath);
		}
		
		public void handle(HttpExchange exchange) throws IOException {
			String filepath = this.rootPath + this.getRequestPath(exchange);
			this.sendFile(exchange, filepath);
		}
	}
	
	public static class JSONAppender extends BaseFile {
		
		public JSONAppender(String rootPath) {
			super(rootPath);
		}
		
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println(this.rootPath + "___" + this.getRequestPath(exchange));
			this.sendFile(exchange, this.rootPath + this.getRequestPath(exchange) + ".json");
		}
	}
	
}