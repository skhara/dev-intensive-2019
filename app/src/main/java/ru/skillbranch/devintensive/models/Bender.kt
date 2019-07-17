package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = question.question

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        val (result, phrase) = question.validate(answer)

        if (question == Question.IDLE) {
            return question.question to status.color
        }

        if (result) {
            if (question.answers.contains(answer.toLowerCase())) {
                question = question.nextQuestion()

            } else {

                status = status.nextStatus()

                return if (status == Status.NORMAL) {
                    question = Question.NAME

                    "Это неправильный ответ. Давай все по новой\n" +
                            question.question to status.color
                } else {
                    "Это неправильный ответ\n" +
                            question.question to status.color
                }
            }
        }

        return "$phrase\n${question.question}" to status.color
    }

    enum class Status(val color: Triple<Int, Int, Int>) {

        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            if (this.ordinal < values().lastIndex) {
                return values()[this.ordinal + 1]
            } else {
                return values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {

        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer[0].isUpperCase()) {
                    return true to okMsg
                }

                return false to "Имя должно начинаться с заглавной буквы"
            }

            override fun nextQuestion(): Question {
                return PROFESSION
            }
        },

        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun validate(answer: String): Pair<Boolean, String> {
                if (!answer[0].isUpperCase()) {
                    return true to okMsg
                }

                return false to "Профессия должна начинаться со строчной буквы"
            }

            override fun nextQuestion(): Question {
                return MATERIAL
            }
        },

        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")) {
            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer.matches("^\\D*$".toRegex())) {
                    return true to okMsg
                }
                return false to "Материал не должен содержать цифр"
            }

            override fun nextQuestion(): Question {
                return BDAY
            }
        },

        BDAY("Когда меня создали?", listOf("2993")) {
            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer.matches("^\\d*$".toRegex())) {
                    return true to okMsg
                }

                return false to "Год моего рождения должен содержать только цифры"
            }

            override fun nextQuestion(): Question {
                return SERIAL
            }
        },

        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer.matches("[0-9]{7}".toRegex())) {
                    return true to okMsg
                }

                return false to "Серийный номер содержит только цифры, и их 7"
            }

            override fun nextQuestion(): Question {
                return IDLE
            }
        },

        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun validate(answer: String): Pair<Boolean, String> {
                return true to okMsg
            }

            override fun nextQuestion(): Question {
                return this
            }
        };

        val okMsg = "Отлично - ты справился"

        abstract fun validate(answer: String) : Pair<Boolean, String>

        abstract fun nextQuestion() : Question
    }
}