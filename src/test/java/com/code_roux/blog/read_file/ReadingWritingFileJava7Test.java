package com.code_roux.blog.read_file;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ReadingWritingFileJava7Test {
	
	private ReadingWritingFileJava7 io;
	private File temp;
	
	@Before
	public void setup() throws Exception {
		io = new ReadingWritingFileJava7();
		temp = File.createTempFile("tempfile", ".tmp"); 
		Assertions.assertThat(temp).exists();
	}
	
	@Test
	public void testReadingSmallFile() throws Exception {
		URL resource = getClass().getResource("test.txt");
		Assertions.assertThat(resource).isNotNull();
		String path = Paths.get(resource.toURI()).toString();
		List<String> list = io.readSmallFile(path);
		Assertions.assertThat(list).isNotEmpty();
	}
	
	@Test
	public void testWritingSmallFile() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("run 1");
		io.writeSmallFile(list, temp.getAbsolutePath());

		list.add("run 2");
		io.writeSmallFile(list, temp.getAbsolutePath());
		
		list = io.readSmallFile(temp.getAbsolutePath());
		Assertions.assertThat(list).hasSize(2);
	}

	@Test
	public void testLoadWritingLargeFile() throws Exception {
		File temp = File.createTempFile("tempfile", ".tmp"); 
		Assertions.assertThat(temp).exists();

		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 5000000; i++) {
			list.add("1324567890132456789013245678901324567890132456789013245678901324567890132456789013245678901324567890" + i);
		}
		
		io.writeSmallFile(list, temp.getAbsolutePath());
		List<String> read = io.readLargeFile(temp.getAbsolutePath());
		Assertions.assertThat(read).hasSize(5000000);
	}
	

	@Test
	public void testReadingWritingLargeFile() throws Exception {
		File temp = File.createTempFile("tempfile", ".tmp"); 
		Assertions.assertThat(temp).exists();

		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 5000000; i++) {
			list.add("1324567890132456789013245678901324567890132456789013245678901324567890132456789013245678901324567890" + i);
		}
		
		io.writeLargeTextFile(temp.getAbsolutePath(), list);
		List<String> read = io.readLargeFile(temp.getAbsolutePath());
		Assertions.assertThat(read).hasSize(5000000);
	}
}
