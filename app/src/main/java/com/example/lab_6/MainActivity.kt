package com.example.lab_6

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CellClickListener {
    private var taskGenerator: TaskGenerator = TaskGenerator()
    private var success = 0
    private var unsuccess = 0

    @SuppressLint("SetTextI18n", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textTrue: TextView = findViewById(R.id.text_true)
        val textFalse: TextView = findViewById(R.id.text_false)
        val textDiff: TextView = findViewById(R.id.text_diff)

        val resetBtn: Button = findViewById(R.id.btn_reset)
        resetBtn.setOnClickListener {
            success = 0
            unsuccess = 0
            taskGenerator.setDifficulty(0)
            textTrue.text = success.toString()
            textFalse.text = unsuccess.toString()
            textDiff.text = taskGenerator.getDifficulty().toString()
            updateQuery()
        }

        val diffSwitch: Switch = findViewById(R.id.switch_diff)
        diffSwitch.setOnClickListener {
            //Toast.makeText(applicationContext, taskGenerator.getDifficulty(), Toast.LENGTH_SHORT).show()
            if (diffSwitch.isChecked){
                taskGenerator.difficulty(true)
            }else{
                taskGenerator.difficulty(false)
            }
        }

        updateQuery()
    }

    override fun onCellClickListener(data: AnswerItem) {
        if (taskGenerator.answer(data.answer)) {
            success++
            val textTrue: TextView = findViewById(R.id.text_true)
            textTrue.text = success.toString()
            updateQuery()
        } else {
            unsuccess++
            val textFalse: TextView = findViewById(R.id.text_false)
            textFalse.text = unsuccess.toString()
            Toast.makeText(this, "Неверно", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateQuery() {
        taskGenerator.generateQuery()

        // Отображение сложности
        val textDiff: TextView = findViewById(R.id.text_diff)
        textDiff.text = taskGenerator.getDifficulty().toString()

        // Запись выражения в textView
        val textTask: TextView = findViewById(R.id.text_task)
        textTask.text = taskGenerator.getQuery()

        // Запись вариантов ответа в список
        val listView: RecyclerView = findViewById(R.id.recycle_list_main)
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = CustomRecyclerAdapter(taskGenerator.getAnswersList(), this)
    }

}
