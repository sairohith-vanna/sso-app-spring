package com.vanna.ssodemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.opencsv.CSVWriter;

@Service
public class CSVService {

	/**
	 * Writes some sample data to a temporary csv file.
	 * Extracts the bytes from the generated file, and deletes the temporary file.
	 * 
	 * @return The bytes of the csv file
	 * 
	 * @throws IOException
	 */
	public byte[] writeSampleCSV() throws IOException
	{
		File tempCSVFile = File.createTempFile("sample", ".csv");
		CSVWriter csvWriter = new CSVWriter(new FileWriter(tempCSVFile));
		csvWriter.writeNext(new String[] {"username", "password"});
		csvWriter.writeNext(new String[] {"admin_1", "sy@7#npl_89"});
		csvWriter.close();
		byte[] csvBytes = FileCopyUtils.copyToByteArray(tempCSVFile);
		tempCSVFile.delete();
		return csvBytes;
	}
}
