package com.example.lab_6

class TaskGenerator() {
    private var answer: Int = 0
    private var query: String = ""
    private var difficulty = 0
    private var incDiff = false

    fun getAnswer(): Int {
        return answer
    }

    fun getQuery(): String {
        return query
    }

    fun getDifficulty(): Int {
        return difficulty
    }

    fun setDifficulty(diff: Int) {
        difficulty = diff
    }

    fun difficulty(enabled: Boolean) {
        incDiff = enabled
    }

    fun getAnswersList(): List<AnswerItem> {
        val list = ArrayList<AnswerItem>()

        // Заполняем неверными данными, мало отличающимися от правильного ответа
        while (list.size != 3) {
            var temp = 0
            while (temp == 0) {
                temp = ((-5..0).random()..(0..5).random()).random()
            }

            temp += answer

            val elem = AnswerItem(temp)

            if (!list.contains(elem) && temp != answer) {
                list.add(elem)
            }
        }

        // Теперь ставим верный ответ на рандомное место в массиве
        list[(0..2).random()] = AnswerItem(answer)

        return list
    }

    // Ввести ответ
    fun answer(value: Int): Boolean {
        if (value != answer) {
            return false
        }

        if (incDiff) {
            difficulty++
        }

        generateQuery()
        return true
    }

    fun generateQuery() {
        var a = 0
        var b = 0

        // Для увеличения сложности каждый раз расширяем диапазон возможных значений
        while (a == 0) {
            // Нуля быть не должно
            a = (-10 - difficulty..10 + difficulty).random()
        }

        while (b == 0) {
            // Нуля быть не должно и A должно нацело делиться на B
            b = (-10 - difficulty..10 + difficulty).random()
        }

        when ((0..2).random()) {
            0 -> {
                query = if (b > 0) {
                    "$a + $b"
                } else {
                    "$a + ($b)"
                }

                answer = a + b
            }
            1 -> {
                query = if (b > 0) {
                    "$a - $b"
                } else {
                    "$a - ($b)"
                }
                answer = a - b
            }
            2 -> {
                query = if (b > 0) {
                    "$a × $b"
                } else {
                    "$a × ($b)"
                }
                answer = a * b
            }
//            3 -> {
//                query = if (b > 0) {
//                    "$a ÷ $b"
//                } else {
//                    "$a ÷ ($b)"
//                }
//                answer = a / b
//            }
        }
    }

    init {
        generateQuery()
    }
}