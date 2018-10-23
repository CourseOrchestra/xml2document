package ru.curs.xml2document.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xml.sax.SAXException;
import ru.curs.xml2document.DOCXReportWriter;
import ru.curs.xml2document.OutputType;
import ru.curs.xml2document.XML2Document;
import ru.curs.xml2document.XML2WordError;
import java.io.File;


public class XML2DocTests {

    @Test
    void saxDataReaderTest() throws FileNotFoundException, IOException, XML2WordError {
        assertThrows(XML2WordError.class, () ->
        XML2Document.processUsingSax("filesForTesting/dataXML.xml", "filesForTesting/descriptorXML.xml",
                "filesForTesting/template.docx", "filesForTesting/resultForSAX.docx")
        );
    }

    @Test
    void resultFileExistenceCheck() throws FileNotFoundException, IOException, XML2WordError {
        XML2Document.process("filesForTesting/dataXML.xml", "filesForTesting/descriptorXML.xml",
                "filesForTesting/template.docx", "filesForTesting/resultTestFile.docx");
        File resultTestFile = new File("filesForTesting/resultTestFile.docx");
        assertTrue(resultTestFile.exists());
        assertTrue(resultTestFile.canRead());
    }

    @Test
    void resultFileExistenceCheck2() throws FileNotFoundException, IOException, XML2WordError {
        XML2Document.process(new FileInputStream("filesForTesting/dataXML.xml"), new FileInputStream("filesForTesting/descriptorXML.xml"),
                new FileInputStream("filesForTesting/template.docx"), new FileOutputStream("filesForTesting/resultTestFile2.docx"));
        File resultTestFile = new File("filesForTesting/resultTestFile2.docx");
        assertTrue(resultTestFile.exists());
        assertTrue(resultTestFile.canRead());
    }

    @Test
    void docResultFileImplementationError() throws FileNotFoundException, IOException, XML2WordError {
                XML2Document.process("filesForTesting/dataXML.xml", "filesForTesting/descriptorXML.xml",
                        "filesForTesting/template.docx", "filesForTesting/resultTestFile3.doc");
        File resultTestFile = new File("filesForTesting/resultTestFile3.docx");
        assertFalse(resultTestFile.canRead());
    }
}
