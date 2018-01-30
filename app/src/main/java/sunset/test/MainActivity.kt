package sunset.test

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(Canvas1(this))
        setContentView(PathHeartBezier(this))


//        setContentView(R.layout.activity_main)
//        perfect.setColor(Color.RED,Color.BLUE)
    }
}
