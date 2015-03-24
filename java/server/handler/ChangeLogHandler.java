package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import facade.IModelFacade;

public class ChangeLogHandler implements HttpHandler {
	
	private IModelFacade modelFacade;
	private String logLevel;
	private ArrayList<String> legalLevels;
	
	public ChangeLogHandler(IModelFacade modelFacade) {
		super();
		this.modelFacade = modelFacade;
		logLevel = "ALL";
		legalLevels = new ArrayList<String>();
		legalLevels.add("ALL");
		legalLevels.add("SEVERE");
		legalLevels.add("WARNING");
		legalLevels.add("INFO");
		legalLevels.add("CONFIG");
		legalLevels.add("FINE");
		legalLevels.add("FINER");
		legalLevels.add("FINEST");
		legalLevels.add("OFF");
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
		StringBuilder stringBuilder = new StringBuilder();
		
		String request;
		while ((request = bufferedReader.readLine()) != null)
			stringBuilder.append(request);
		
		String[] strings = stringBuilder.toString().split(":");
		String level = strings[1].substring(2, strings[1].length() - 2);
		level = level.toUpperCase();
		
		boolean isCorrect = false;
		for(int i = 0; i < legalLevels.size(); i++) {
			if(level.equalsIgnoreCase(legalLevels.get(i)))
				isCorrect = true;
		}
		
		Headers head = exchange.getResponseHeaders();
		head.set("Content-Type", "text/html");
		
		if(isCorrect) {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			isCorrect = false;
			System.out.println(logLevel + ": " + "Log level being updated from " + logLevel + " to " + level);
			logLevel = level;
	
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
			os.write("Success");
			os.close();
		} else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			
			OutputStreamWriter os = new OutputStreamWriter(exchange.getResponseBody());
			os.write("Invalid log level: " + level);
			os.close();
		}
		exchange.close();
	}

}
