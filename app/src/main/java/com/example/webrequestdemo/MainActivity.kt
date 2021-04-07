package com.example.webrequestdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tfResult = findViewById<TextView>(R.id.testField)

        val getBtn = findViewById<Button>(R.id.getBtn)
        getBtn.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonArrayRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getAll",
                null,
                Response.Listener {
                        response ->  try
                        {
                            var nameList: StringBuffer = StringBuffer()

                            for(i in 0 until response.length()){
                                val objStudent: JSONObject = response.getJSONObject(i)
                                nameList.append(objStudent.getString("name") + "\n")

                            }

                            tfResult.setText(nameList)

                        }
                catch(e:JSONException)
                {
                                    tfResult.setText(e.message)
                }
                }
                , Response.ErrorListener
                {
                    error ->  tfResult.setText(error.message)
                }
            )

            rq.add(objRequest)
        }

        val tfName = findViewById<TextView>(R.id.tfStuName)
        val studentIDTF = findViewById<TextView>(R.id.tfStuID)
        val searchBtn = findViewById<Button>(R.id.searchBtn)
        searchBtn.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonObjectRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getById?id=w222",
                null,
                Response.Listener {
                        response ->  try
                {
                    var name: String
                    val objStudent: JSONObject = response
                    name = objStudent.getString("name")
                    tfName.setText(name)

                }
                catch(e:JSONException)
                {
                    tfResult.setText(e.message)
                }
                }
                , Response.ErrorListener
                {
                        error ->  tfResult.setText(error.message)
                }
            )

            rq.add(objRequest)
        }

        val tfProgramme: TextView = findViewById(R.id.tfStuProgramme)
        val addBtn:Button = findViewById(R.id.addBtn)
        addBtn.setOnClickListener {

            val inputName:String = (findViewById<TextView>(R.id.tfStuName).text.toString())
            val inputID:String = (findViewById<TextView>(R.id.tfStuID).text.toString())
            val inputProgramme:String = (findViewById<TextView>(R.id.tfStuProgramme).text.toString())

            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonObjectRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/add?id=${inputID}&name=${inputName}&programme=${inputProgramme}",
                null,
                Response.Listener {
                        response ->  try
                {
                    tfResult.setText("student added!")

                }
                catch(e:JSONException)
                {
                    tfResult.setText(e.message)
                }
                }
                , Response.ErrorListener
                {
                        error ->  tfResult.setText(error.message)
                }
            )

            rq.add(objRequest)
        }

    }
}