package encryptdecrypt

abstract class Encrypt(protected var key: Int, protected var message: String) {
    abstract fun encrypt(): String
}


internal class Shift(key: Int, message: String) : Encrypt(key, message) {
    override fun encrypt(): String {
        val sb = StringBuilder()
        for (element in message) {
            var newChar = element
            if (element.code in 65..90) {
                newChar = if (element.code + key < 90) {
                    (element.code + key).toChar()
                } else {
                    val tmpKey = (element.code + key % 26) % 90
                    (64 + tmpKey).toChar()
                }
            } else if (element.code in 97..122) {
                newChar = if (element.code + key < 123) {
                    (element.code + key).toChar()
                } else if (element.code + key % 26 < 123) {
                    (element.code + key % 26).toChar()
                } else {
                    val tmpKey = (element.code + key % 26) % 122
                    (96 + tmpKey).toChar()
                }
            }
            sb.append(newChar)
        }
        return sb.toString()
    }
}


internal class Unicode(key: Int, message: String) : Encrypt(key, message) {
    override fun encrypt(): String {
        val sb = StringBuilder()
        for (element in message) {
            val newChar = (element.code + key).toChar()
            sb.append(newChar)
        }
        return sb.toString()
    }
}
