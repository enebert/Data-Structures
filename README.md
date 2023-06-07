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

Many of the vocab notes are from *Algorithms* Cormen, Leiserson, Rivest, Stein.
