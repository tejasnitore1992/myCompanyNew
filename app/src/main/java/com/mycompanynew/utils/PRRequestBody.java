package com.mycompanynew.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class PRRequestBody extends RequestBody {
    private File mFile;
    private MediaType mediaType;
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private FileUploaderCallback fileUploaderCallback;

    public PRRequestBody(final File file, MediaType mediaType) {
        mFile = file;
        this.mediaType = mediaType;
    }

    @Override
    public MediaType contentType() {
        return mediaType;
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        long fileLength = mFile.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        FileInputStream in = new FileInputStream(mFile);
        long uploaded = 0;

        try {
            int read;
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = in.read(buffer)) != -1) {

                // update progress on UI thread
                handler.post(new ProgressUpdater(uploaded, fileLength));
                uploaded += read;
                sink.write(buffer, 0, read);
            }
        } finally {
            in.close();
        }
    }

    private class ProgressUpdater implements Runnable {
        private long mUploaded;
        private long mTotal;
        public ProgressUpdater(long uploaded, long total) {
            mUploaded = uploaded;
            mTotal = total;
        }

        @Override
        public void run() {
            int current_percent = (int)(100 * mUploaded / mTotal);
            fileUploaderCallback.onProgressUpdate(current_percent, 100 );
        }
    }

    public void setFileUploaderCallback(FileUploaderCallback fileUploaderCallback){
        this.fileUploaderCallback = fileUploaderCallback;
    }

    public interface FileUploaderCallback{
        void onProgressUpdate(int currentpercent, int totalpercent);
    }
}
