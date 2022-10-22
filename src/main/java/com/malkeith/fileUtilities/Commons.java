package com.malkeith.fileUtilities;

public class Commons {

    /**
     * Returns the size in bytes of the smaller file
     * @param totalBytes total bytes
     * @param numberOfFiles number of small files
     * @return Returns the size in bytes of the smaller file
     * @throws IllegalStateException if the byte chunk is too large
     */
    public static int getSizeInBytes(long totalBytes, int numberOfFiles) {
        if (totalBytes % numberOfFiles != 0) {
            totalBytes = ((totalBytes / numberOfFiles) + 1) * numberOfFiles;
        }
        long x = totalBytes / numberOfFiles;
        if (x > Integer.MAX_VALUE) {
            throw new IllegalStateException("Byte chunk too large");

        }
        return (int) x;
    }
}
