# Data-Structures

A place to hold implementations of data structures for future reference.

* 4/5/2023 - Implemented Linked List
* 5/20/2023 - Finished implementing BST
* 5/26/2023 - Finished refactoring BST to include generics and functional interfaces.
* 6/2/2023 - A basic graph ADT is functional. Transferred LA Notes to this repository. The notes contain an intersection
of graph theory with linear algebra and algorithms broadly defined.
* 6/4/2023 - Sparse Graph class is finshed for now. Plan on looking at minimal spanning trees next.
* 6/5/2023 - Implemented dfs and bfs in a utilities class. To use a type variable for a static method do
something like this:

```java
public static <T> boolean bfSearch(Graph<T> graph, T key)
```
* 6/6/2023 - Implemented shortest path using bfs in utilities class. Still needs to be tested. Cormen, et.al.
has the best explanation of how to do this. Worked through the first chapter of Stanley's book *Algebraic
Combinatorics*. Will add some of those notes to the Notes later. Finding spectral graph theory particularly
interesting right now.

* 6/8/2023 - Worked through examples of Dijkstra's algorithms from Sedgewick and the grokking book. Working through
the three different implementations was enlightening.

* 6/9/2023 - Changed the abstraction. Graph is now an abstract class instead of an interface. Member variables
moved to Graph also. Since Graph is abstract, it has no constructor. Methods that work for both SparseGraph and
WeightedGraph have been moved to Graph. Graph now takes two type variables to account for using adjacent edge
lists in WeightedGraph.

* 6/9/2023 - Worked out pencil-and-paper examples of topological sort of a DAG. Read through the chapter on
actions of the symmetric group on a boolean algebra, from Stanley's *Algebraic Combinatorics*.

* 6/10/2023 - Created the abstract graph interface.

* 6/11/2023 - Created an inner class Pair in utilities to pair a vertex with a weight or cost.

```java
private static class Pair<T>
```
The private keyword is used to keep access restricted to the class it is defined in. The keyword static allows
use without an instance of Utilities. Static classes can't implement an interfaces. Implemented Dijkstra's
algorithm as presented in *grokking algorithms*. A very inefficient coding day.

Many of the vocab notes are from *Algorithms* Cormen, Leiserson, Rivest, Stein.
