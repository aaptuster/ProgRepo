import java.util.Random;
public class ApplicationDriver {
	
	public static void main(String[] args)
	{
		int num = Integer.parseInt(args[0]);
		int bound = Integer.parseInt(args[1]);
		Tree tree = new Tree();
		
		System.out.printf("Hash value generated for string %s is %d", args[2], Hash.getHASHValue(args[2]));
		Random rand = new Random();
		for (int i=0;i<num;i++)
		{
			int number = rand.nextInt(bound);
			tree.add(number);
		}
		tree.inorder();
		/*for (int i =0;i<=num;i++)
		{
			int number1 = rand.nextInt(bound);
			int number2 = rand.nextInt(bound);
			
			int minAncestor = tree.leastCommonAncestor(number1, number2);
			if (minAncestor != -1)
				System.out.printf("minAncestor to %d and %d  is %d \n",number1,number2,minAncestor);
		}*/
		
		//tree.convertTreeToLinkedList();
		tree.convertTreeToDoublyLinkedList();
		//tree.inorder();
		//tree.messWithTree();
		//System.out.println("After messing with the tree , inorder is");
		//tree.inorder();
		//System.out.println("MAX size of BST is "+tree.findMAXBST());
		
		
		
		
	}
}