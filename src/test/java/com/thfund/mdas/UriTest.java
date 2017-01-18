package com.thfund.mdas;

import java.net.URI;
import java.net.URISyntaxException;

public class UriTest {

	public static void main(String[] args) {
		try {
			URI uri = new URI("c:/foo");
			System.out.println(uri.getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
