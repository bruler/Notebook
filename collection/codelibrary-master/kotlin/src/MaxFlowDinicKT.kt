import java.util.*

object MaxFlowDinicKT {

    class Edge(val t: Int, val rev: Int, val cap: Int, var f: Int = 0)

    fun addEdge(graph: Array<MutableList<Edge>>, s: Int, t: Int, cap: Int) {
        graph[s].add(Edge(t, graph[t].size, cap))
        graph[t].add(Edge(s, graph[s].size - 1, 0))
    }

    fun dinicBfs(graph: Array<out List<Edge>>, src: Int, dest: Int, dist: IntArray): Boolean {
        Arrays.fill(dist, -1)
        dist[src] = 0
        val Q = IntArray(graph.size)
        var sizeQ = 0
        Q[sizeQ++] = src
        for (i in 0..sizeQ - 1) {
            val u = Q[i]
            for (e in graph[u]) {
                if (dist[e.t] < 0 && e.f < e.cap) {
                    dist[e.t] = dist[u] + 1
                    Q[sizeQ++] = e.t
                }
            }
        }
        return dist[dest] >= 0
    }

    fun dinicDfs(graph: Array<out List<Edge>>, ptr: IntArray, dist: IntArray, dest: Int, u: Int, f: Int): Int {
        if (u == dest)
            return f
        while (ptr[u] < graph[u].size) {
            val e = graph[u][ptr[u]]
            if (dist[e.t] == dist[u] + 1 && e.f < e.cap) {
                val df = dinicDfs(graph, ptr, dist, dest, e.t, Math.min(f, e.cap - e.f))
                if (df > 0) {
                    e.f += df
                    graph[e.t][e.rev].f -= df
                    return df
                }
            }
            ++ptr[u]
        }
        return 0
    }

    fun maxFlow(graph: Array<out List<Edge>>, src: Int, dest: Int): Int {
        var flow = 0
        val dist = IntArray(graph.size)
        while (dinicBfs(graph, src, dest, dist)) {
            val ptr = IntArray(graph.size)
            while (true) {
                val df = dinicDfs(graph, ptr, dist, dest, src, Integer.MAX_VALUE)
                if (df == 0)
                    break
                flow += df
            }
        }
        return flow
    }

    // Usage example
    @JvmStatic fun main(args: Array<String>) {
        val graph = (1..3).map { mutableListOf<Edge>() }.toTypedArray()
        addEdge(graph, 0, 1, 3)
        addEdge(graph, 0, 2, 2)
        addEdge(graph, 1, 2, 2)
        println(4 == maxFlow(graph, 0, 2))
    }
}