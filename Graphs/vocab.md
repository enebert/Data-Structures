A graph $G$ is a pair $(V, E)$ where $V$ is a finite set called the *vertex set* and $E$ is the *edge set*.
If $(u,v)$ is an edge in a directed graph we say that $(u,v)$ is *incident from* $u$ and *incident to* $v$.
One can think of these in terms of two functions $t:E \rightarrow V$ and $h:E \rightarrow V$ given an edge which
pick out the *tail* and the *head* of the edge respectively.

We say that two vertices are *adjacent* if there is an edge $(u,v)$. This relation is symmetric when $G$ is
undirected.

The *degree* of a vertex in an undirected graph is the number of edges incident to it. In a directed graph
we talk of the *in-degree* and *out-degree* of a vertex. The *total degree* of a vertex is the some of its
in-degree and out-degree.

A *path* of length $k$ from a vertex $u$ to a vertex $u'$ is a sequence $\{v_0, v_1, \ldots , v_k\}$ such that
$u=v_0, u'=v_k$ and $(v_{i-1}, v_i)$ is an edge for $i=1, 2, \ldots , k$.

If there is a path $p$ from $u$ to $u'$ we say that $u'$ is *reachable* from $u$ via $p$. A path is said to
be *simple* if the edges in the path are all distinct.

In a directed graph, a path $\langle v_0, v_1, \ldots , v_k \rangle$ forms a cycle if $v_0=v_k$ and the path
contains at least one edge. A *self-loop* is a cycle of length 1.

A directed graph with no self-loops is *simple*. A graph with no simple cycles is said to be acyclic.

An undirected graph is *connected* if each vertex is reachable from every other vertex. The *connected components*
of a graph form equivalence classes of vertices under the "is reachable from" relation.

A directed graph is *strongly connected* if every two vertices are reachable from each other.

**Graph Isomorphism:** Let $G=(V,E)$ and $G'=(V',E')$ be two graphs.

The two graphs are said to be *isomorphic* if there exists a bijection $f:V \rightarrow V'$ such that $(u,v) \in E$
if and only if $(f(u), f(v)) \in E'$.

**Remark:** This amounts to relabeling the vertices of $G$ to get the vertices of $G'$ maintaining corresponding
edges.

We say that a graph $G'=(V',E')$ is a *subgraph* of $G=(V,E)$ if $V' \subseteq V \text{ and } E' \subseteq E$

Give a set $V' \subset V$, the subgraph of $G$ *induced* by $V'$ is the graph of $G'=(V',E')$ where

$$ E'=\{(u,v) \in E \mid u,v \in V' \} $$

Given a directed graph $G = (V,E)$ a *neighbor* of a vertex $u$ is any vertex adjacent to $u$ in the
undirected version of $G$.

**Types of Graphs:**

* *Complete Graph:* An undirected graph where every pair of vertices is adjacent.
* *Bipartite Graph:* An undirected graph whose vertices can be partitioned into two sets $V_1$ and $V_2$ such
that all edges go between $V_1$ and $V_2$.

An acyclic undirected graph is a *forest* and a connected, acyclic, undirected graph is a *tree*.

A *multigraph* can have both self-loops and multiple edges between vertices.

The *contraction* of an undirected graph $G=(V,E)$ by an edge $e=(u,v)$ is a graph $G'=(V',E')$ where

$$ V = V - \{u,v\} \cup \{x\}$$

where $x$ is a new vertex. The set of edges is formed by deleting $(u,v)$ and for each vertex adjacent to $u$
or $v$

* deleting whichever of $(u,w)$ and $(v,w)$ belongs to $E$
* adding a new edge $(x,w)$

**Remark:** In effect, $u$ and $v$ are "contracted" into a single vertex.