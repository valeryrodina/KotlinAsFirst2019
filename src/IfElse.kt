@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt
import kotlin.math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val edinitsy = age - age / 10 * 10
    return when {
        (age in 5..20) || (age in 105..120) -> "$age лет"
        (edinitsy in 5..9) || (edinitsy == 0) -> "$age лет"
        edinitsy == 1 -> "$age год"
        else -> "$age года"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s_half = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    return when {
        (s_half < t1 * v1) -> s_half / v1
        (s_half == t1 * v1) -> t1
        ((s_half > t1 * v1) && (s_half < t1 * v1 + t2 * v2)) -> (s_half - v1 * t1) / v2 + t1
        (s_half == t1 * v1 + t2 * v2) -> t1 + t2
        (s_half > (t1 * v1 + t2 * v2)) -> s_half / v3 - (t1 * v1) / v3 - (t2 * v2) / v3 + t1 + t2
        else -> 1.0
    }

}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int = TODO()

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int = TODO()

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    return when {
        ((acos((sqr(a) + sqr(c) - sqr(b)) / (2 * a * c)) / PI * 180.0) < 90.0) &&
                ((acos((sqr(a) + sqr(b) - sqr(c)) / (2 * a * b)) / PI * 180) < 90.0) &&
                ((acos((sqr(b) + sqr(c) - sqr(a)) / (2 * b * c)) / PI * 180) < 90.0) -> 0

        ((acos((sqr(a) + sqr(c) - sqr(b)) / (2 * a * c)) / PI * 180.0) == 90.0) ||
                ((acos((sqr(a) + sqr(b) - sqr(c)) / (2 * a * b)) / PI * 180) == 90.0) ||
                ((acos((sqr(b) + sqr(c) - sqr(a)) / (2 * b * c)) / PI * 180) == 90.0) -> 1

        ((acos((sqr(a) + sqr(c) - sqr(b)) / (2 * a * c)) / PI * 180.0) > 90.0) ||
                ((acos((sqr(a) + sqr(b) - sqr(c)) / (2 * a * b)) / PI * 180) > 90.0) ||
                ((acos((sqr(b) + sqr(c) - sqr(a)) / (2 * b * c)) / PI * 180) > 90.0) -> 2

        else -> -1
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        ((a == c) && (b == d)) -> d - c
        (b == c) -> 0
        ((a < c) && (b < d) && (c < b)) -> b - c
        ((a > c) && (b > d) && (a < d)) -> d - a
        ((a < c) && (b > d)) -> d - c
        ((c < a) && (d > b)) -> b - a
        ((a == b) && (b == c)) -> d - a
        ((a == b) && (c < a) && (d > b)) -> 0
        ((a == b) && (b == d)) -> 0
        ((b == c) && (c == d)) -> b - a
        ((c == d) && (a < c) && (b > d)) -> 0
        ((a == c) && (c == d)) -> 0
        ((a == c) && (b > d)) -> d - a
        ((a == c) && (b < d)) -> b - a
        ((a == d) && (c < a) && (b > d)) -> 0
        ((a == c) && (b == d)) -> d - c
        ((b == d) && (a < c)) -> d - c
        ((b == d) && (a > c)) -> b - a
        else -> -1
    }
}