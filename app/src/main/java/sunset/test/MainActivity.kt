package sunset.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket
import java.net.UnknownHostException


class MainActivity : AppCompatActivity() {

    lateinit var  mThread:Thread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(Canvas1(this))
//        setContentView(PathHeartBezier(this))


        setContentView(R.layout.activity_main)
//        perfect.setColor(Color.RED,Color.BLUE)
        test.setOnClickListener {
            mThread= Thread(mRun)
            mThread.start()
            Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show()
        }
    }

    var mRun:Runnable= Runnable {

        try {
            println("Client：Connecting")
            //IP地址和端口号（对应服务端），我这的IP是本地路由器的IP地址
            val socket = Socket("192.168.1.9", 12345)
            //发送给服务端的消息
            val message = "收到我的消息没"
            try {
                println("Client Sending: '$message'")

                //第二个参数为True则为自动flush
                val out = PrintWriter(
                        BufferedWriter(OutputStreamWriter(
                                socket.getOutputStream())), true)
                out.println(message)
                //                      out.flush();
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                //关闭Socket
                socket.close()
                println("Client:Socket closed")
            }
        } catch (e1: UnknownHostException) {
            e1.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
