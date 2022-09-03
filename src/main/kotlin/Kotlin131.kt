const val MINUTE = 60
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val STARTS_WITH = "Был(а) в сети"

fun main() {
    println(agoToText(1))
    println(agoToText(120))
    println(agoToText(360))
    println(agoToText(7200))
    println(agoToText(13000))
    println(agoToText(18000))
    println(agoToText(90000))
    println(agoToText(180000))
}


fun agoToText(seconds: Int): String = STARTS_WITH + when (seconds) {
    in 0..MINUTE -> " только что"
    in (MINUTE + 1)..HOUR -> {
        val minutes: Int = seconds / 60
        getRightForm(isItMinutes = true, time = minutes)
    }
    in HOUR + 1..DAY -> {
        val hours: Int = seconds / 3600
        getRightForm(isItHours = true, time = hours)
    }
    in (DAY + 1)..(2 * DAY) -> " вчера"
    in (2 * DAY + 1)..(3 * DAY) -> " позавчера"
    in (3 * DAY + 1)..Int.MAX_VALUE -> " давно"
    else -> " TimeFormatError"
}

fun getRightForm(isItMinutes: Boolean = false, isItHours: Boolean = false, time: Int): String {
    var result: String = ""
    if (isItMinutes) {
        when (time % 10) {
            1 -> result = (if (time.toString()
                    .endsWith("11")
            ) " $time минут назад" else " $time минуту назад")
            in 2..4 -> result = " $time минуты назад"
            0, in 5..9 -> result = " $time минут назад"
        }
    }
    if (isItHours) {
        //тут явно не хватает тернарного оператора из Джавы, так бы одним свитчем обошелся
        when (time % 10) {
            1 -> result = (if (time.toString()
                    .endsWith("11")
            ) " $time часов назад" else " $time час назад")
            in 2..4 -> result = " $time часа назад"
            0, in 5..9 -> result = " $time часов назад"
        }
    }
    return result
}



