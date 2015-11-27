package trees;

import estructuras.Nodo;

public class Abb implements ITree {

	private Nodo root;

	public Abb() {
		this.root = null;
	}

	@Override
	public String search(String key) {

		Nodo nodo = root;
		while (nodo != null) {
			if (key.equals(nodo.getKey()))
				return nodo.getValue();
			if (key.compareTo(nodo.getKey()) < 0)
				nodo = nodo.getLeft();
			else
				nodo = nodo.getRight();
		}
		return null;

	}

	@Override
	public void insert(String key, String value) {
		if (root == null) {
			root = new Nodo(key, value);
			return;
		}
		Nodo nodo = root;
		while (true) {
			if (nodo.getKey().equals(key)) {
				nodo.setValue(value);
				return;
			}
			if (nodo.getKey().compareTo(key) > 0)
				if (nodo.getLeft() != null)
					nodo = nodo.getLeft();
				else {
					nodo.setLeft(new Nodo(key, value));
					return;
				}
			else if (nodo.getRight() != null)
				nodo = nodo.getRight();
			else {
				nodo.setRight(new Nodo(key, value));
				return;
			}
		}

	}

	private Nodo join(Nodo izq, Nodo der) {
		if (izq == null)
			return der;
		if (der == null)
			return izq;
		Nodo centro = join(izq.getRight(), der.getLeft());
		izq.setRight(centro);
		der.setLeft(izq);
		return der;
	}

	private Nodo delete(String key, Nodo nodo) {
		if (nodo == null)
			return null;
		if (nodo.getKey().equals(key))
			return join(nodo.getLeft(), nodo.getRight());

		if (key.compareTo(nodo.getKey()) < 0)
			nodo.setLeft(delete(key, nodo.getLeft()));
		else
			nodo.setRight(delete(key, nodo.getRight()));
		return nodo;
	}

	@Override
	public void delete(String key) {
		root = delete(key, root);
	}

}
