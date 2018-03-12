/**
 * 
 */
package com.softeem.binaryTree;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author XiongYan
 * 2018年1月29日
 */
//二叉树的相关操作：

public class binaryTree {
	public static void main(String[] args) {
		int[] pre = {3,5,4,2,7,6,9};
		int[] infix = {4,5,2,3,6,7,9};
		int[] post = {4,2,5,6,9,7,3};
		Node root = createBinaryTreeByPreIn(pre, infix, 0, 0, 7);
		Node root2 = createBinaryTreeInPost(infix, post, 0, 0, 7);
		preOrder1(root2);
		preOrder1(root);
		System.out.println();
		ArrayList<Integer> list1 = preOrder2(root);
		System.out.println(list1);
		infixOrder1(root);
		ArrayList<Integer> list2 = infixOrder2(root);
		System.out.println();
		System.out.println(list2);
		postOrder1(root);
		ArrayList<Integer> list3 = postOrder2(root);
		System.out.println();
		System.out.println(list3);
		ArrayList<Integer> list4 = postOrder3(root);
		System.out.println(list4);

	}
	/**创建二叉树：
	 * */
	public Node createBinaryTree() {
		Scanner sc = new Scanner(System.in);
		Node root;
		int data = sc.nextInt();
		if(data == -1) {
			return null;
		}else {
			root = new Node(data);
			root.lchild = createBinaryTree();
			root.rchild = createBinaryTree();
		}
		return root;
	}
	/**根据先序遍历以及中序遍历创建二叉树：
	 * pre:先序遍历序列
	 * infix：中序遍历序列
	 * preBegin：先序遍历序列的第一个元素
	 * infixBegin：中序遍历的第一个元素
	 * len：长度
	 * */
	public static Node createBinaryTreeByPreIn(int[] pre,int[] infix,int preBegin,int infixBegin,int len) {
		if(pre == null || infix ==null || pre.length != infix.length ||pre.length ==0 || infix.length == 0) {
			return null;
		}
		if(len <= 0) {
			return null;
		}
		Node root = new Node(pre[preBegin]);//根节点为先序遍历的第一个元素
		int rootIndex;
		//for循环的作用是找到中序遍历时的根节点,然后将二叉树一分为二，一棵左子树，一棵右子树，然后递归调用；
		for(rootIndex = 0; infix[rootIndex] != pre[preBegin]; rootIndex++);
		//root.lchild = createBiTreeByPreIn(pre, in, preBegin + 1, inBegin, rootIndex - inBegin);
		root.lchild = createBinaryTreeByPreIn(pre, infix, preBegin+1, infixBegin, rootIndex - infixBegin);
		root.rchild = createBinaryTreeByPreIn(pre, infix, preBegin+(rootIndex - infixBegin) + 1, rootIndex +1, len -(rootIndex-infixBegin)-1);
		return root;
	}
	/**根据中序和后序遍历序列创建二叉树：
	 * infix:中序遍历序列
	 * post：后序遍历序列
	 * infixBegin：中序遍历序列第一个元素
	 * postBegin：后序遍历序列第一个元素
	 * len：长度：
	 * */
	public static Node createBinaryTreeInPost(int[] infix,int[] post,int infixBegin,int postBegin,int len) {
		if(infix == null || post == null || infix.length != post.length || infix.length == 0 || post.length ==0) {
			return null;
		}
		if(len <= 0) {
			return null;
		}
		Node root = new Node(post[len-1]);//根节点为后序遍历的最后一个节点;
		int rootIndex;
		for(rootIndex = 0; infix[rootIndex] != post[len-1];rootIndex++);
		root.lchild = createBinaryTreeInPost(infix, post, infixBegin, postBegin, rootIndex -infixBegin);
		root.rchild = createBinaryTreeInPost(infix, post, rootIndex+1, rootIndex, len - (rootIndex - infixBegin)-1);
		return root;
	}
	/**递归前序遍历二叉树：
	 * */
	public static void preOrder1(Node root) {
		if(root != null) {
			System.out.print(root.data + " ");
			preOrder1(root.lchild);
			preOrder1(root.rchild);
		}
	}
	/**非递归前序遍历二叉树：
	 * */
	public static ArrayList<Integer> preOrder2(Node root){
		ArrayList<Integer> preList = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		while(root !=null || !stack.isEmpty()) {
			//当访问二叉树的根节点时，此时栈为空；接着访问根节点的左子树，而当某个树root的左子树为空时，
			//栈一定不为空，应为root在栈顶----!stack.isEmpty()就是访问当前根节点(栈顶元素)
			if(root != null) { //当前树的root不为空，将其入栈，并将root重新指向其左子树
				preList.add(root.data); 
				stack.push(root);
				root = root.lchild;
			}else { //否则根节点出栈，遍历右子树
				root = stack.pop();
				root = root.rchild;
			}
		}
		return preList;
	}
	/**递归中序遍历二叉树：
	 * */
	public static void infixOrder1(Node root) {
		if(root !=null) {
			infixOrder1(root.lchild);
			System.out.print(root.data+" ");
			infixOrder1(root.rchild);
		}
	}
	/**非递归中序遍历二叉树：
	 **/
	public static ArrayList<Integer> infixOrder2(Node root) {
		ArrayList<Integer> infixList = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		while(root !=null || !stack.isEmpty()) {
			if(root !=null) {
				stack.push(root);
				root = root.lchild;
			}else {
				root = stack.pop();
				infixList.add(root.data);
				root = root.rchild;
			}
		}
		return infixList;
	}
	/**递归后序遍历二叉树：
	 * */
	public static void postOrder1(Node root) {
		if(root != null) {
			postOrder1(root.lchild);
			postOrder1(root.rchild);
			System.out.print(root.data + " ");
		}
	}
	/**非递归后序遍历二叉树：
	 * */
	public static ArrayList<Integer> postOrder2(Node root){
		ArrayList<Integer> postList = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		Node isAccess = null;
		while(root != null || !stack.isEmpty()) {
			if(root != null) {
				stack.push(root);
				root = root.lchild;
			}else{ //某棵树没有左子树
				root = stack.peek();//取出栈顶的元素，但并为将该元素删除
				root = root.rchild;
				if(root == null || isAccess == root) {
					//若没有右子树吧或者右子树已经被访问，那么就可以访问栈顶元素了(此时左右子树元素均已被访问)
					root = stack.pop();//将栈顶元素取出，并将该元素从栈中删除
					postList.add(root.data);
					isAccess = root; //记录刚刚访问的节点
					root = null; //刚访问的左右子树已经被访问了(root也被访问了)
				}else{ //此时应该从获取栈顶元素(root的父节点)，遍历父节点的左子树
					stack.push(root);
					root = root.lchild;
				}
			}
		}
		return postList;
	}
	/**递归后序遍历：
	 * */
	public static ArrayList<Integer> postOrder3(Node root){
		ArrayList<Integer> postList = new ArrayList<Integer>();
		ArrayList<Integer> leftList = new ArrayList<Integer>();
		ArrayList<Integer> rightList = new ArrayList<Integer>();
		if(root == null) {
			return postList;
		}
		leftList = postOrder3(root.lchild);
		rightList = postOrder3(root.rchild);
		postList.addAll(leftList);
		postList.addAll(rightList);
		postList.add(root.data);
		return postList;
	}
	/**按层次遍历：从上到下，从左到右
	 * */
}
