fun main(args: Array<String>) {
    println(buildArray(intArrayOf(1, 2), 4))
}

/**
1441. 用栈操作构建数组
给你一个数组 target 和一个整数 n。每次迭代，需要从  list = { 1 , 2 , 3 ..., n } 中依次读取一个数字。
请使用下述操作来构建目标数组 target ：
"Push"：从 list 中读取一个新元素， 并将其推入数组中。
"Pop"：删除数组中的最后一个元素。
如果目标数组构建完成，就停止读取更多元素。
题目数据保证目标数组严格递增，并且只包含 1 到 n 之间的数字。
请返回构建目标数组所用的操作序列。如果存在多个可行方案，返回任一即可。
 */
//private fun buildArray(target: IntArray, n: Int): List<String> {

//    val t = mutableListOf<Int>()
//    target.map { t.add(it) }
//    val result = ArrayList<String>()
//    for (i in 1 .. n) {
//        result.add("Push")
//        if (target.contains(i)) {
//            t.remove(i)
//            if (t.isEmpty()) return result
//        } else {
//            result.add("Pop")
//        }
//    }
//    return result


//}

private fun buildArray(target: IntArray, n: Int): List<String>? {
    val res: MutableList<String> = ArrayList()
    var prev = 0
    for (number in target) {
        for (i in 0 until number - prev - 1) {
            res.add("Push")
            res.add("Pop")
        }
        res.add("Push")
        prev = number
    }
    return res
}