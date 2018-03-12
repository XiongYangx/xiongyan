/**
 * 
 */
package com.softeem.binaryTree;

/**
 * @author XiongYan
 *
 * 2018年1月29日
 */
public class Node {
	protected int data;            //存储数据域
	protected Node rchild = null;  //右指针
	protected Node lchild = null;  //左指针
	public Node() {
		
	}
	public Node(int data) {
		this.data = data;
	}
}
