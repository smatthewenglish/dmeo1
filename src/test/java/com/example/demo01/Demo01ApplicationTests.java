package com.example.demo01;

import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Demo01ApplicationTests.class)
class Demo01ApplicationTests {

	static List<String> findPairs(int[] nums, int target) {
		List<String> resultList = new ArrayList<>();
		List<Integer> usedIndex = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int value00 = nums[i];
			for(int j = i + 1; j < nums.length; j++) {
				int value01 = nums[j];
				if(value00 + value01 == target) {
					if(!usedIndex.contains(i) && !usedIndex.contains(j)) {
						resultList.add(value00 + "," + value01);
						usedIndex.add(i);
						usedIndex.add(j);
						break;
					}
				}
			}
		}
		return resultList;
	}

	@Test
	void test00() {
		int[] nums = new int[]{5,1,2,4,3};
		int target = 5;
		List<String> outputList = findPairs(nums, target);
		System.out.println(outputList);
		List<String> expectedList = new ArrayList<>();
		expectedList.add("1,4");
		expectedList.add("2,3");
		assertEquals(expectedList, outputList);
	}

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			buildString(sb, this, 0, "Root: ");
			return sb.toString();
		}
	
		private void buildString(StringBuilder sb, TreeNode node, int level, String prefix) {
			if (node == null) {
				for (int i = 0; i < level; i++) {
					sb.append("   ");
				}
				sb.append(prefix).append("null\n");
				return;
			}
	
			for (int i = 0; i < level; i++) {
				sb.append("   ");
			}
	
			sb.append(prefix).append(node.val).append("\n");
			buildString(sb, node.left, level + 1, "L--- ");
			buildString(sb, node.right, level + 1, "R--- ");
		}
	}

	@Test
	void test01() {

		
		TreeNode n03 = new TreeNode(3);
		TreeNode n04 = new TreeNode(4);
		TreeNode n05 = new TreeNode(5);
		TreeNode n06 = new TreeNode(6);

		TreeNode n01 = new TreeNode(1, n03, n04);
		//TreeNode n02 = new TreeNode(2, n05, null);
		TreeNode n02 = new TreeNode(2, n05, n06);
		TreeNode n00 = new TreeNode(0, n01, n02);

		System.out.println("Tree Structure:");
    	System.out.println(n00);


		// breadth-first search using a queue
		List<Integer> orderBfs = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(n00);

		while(!queue.isEmpty()){
			TreeNode current = queue.poll();
			orderBfs.add(current.val);

			if(current.left != null) {
				queue.add(current.left);
			}
			if(current.right != null) {
				queue.add(current.right);
			}
		}

		System.out.println("BFS Order: " + orderBfs);


		// depth-first search using a stack
		List<Integer> orderDfs = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(n00);

		while(!stack.isEmpty()) {
			TreeNode current = stack.pop();

			orderDfs.add(current.val);

			// Push right first, so that left is processed first
			if(current.right != null) {
				stack.push(current.right);
			}
			if(current.left != null) {
				stack.push(current.left);
			}
		}
		System.out.println("DFS Order: " + orderDfs);
	}

	

}
