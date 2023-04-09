package encryptdecrypt

object Encrypt {
    fun simpleEncrypt() {
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

    fun letterKeyEncrypt(s: String, inKey: Int) {
        var key = inKey
        val sb = StringBuilder()
        for (element in s) {
            var newChar = element
            if (element.code in 65..90) {
                if (element.code + key < 90) {
                    newChar = (element.code + key).toChar()
                } else {
                    key -= 90 - element.code
                    newChar = (64 + key).toChar()
                }
            } else if (element.code in 97..122) {
                newChar = if (element.code + key < 122) {
                    (element.code + key).toChar()
                } else {
                    val tmpKey = key - (122 - element.code)
                    (96 + tmpKey).toChar()
                }
            }
            sb.append(newChar)
        }
        println(sb)
    }

    fun allKeyEncrypt(message: String, key: Int) {
        val sb = java.lang.StringBuilder()
        for (element in message) {
            val newChar = (element.code + key).toChar()
            sb.append(newChar)
        }
        println(sb)
    }
}