module AVL
    where

        class Tree a where
            create :: (Show b) => [b] -> a b
        
        data AVLTree a = Empty Int | Leaf a Int | Node (AVLTree a) a Int (AVLTree a)

        instance Show a => Show (AVLTree a) where
            show (Empty d) = "Empty" ++ " " ++ show d
            show (Leaf a d) = "Leaf" ++ " " ++ show a ++ " " ++ show d
            show (Node left a d right) = "(" ++ show left ++ " " ++ show a ++ " " ++ show d ++ " " ++ show right ++ ")"

        instance Tree (AVLTree) where 
            create :: [a] -> AVLTree a
            create [] = Empty 0
            create [x] = Leaf x 1
        
        instance Functor AVLTree where
            fmap :: (a -> b) -> AVLTree a -> AVLTree b
            fmap _ (Empty d) = Empty d
            fmap f (Leaf x d) = Leaf (f x) d
            fmap f (Node left x d right) = Node (fmap f left) (f x) d (fmap f right)