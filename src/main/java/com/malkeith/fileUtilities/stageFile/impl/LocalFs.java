package com.malkeith.fileUtilities.stageFile.impl;

import com.malkeith.fileUtilities.stageFile.StageFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LocalFs extends StageFile {

    private final String tempDirectory;

    public LocalFs(String temp_directory) {
        tempDirectory = temp_directory;
    }

    @Override
    public File apply(byte[] buffer, int length) throws IOException {
        File outPutFile = File.createTempFile("temp-", "-split", new File(tempDirectory));
        try (FileOutputStream fos = new FileOutputStream(outPutFile)) {
            fos.write(buffer, 0, length);
        }
        return outPutFile;
    }
}
