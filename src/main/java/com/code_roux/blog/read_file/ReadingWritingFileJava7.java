package com.code_roux.blog.read_file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadingWritingFileJava7 {

	public List<String> readSmallFile(final String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllLines(path, StandardCharsets.UTF_8);
	}

	public void writeSmallFile(final List<String> lines, final String fileName)
			throws IOException {
		Path path = Paths.get(fileName);
		Files.write(path, lines, StandardCharsets.UTF_8);
	}

	public List<String> readLargeFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		List<String> lines = new ArrayList<String>();
		try (BufferedReader reader = Files.newBufferedReader(path,
				StandardCharsets.UTF_8)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		}
		return lines;
	}

	public void writeLargeTextFile(String aFileName, List<String> aLines)
			throws IOException {
		Path path = Paths.get(aFileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path,
				StandardCharsets.UTF_8)) {
			for (String line : aLines) {
				writer.write(line);
				writer.newLine();
			}
		}
	}
}
