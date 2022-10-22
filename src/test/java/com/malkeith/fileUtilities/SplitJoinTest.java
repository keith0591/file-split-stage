package com.malkeith.fileUtilities;


import com.malkeith.fileUtilities.stageFile.impl.LocalFs;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;

public class SplitJoinTest {

    File input = new File("C:\\Users\\kthsi\\Downloads\\SampleCSVFile_5300kb.csv");
    private SplitJoin splitJoin = null;

    @Before
    public void before() {
        splitJoin = new SplitJoin(new LocalFs("D:\\temp\\"));
    }

    @Test
    public void testSplitBySize() throws IOException {
        File outPut1 = splitJoin.join(splitJoin.splitBySize(input, 1024_000));
        try (InputStream in = Files.newInputStream(input.toPath()); InputStream out = Files.newInputStream(outPut1.toPath())) {
            assertTrue(IOUtils.contentEquals(in, out));
        }
    }

    @Test
    public void testSplitByNo() throws IOException {
        File outPut2 = splitJoin.join(splitJoin.splitByNumberOfFiles(input, 17));
        try (InputStream in = Files.newInputStream(input.toPath()); InputStream out = Files.newInputStream(outPut2.toPath())) {
            assertTrue(IOUtils.contentEquals(in, out));
        }
    }
}
