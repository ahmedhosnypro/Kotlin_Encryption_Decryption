package encryptdecrypt

fun main() {
    val s = "we found a treasure!"
    val sb = StringBuilder()
    for (element in s) {
        var newChar = element
        if (element.code in 65..90) {
            val n = 90 - element.code
            newChar = if (n < 13) {
                (90 + n).toChar()
            } else (65 - (25 - n)).toChar()
        } else if (element.code in 97..122) {
            val n = 122 - element.code
            newChar = if (n < 13) {
                (97 + n).toChar()
            } else (122 - (25 - n)).toChar()
        }
        sb.append(newChar)
    }
    println(sb)
}