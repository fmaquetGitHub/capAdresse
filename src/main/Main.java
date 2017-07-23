package main;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Main {

	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		registerContexts(server);
		server.setExecutor(null);
		server.start();
	}

	private static void registerContexts(HttpServer server) {
		server.createContext("/test", new MyHandler());
	}

}
