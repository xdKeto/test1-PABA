package paba.example.tes1_rework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class addMoney : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_money)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val _inputMoney = findViewById<EditText>(R.id.inputMoney)
        val _addButton = findViewById<Button>(R.id.addButton)
        val _backButton = findViewById<Button>(R.id.backButton)

        val goalID = intent.getStringExtra("goalID")
        _addButton.setOnClickListener {
            var input = _inputMoney.text.toString().toLongOrNull() ?: 0
            var oldGoal =  when (goalID) {
                "1" -> intent.getStringExtra("goalMoney1").toString().toLong()
                "2" -> intent.getStringExtra("goalMoney2").toString().toLong()
                else -> 0
            }

            var total = oldGoal + input

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("newGoal1", if (goalID == "1") total.toString() else MainActivity.goal1.goalAmount)
                putExtra("newGoal2", if (goalID == "2") total.toString() else MainActivity.goal2.goalAmount)
            }

            startActivity(intent)
            finish()
        }

        _backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}