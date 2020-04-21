package excel

import java.util.LinkedHashMap

interface DataSource {
    val data: List<LinkedHashMap<String, Any>>
}