package leetcode.d220730.q916

class Solution {
//    fun wordSubsets(words1: Array<String>, words2: Array<String>): List<String> {
//        val result = mutableListOf<String>()
//        val map = HashMap<Int, HashMap<String, Int>>()
//        for (i: Int in words2.indices) {
//            map[i] = HashMap()
//            var sp = words2[i].split("")
//            for (v in sp) {
//                map[i]!![v] = map[i]!!.getOrDefault(v, 0) + 1
//            }
//            map[i]!!.remove("")
//        }
//
//        for (u in words1) {
//            var temp = HashMap<String, Int>(map)
//
//            for (k in map.keys) {
//                if (temp.size == 0) {
//                    result.add(u)
//                    break
//                }
//                if (u.contains(k)) {
//                    temp[k] = temp[k]!! - 1
//                    if (temp[k] == 0) temp.remove(k)
//                }
//            }
//            if (temp.size == 0) {
//                result.add(u)
//            }
//        }
//        return result
//    }
}

fun main() {

    val words1 = arrayOf("amazon", "apple", "facebook", "google", "leetcode")
    val words2 = arrayOf("e", "oo")
    val sol = Solution();
//    println(sol.wordSubsets(words1, words2).toString())

}