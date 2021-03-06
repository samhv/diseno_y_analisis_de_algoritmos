package trees;

import estructuras.Nodo;

/**
 * Árbol ABB
 * 
 * @author Pablo
 *
 */
public class Abb extends ITree {

	public Abb() {
		this.root = null;
		
	}

	public long size() {
		return size(root);
	}

	@Override
	public int search(int key) {

		Nodo nodo = root;
		while (nodo != null) {
			if (key == nodo.getKey())
				return nodo.getValue();
			if (key < nodo.getKey() )
				nodo = nodo.getLeft();
			else
				nodo = nodo.getRight();
		}
		return -1;

	}

	@Override
	public Nodo insert(int key, int value) {
		Nodo nuevo = new Nodo(null, key, value);

		if (root == null) {
			size++;
			root = nuevo;
			return nuevo;
		}
		Nodo nodo = root;
		while (nodo != null) {
			
			if (nodo.getKey() < key) {
				if (nodo.getRight() == null) {
					nodo.setRight(nuevo);
					nuevo.setParent(nodo);
					size++;
					return nuevo;
				}
				nodo = nodo.getRight();

			} else {
				if (nodo.getLeft() == null) {
					nodo.setLeft(nuevo);
					nuevo.setParent(nodo);
					size++;
					return nuevo;
				}
				nodo = nodo.getLeft();
			}
		}

		return nuevo;
	}

	protected Nodo getNodo(int key) {
		Nodo node = root;
		while (node != null) {
			
			if (key == node.getKey()) {
				return node;
			} else if (key < node.getKey()) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		return node;
	}

	protected Nodo getGreatest(Nodo startingNode) {
		if (startingNode == null)
			return null;

		Nodo greater = startingNode.getRight();
		while (greater != null) {
			Nodo node = greater.getRight();
			if (node != null)
				greater = node;
			else
				break;
		}
		return greater;
	}

	// revisar
	public Nodo delete(int key) {
		Nodo toRemoved = getNodo(key);
		if (toRemoved != null) {
			toRemoved = removeNode(toRemoved);
		}
		return toRemoved;
	}

	protected Nodo removeNode(Nodo toRemoved) {
		if (toRemoved != null) {
			Nodo replacementNode = getReplacementNode(toRemoved);
			replaceNodeWithNode(toRemoved, replacementNode);
		}
		return toRemoved;

	}

	protected Nodo getReplacementNode(Nodo nodeToRemoved) {
		Nodo replacement = null;
		if (nodeToRemoved.getLeft() != null && nodeToRemoved.getRight() == null) {
			// Usamos el de la izquierda
			replacement = nodeToRemoved.getLeft();
		} else if (nodeToRemoved.getRight() != null && nodeToRemoved.getLeft() == null) {
			// Usamos el de la derecha
			replacement = nodeToRemoved.getRight();
		} else if (nodeToRemoved.getRight() != null && nodeToRemoved.getLeft() != null) {
			// Dos hijos
			// Usamos el mayor de los menores
			replacement = getGreatest(nodeToRemoved.getLeft());
			if (replacement == null)
				replacement = nodeToRemoved.getLeft();

		}
		return replacement;
	}

	protected void replaceNodeWithNode(Nodo nodeToRemoved, Nodo replacementNode) {
		if (replacementNode != null) {
			// Save for later
			Nodo replacementNodeLesser = replacementNode.getLeft();
			Nodo replacementNodeGreater = replacementNode.getRight();

			// Replace replacementNode's branches with nodeToRemove's branches
			Nodo nodeToRemoveLesser = nodeToRemoved.getLeft();
			if (nodeToRemoveLesser != null && !nodeToRemoveLesser.equals(replacementNode)) {

				replacementNode.setLeft(nodeToRemoveLesser);
				nodeToRemoveLesser.setParent(replacementNode);
			}
			Nodo nodeToRemoveGreater = nodeToRemoved.getRight();
			if (nodeToRemoveGreater != null && !nodeToRemoveGreater.equals(replacementNode)) {

				replacementNode.setRight(nodeToRemoveGreater);
				nodeToRemoveGreater.setParent(replacementNode);

			}

			// Remove link from replacementNode's parent to replacement
			Nodo replacementParent = replacementNode.getParent();
			if (replacementParent != null && !replacementParent.equals(nodeToRemoved)) {
				Nodo replacementParentLesser = replacementParent.getLeft();
				Nodo replacementParentGreater = replacementParent.getRight();
				if (replacementParentLesser != null && replacementParentLesser.equals(replacementNode)) {
					replacementParent.setLeft(replacementNodeGreater);
					if (replacementNodeGreater != null)
						replacementNodeGreater.setParent(replacementParent);
				} else if (replacementParentGreater != null && replacementParentGreater.equals(replacementNode)) {
					replacementParent.setRight(replacementNodeLesser);
					if (replacementNodeLesser != null)
						replacementNodeLesser.setParent(replacementParent);
				}
			}
		}

		// Update the link in the tree from the nodeToRemoved to the
		// replacementNode
		Nodo parent = nodeToRemoved.getParent();
		if (parent == null) {
			// Replacing the root node
			root = replacementNode;
			if (root != null)
				root.setParent(null);
		} else if (parent.getLeft() != null && (parent.getLeft().getKey()==nodeToRemoved.getKey())) {
			parent.setLeft(replacementNode);
			if (replacementNode != null)
				replacementNode.setParent(parent);
		} else if (parent.getRight() != null && (parent.getRight().getKey()==nodeToRemoved.getKey()) ) {
			parent.setRight(replacementNode);
			if (replacementNode != null)
				replacementNode.setParent(parent);

		}
		size--;
	}

}
