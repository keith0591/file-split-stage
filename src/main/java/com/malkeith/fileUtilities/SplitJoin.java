package com.malkeith.fileUtilities;

import com.malkeith.fileUtilities.stageFile.StageFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SplitJoin {

    private final StageFile stageFile;

    public SplitJoin(StageFile stageFile) {
        this.stageFile = stageFile;
    }


    /**
     * Splits the given file into smaller files of specified size
     * @param largeFile the large file
     * @param maxChunkSize the size of the smaller files
     * @return list of chunked files
     * @throws IOException for file related errors
     */
    public List<File> splitBySize(File largeFile, int maxChunkSize) throws IOException {
        List<File> list = new ArrayList<>();
        try (InputStream in = Files.newInputStream(largeFile.toPath())) {
            final byte[] buffer = new byte[maxChunkSize];
            int dataRead = in.read(buffer);
            while (dataRead > -1) {
                File fileChunk = stageFile.apply(buffer, dataRead);
                list.add(fileChunk);
                dataRead = in.read(buffer);
            }
        }
        return list;
    }

    /**
     * Splits the given file into specified number of smaller files
     * @param largeFile the large file
     * @param noOfFiles the number of files
     * @return list of chunked files
     * @throws IOException for file related errors
     */
    public List<File> splitByNumberOfFiles(File largeFile, int noOfFiles) throws IOException {
        return splitBySize(largeFile, Commons.getSizeInBytes(largeFile.length(), noOfFiles));
    }

    /**
     * Combines the given files in the order specified by the list
     * @param list the list of files to combine
     * @return a large file
     * @throws IOException for file related errors
     */
    public File join(List<File> list) throws IOException {
        File outPutFile = File.createTempFile("temp-", "-unsplit");
        FileOutputStream fos = new FileOutputStream(outPutFile);
        for (File file : list) {
            Files.copy(file.toPath(), fos);
        }
        fos.close();
        return outPutFile;
    }

}
