import java.util.ArrayList;
import java.util.List;

public class P065_BinarySearchTree<T extends Comparable<T>> {

   Node<T> root;

   P065_BinarySearchTree(T root) {
      this.root = new Node(root);
   }

   void add(T n) {
      root.add(new Node(n));
   }

   T search(T value) {
      return root.search(value).value;
   }

   List<T> path(T value) {
      List<T> results = new ArrayList<>();
      Node<T> current = root;
      results.add(current.value);
      while (current.value.compareTo(value) != 0) {
         if (current.value.compareTo(value) > 0) {
            if (current.left == null)
               return null;
            else {
               results.add(current.left.value);
               current = current.left;
            }
         } else {
            if (current.right == null)
               return null;
            else {
               results.add(current.right.value);
               current = current.right;
            }
         }
      }
      return results;
   }

   void depthFirstTraverse() {
      root.depthFirstTraverse();
   }

   public static void main(String[] args) {
      P065_BinarySearchTree<Integer> tree = new P065_BinarySearchTree<Integer>(50);
      tree.add(25);
      tree.add(10);
      tree.add(75);
      tree.add(30);
      tree.add(55);
      Integer n30 = tree.search(30);
      Integer nNull = tree.search(74);
      System.out.println(tree.path(74));
      List<Integer> ints = tree.path(30);
      ints.add(61);
      ints.add(22);
      ints.add(2);
      ints.remove(2);
   }
   
    class Node<T extends Comparable<T>> {
		Node<T> left;
		Node<T> right;
		T value;

		Node(T value) {
			this.value = value;
		}

		void add(Node<T> other) {
			if (this.value.compareTo(other.value) > 0) {
				if (left == null) {
					left = other;
				} else {
					left.add(other);
				}
			} else if (this.value.compareTo(other.value) < 0) {
				if (right == null) {
					right = other;
				} else {
					right.add(other);
				}
			}
		}
		
		Node<T> search(T value) {
			System.out.println("Checking "+this+" for "+value);
			if (this.value.equals(value)) return this;
			else if (left!=null && this.value.compareTo(value) > 0) {
				return left.search(value);
			} else if (right!=null && this.value.compareTo(value) < 0) {
				return right.search(value);
			} else return null;
		}

		public String toString() {
			return value.toString();
		}

		public void depthFirstTraverse() {
			if (left!= null) {
				left.depthFirstTraverse();
			}
			System.out.println(this);
			if (right!=null) {
				right.depthFirstTraverse();
			}
		}
   }
}
