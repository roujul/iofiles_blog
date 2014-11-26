package com.code_roux.blog.read_file;

import java.io.File;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class ReadingWritingFileJava7Test {
	
	@Test
	public void testApp() throws Exception {
		File temp = File.createTempFile("tempfile", ".tmp"); 
		Assertions.assertThat(temp).exists();

		ReadingWritingFileJava7 io = new ReadingWritingFileJava7();
		List<String> list = io.readSmallFile(temp.getAbsolutePath());
		Assertions.assertThat(list).isEmpty();

		list.add("run 1");
		io.writeSmallTextFile(list, temp.getAbsolutePath());

		list.add("run 2");
		io.writeSmallTextFile(list, temp.getAbsolutePath());
		
		list = io.readSmallFile(temp.getAbsolutePath());
		Assertions.assertThat(list).hasSize(2);
	}
}
