package com.ssafy.boj;

import java.util.Scanner;

class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
}

class BST {
	Node root = null;

	public void insert(int value) {
		if (root == null) {
			root = new Node(value);
		} else {
			Node current = root;
			while (true) {
				if (current.value > value) {
					if (current.left == null) {
						current.left = new Node(value);
						break;
					}
					current = current.left;
				} else {
					if (current.right == null) {
						current.right = new Node(value);
						break;
					}
					current = current.right;
				}
			}
		}
	}
	
	public void travel() {
		postorder(root);
	}
	
	private void postorder(Node n) {
		if (n == null) return;
		
		postorder(n.left);
		postorder(n.right);
		
		System.out.println(n.value);
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BST bst = new BST();
		
		while (sc.hasNextInt()) {
			bst.insert(sc.nextInt());
		}
		
		bst.travel();
	}
}