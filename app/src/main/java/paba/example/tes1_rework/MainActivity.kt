package paba.example.tes1_rework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val _totalMoney = findViewById<TextView>(R.id.totalMoney)
        val _goalMoney1 = findViewById<TextView>(R.id.goalMoney1)
        val _goalMoney2 = findViewById<TextView>(R.id.goalMoney2)

        val _addMoney1 = findViewById<Button>(R.id.addMoney1)
        val _addMoney2 = findViewById<Button>(R.id.addMoney2)

//        GOAL 1
        _addMoney1.setOnClickListener {
            val intent = Intent(this@MainActivity, addMoney::class.java).apply {
                putExtra("goalMoney1", goal1.goalAmount.toString())
                putExtra("goalID", "1")
            }

            startActivity(intent)
        }
        val getGoalMoney1 = intent.getStringExtra("newGoal1")?.toLongOrNull() ?: goal1.goalAmount

        goal1.goalAmount = getGoalMoney1
        _goalMoney1.text = goal1.goalAmount.toString()

//        GOAL 2
        _addMoney2.setOnClickListener {
            val intent = Intent(this@MainActivity, addMoney::class.java).apply {
                putExtra("goalMoney2", goal2.goalAmount.toString())
                putExtra("goalID", "2")
            }

            startActivity(intent)
        }
        val getGoalMoney2 = intent.getStringExtra("newGoal2")?.toLongOrNull() ?: goal2.goalAmount
        goal2.goalAmount = getGoalMoney2
        _goalMoney2.text = goal2.goalAmount.toString()


//        total money
        val goalAmount1 = goal1.goalAmount
        val goalAmount2 = goal2.goalAmount
        val total = goalAmount1 + goalAmount2
        _totalMoney.text = total.toString()

    }

    companion object {
        var goal1 : Goals = Goals(1000, "1")
        var goal2 : Goals = Goals(1000, "1")
    }
}