package nl.pindab0ter.imagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import nl.pindab0ter.imagedemo.feature.R;

public class JavaImageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getString(R.string.image_java));
                    final InputStream inputStream = url.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    View parentView = getView();

                    if (parentView != null) {
                        final ImageView imageView = parentView.findViewById(R.id.image_view);
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

