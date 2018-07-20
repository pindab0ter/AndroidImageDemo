package nl.pindab0ter.imagedemo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_image.*
import nl.pindab0ter.imagedemo.feature.R
import java.net.URL
import kotlin.concurrent.thread

class KotlinImageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thread {
            val imageStream = URL("https://hansvl.nl/images/kotlin.png").openStream()
            val bitmap = BitmapFactory.decodeStream(imageStream)
            this@KotlinImageFragment.activity?.runOnUiThread {
                image_view.setImageBitmap(bitmap)
            }
        }

    }
}
