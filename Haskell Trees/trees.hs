module Trees
    where
    
    data BinaryTree a = EmptyTree | Leaf a | Branch (BinaryTree a) a (BinaryTree a) deriving (Show)
    
    treeSize EmptyTree = 0
    treeSize (Leaf x) = 1
    treeSize (Branch left x right) = 1 + treeSize left + treeSize right
    
    mapTree :: (t->a) -> BinaryTree t -> BinaryTree a
    mapTree f (EmptyTree) = EmptyTree
    mapTree f (Leaf x) = Leaf (f x) 
    mapTree f (Branch left x right) = Branch (mapTree f left) (f x) (mapTree f right)
    