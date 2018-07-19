package nl.pindab0ter.imagedemo.feature;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class JavaImageFragment extends Fragment implements DownloadImageListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new DownloadImageTask(this).execute("https://hansvl.nl/images/java.png");
    }

    @Override
    public void onImageDownloaded(Bitmap bitmap) {
        ImageView imageView = getView().findViewById(R.id.image);

        imageView.setImageBitmap(bitmap);
    }
}

interface DownloadImageListener {
    void onImageDownloaded(Bitmap bitmap);
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private DownloadImageListener listener;

    DownloadImageTask(DownloadImageListener listener) {
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        final String url = urls[0];
        Bitmap bitmap = null;

        try {
            final InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (final IOException ioException) {
            // Handle error
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        listener.onImageDownloaded(bitmap);
    }
}
