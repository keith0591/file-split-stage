package com.malkeith.fileUtilities.stageFile;

import java.io.File;
import java.io.IOException;

public abstract class StageFile {

    public abstract File apply(byte[] buffer, int length) throws IOException;
}
