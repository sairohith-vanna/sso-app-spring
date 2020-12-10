package com.vanna.ssodemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.opencsv.CSVWriter;

@Service
public class CSVService {

	public byte[] writeSampleCSV() throws IOException
	{
		File tempCSVFile = File.createTempFile("sample", ".csv");
		CSVWriter csvWriter = new CSVWriter(new FileWriter(tempCSVFile));
		csvWriter.writeNext(new String[] {"username", "password"});
		csvWriter.writeNext(new String[] {"admin_1", "sy@7#npl_89"});
		csvWriter.close();
		return FileCopyUtils.copyToByteArray(tempCSVFile);
	}
}
