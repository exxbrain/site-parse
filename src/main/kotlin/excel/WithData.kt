package excel

import java.util.LinkedHashMap

interface WithData {
    fun getData(): LinkedHashMap<String, Any>
}