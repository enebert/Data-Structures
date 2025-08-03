module BST
    where
    
    import Data.Maybe
    import Trees
    
    preOrder :: Show t => BinaryTree t -> IO()
    preOrder (EmptyTree) = return()
    preOrder (Leaf x) = putStr $ show x ++ "\t"
    preOrder (Branch left x right) = do
        putStr $ show x ++ "\t"
        preOrder left
        preOrder right
    
    inOrder :: Show t => BinaryTree t -> IO()
    inOrder (EmptyTree) = return()
    inOrder (Leaf x) = putStr $ show x ++ "\t"
    inOrder (Branch left x right) = do
        inOrder left
        putStr $ show x ++ "\t"
        inOrder right
    
    postOrder :: Show t => BinaryTree t -> IO()
    postOrder (EmptyTree) = return()
    postOrder (Leaf x) = putStr $ show x ++ "\t"
    postOrder (Branch left x right) = do
        postOrder left
        postOrder right
        putStr $ show x ++ "\t"

    createBST :: Ord t => [t] -> BinaryTree t
    createBST [] = EmptyTree
    createBST xs = foldr insert EmptyTree xs
    
    insert :: Ord t => t -> BinaryTree t -> BinaryTree t
    insert a (EmptyTree) = Leaf a
    insert a (Leaf x)
        | a < x = Branch (Leaf a) x EmptyTree
        | a == x = Branch (Leaf a) a EmptyTree
        | a > x = Branch EmptyTree x (Leaf a)
    insert a (Branch left x right)
        | a < x = Branch (insert a left) x right
        | a == x = Branch left a right
        | a > x = Branch left x (insert a right)
        
    delete :: Ord t => t -> BinaryTree t -> BinaryTree t
    delete a (EmptyTree) = EmptyTree
    delete a (Leaf x) = EmptyTree
    delete a (Branch left x right)  
        | a < x = Branch (delete a left) x right
        | a > x = Branch left x (delete a right)
        | a == x = Branch left (min) (delete min right) where min = fromJust $ minElement right

    maxElement :: Eq t => BinaryTree t -> Maybe t
    maxElement (EmptyTree) = Nothing
    maxElement (Leaf x) = Just x
    maxElement (Branch left x right) = if ((maxElement right) == Nothing) then Just x else maxElement right
    
    minElement :: Eq t => BinaryTree t -> Maybe t
    minElement (EmptyTree) = Nothing
    minElement (Leaf x) = Just x
    minElement (Branch left x right) = if ((minElement left) == Nothing) then Just x else minElement left
        
    