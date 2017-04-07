object DisjointSetsKT {

    fun root(p: IntArray, x: Int): Int {
        if (x == p[x]) {
            return x
        } else {
            p[x] = root(p, p[x])
            return p[x]
        }
    }

    fun unite(p: IntArray, a: Int, b: Int): Boolean {
        val ra = root(p, a)
        val rb = root(p, b)
        if (ra != rb) {
            p[ra] = rb
            return true
        }
        return false
    }

    fun createSets(size: Int): IntArray = IntArray(size, { i -> i })

    // Usage example
    @JvmStatic fun main(args: Array<String>) {
        val p = createSets(10)
        println(false == (root(p, 0) == root(p, 9)))
        unite(p, 0, 9)
        println(true == (root(p, 0) == root(p, 9)))
    }
}
