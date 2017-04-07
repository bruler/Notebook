object DijkstraKT {
    data class Edge(val target: Int, val cost: Int)

    data class ShortestPaths(val dist: IntArray, val pred: IntArray)

    fun shortestPaths(graph: Array<out List<Edge>>, s: Int): ShortestPaths {
        val n = graph.size
        val dist = IntArray(n, { Integer.MAX_VALUE })
        dist[s] = 0
        val pred = IntArray(n, { -1 })
        val visited = BooleanArray(n)
        for (i in 0..n - 1) {
            var u = -1
            for (j in 0..n - 1) {
                if (!visited[j] && (u == -1 || dist[u] > dist[j]))
                    u = j
            }
            if (dist[u] == Integer.MAX_VALUE)
                break
            visited[u] = true

            for (e in graph[u]) {
                val v = e.target
                val nprio = dist[u] + e.cost
                if (dist[v] > nprio) {
                    dist[v] = nprio
                    pred[v] = u
                }
            }
        }
        return ShortestPaths(dist, pred)
    }

    // Usage example
    @JvmStatic fun main(args: Array<String>) {
        val cost = arrayOf(intArrayOf(0, 3, 2), intArrayOf(0, 0, -2), intArrayOf(0, 0, 0))
        val n = cost.size
        val graph = (1..n).map { mutableListOf<Edge>() }.toTypedArray()
        for (i in 0..n - 1) {
            for (j in 0..n - 1) {
                if (cost[i][j] != 0) {
                    graph[i].add(Edge(j, cost[i][j]))
                }
            }
        }
        println(graph)
        val (dist, pred) = shortestPaths(graph, 0)
        println(0 == dist[0])
        println(3 == dist[1])
        println(1 == dist[2])
        println(-1 == pred[0])
        println(0 == pred[1])
        println(1 == pred[2])
    }
}