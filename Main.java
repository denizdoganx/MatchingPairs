import java.io.*;
import java.util.Random;

public class Main {	
	static int defaultValueOfX = 5;
	static String name = "YOU";
	static Stack fruitStack = new Stack(15);
	static Random rnd = new Random();
	
	
	public static Stack UploadLoginFile() throws IOException {
		//file upload method
		File file = new File("D:\\fruits.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String fruitName;
		
		while((fruitName = br.readLine()) != null) {
			fruitStack.push(fruitName);
		}
		br.close();
		return fruitStack;
	}
	public static void PrintStack(Stack stack) {
		//stack print function
		Stack tempStack = new Stack(stack.size());
		while(!stack.isEmpty()) {
			Object object = stack.pop();
			tempStack.push(object);
			System.out.print(object + "   ");
		}
		System.out.println();
		while(!tempStack.isEmpty()) {
			stack.push(tempStack.pop());
		}
	}
	public static Stack BackupCreate(Stack stack) {
		//creating a backup stack
		Stack tempStack = new Stack(stack.size());
		while(!stack.isEmpty()) {
			tempStack.push(stack.pop());
		}
		Stack backup = new Stack(tempStack.size());
		while(!tempStack.isEmpty()) {
			Object object = tempStack.pop();
			stack.push(object);
			backup.push(object);
		}
		return backup;
	}
	public static Stack CreateStacks(Stack randomStack) {
		//Fruits into stack1 and stack2 are dropped in this function
		Stack stack = new Stack(randomStack.size());
		int index = 0;
		int order = 0;
		while(index != defaultValueOfX) {
			Stack tempFruitStack = new Stack(fruitStack.size());
			while((int)randomStack.peek() != order) {
				tempFruitStack.push(fruitStack.pop());
				order++;
			}
			stack.push(fruitStack.peek());
			randomStack.pop();
			while(!tempFruitStack.isEmpty()) {
				fruitStack.push(tempFruitStack.pop());
			}
			order = 0;
			index++;
		}
		return stack;
	}
	public static int MainGameMode(Stack stack1, Stack stack2) {
		//main game zone
		int rnd1;int rnd2;
		int score = 0;
		int step = 1;
		while(!stack1.isEmpty()) {
			rnd1 = rnd.nextInt(stack1.size()) + 1;
			rnd2 = rnd.nextInt(stack2.size()) + 1;
			System.out.print("\nStack1 : ");
			PrintStack(stack1);
			System.out.print("Stack2 : ");
			PrintStack(stack2);
			System.out.println("\t\t\t\t\t\t\t\tStep = " + step);
			System.out.print("Randomly generated numbers : " + rnd1 + "    " + rnd2);
			System.out.println("\t\t\t\tScore = " + score + "\n\n\n\n\n");
			Stack backupStack1 = new Stack(stack1.size());
			Stack backupStack2 = new Stack(stack2.size());
			//The necessary checks have been made for the area where a random dice is thrown and the stack is given.
			for(int i = 0;i < rnd1; i++) {
				if(i == rnd1 - 1) {
					break;
				}
				backupStack1.push(stack1.pop());
			}
			for(int i = 0;i < rnd2; i++) {
				if(i == rnd2 - 1) {
					break;
				}
				backupStack2.push(stack2.pop());
			}
			if(stack1.peek().equals(stack2.peek())) {
				stack1.pop();
				stack2.pop();
				score += 20;					
			}
			else {
				score -= 1;
			}
			
			while(!backupStack1.isEmpty()) {
				stack1.push(backupStack1.pop());
			}
			while(!backupStack2.isEmpty()) {
				stack2.push(backupStack2.pop());
			}
			step++;
		}
		System.out.println("\nStack1 : ");
		System.out.println("Stack2 : \t\t\t\t\t\t\t" + "Score = " + score + "\n\n");
		System.out.println("Congratulations You Won " + score + " Points !!!\n");
		System.out.println("The game is over.");
		return score;
	}
	public static Stack getHighScoreTable() throws IOException {
		//The high score table is taken to a stack in this section
		File file = new File("D:\\highscoretable.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		Stack highScoreTable = new Stack(10);
		String line;
		String name = "";
		int score;
		int index = 0;
		while((line = br.readLine()) != null) {
			if(index % 2 == 0) {
				name = line;
				index++;
				continue;
			}
			else {
				score = Integer.valueOf(line);
				index++;
			}
			highScoreTable.push(name + " " + score);
		}
		br.close();
		return highScoreTable;
	}
	public static void PrintHighScoreTable(Stack stack) {
		//With the newly obtained score, the scores are rearranged and returned
		System.out.println("\n\n-------High Score Table-------\n");
		Stack tempStack = new Stack(stack.size());
		while(!stack.isEmpty()) {
			tempStack.push(stack.pop());
		}
		while(!tempStack.isEmpty()) {
			Object data = tempStack.pop();
			System.out.println("-> " + data);
			stack.push(data);
		}
	}
	public static Stack SortScores(int score, Stack highScoreTable) {
		//With the newly obtained score, the scores are rearranged and returned
		Stack tempStack = new Stack(highScoreTable.size());
		while(!highScoreTable.isEmpty()) {
			Object data = highScoreTable.pop();
			tempStack.push(data);
			String sData = data.toString();
			int index = 0;
			//name and score are separated by space
			for(int i = 0;i < sData.length(); i++) {
				char chr = sData.charAt(i);
				if(chr == ' ') {
					index = i + 1;
					break;
				}
			}
			int iScore = Integer.valueOf(sData.substring(index));
			if(score > iScore) {
				continue;
			}
			else if(score == iScore) {
				Object youData = name + " " + score;
				highScoreTable.push(youData);
			}
			else {
				highScoreTable.push(tempStack.pop());
				Object youData = name + " " + score;
				highScoreTable.push(youData);
			}
			while(!highScoreTable.isFull()) {
				highScoreTable.push(tempStack.pop());
			}
			break;
		}
		if(!highScoreTable.isFull()) {
			/*if the score obtained was the best score of all time,
			we could not create the high score table there, so an extra check is made here for this situation.*/
			highScoreTable.push(name + " " + score); 
			while(!highScoreTable.isFull()) {
				highScoreTable.push(tempStack.pop());
			}
		}
		return highScoreTable;
	}
	public static void PrintingToFile(Stack highScoreTable) throws IOException {
		//The new high score table created in this section is printed in the highscoretable.txt file.
		File file = new File("highscoretable.txt");
		FileWriter fw = new FileWriter(file, false);
		BufferedWriter bw = new BufferedWriter(fw);
		Stack tempStack = new Stack(highScoreTable.size());
		while(!highScoreTable.isEmpty()) {
			tempStack.push(highScoreTable.pop());
		}
		while(!tempStack.isEmpty()) {
			Object data = tempStack.pop();
			String line = data.toString();
			int index = 0;
			for(int i = 0;i < line.length(); i++) {
				if(line.charAt(i) == ' ') {
					index = i;
					break;
				}
			}
			String name = line.substring(0,index);
			String score = line.substring(index + 1);
			bw.write(name);
			bw.newLine();
			bw.write(score);
			bw.newLine();
		}
		bw.close();
	}
	public static void main(String[] args) throws IOException {
		
		fruitStack = UploadLoginFile();
		System.out.print("Fruit Stack -> ");
		PrintStack(fruitStack);
		
		System.out.println();
		Stack stack1;
		Stack stack2;
		Stack randomStack1 = new Stack(defaultValueOfX);
		Stack randomStack2 = new Stack(defaultValueOfX);
		//an array of random numbers that do not repeat is created
		int index = 0;
		while(index != defaultValueOfX) {
			boolean isSameData = false;
			int randomNumber = rnd.nextInt(fruitStack.size());
			Stack tempStack = new Stack(randomStack1.size());
			while(!randomStack1.isEmpty()) {
				if((int)randomStack1.peek() == randomNumber) {
					isSameData = true;
					break;
				}
				tempStack.push(randomStack1.peek());
				randomStack1.pop();
			}
			if(isSameData) {
				while(!tempStack.isEmpty()) {
					randomStack1.push(tempStack.pop());
				}
				continue;
			}
			while(!tempStack.isEmpty()) {
				randomStack1.push(tempStack.pop());
			}
			randomStack1.push(randomNumber);
			index++;
		}
		//random array is sorted randomly within itself
		Stack backupRandomStack1 = BackupCreate(randomStack1);
		index = 0;
		while(index != defaultValueOfX) {
			boolean isSameData = false;
			int randomNumber = rnd.nextInt(fruitStack.size());
			Stack tempRandomStack1 = new Stack(backupRandomStack1.size());
			while(!backupRandomStack1.isEmpty()) {
				if((int)backupRandomStack1.peek() == randomNumber) {
					isSameData = true;
					break;
				}
				tempRandomStack1.push(backupRandomStack1.pop());
			}
			if(isSameData) {
				randomStack2.push(backupRandomStack1.pop());
				index++;
			}
			while(!tempRandomStack1.isEmpty()) {
				backupRandomStack1.push(tempRandomStack1.pop());
			}
		}
		//Stack 1 and Stack 2 are created with random arrays
		//some functions or procedures are called in this section.
		stack1 = CreateStacks(randomStack1);
		stack2 = CreateStacks(randomStack2);
		int score = MainGameMode(stack1, stack2);
		Stack highScoreTable = getHighScoreTable();
		Stack sortedHighScoreTableForm = SortScores(score, highScoreTable);
		PrintHighScoreTable(sortedHighScoreTableForm);
		PrintingToFile(sortedHighScoreTableForm);
	}
}
