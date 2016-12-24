import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

/**
* @param Name: Frank Dong
* @param Date: Nov 18, 2016
* @param Purpose: A UI class which implements the user interface and it contains
* 				  the main method.
*/
public class UI {
	public static void main(String[] args) 
	{
		//Create new dictionary and file
		OrderedDictionary dictionary = new OrderedDictionary();
		File file =  null;
		
		//If arguments is not null read arguments
		if (args.length > 0)
		{
			file = new File(args[0]);
		}
		//Else return error, as arguments are not provided
		else
		{
			System.out.println("Invalid arguments");
			System.exit(0);
		}
		try {
			
			//Scan file
			Scanner scan = new Scanner(file);
			while (scan.hasNext())
			{
				//Declare variables
				int keyTypeValue;
				String keyWordValue;
				String keyWordType;
				String keyValue;
				String recordData;
				
				//Tokenize first line (the word for key)
				StringTokenizer word = new StringTokenizer(scan.nextLine(), "\n");
				keyWordValue = word.nextToken();
				
				
				//Tokenize Second line (the data for record and type for key)
				StringTokenizer data = new StringTokenizer(scan.nextLine(), "\n");
				keyValue = data.nextToken();
				StringTokenizer keyType = new StringTokenizer(keyValue, ".");
				recordData = keyValue;
				System.out.print(keyValue +"\n");
				
				//If there are more than 2 tokens for the second line (. found)
				if (keyType.countTokens() > 1)
				{
					//Go to token after .
					keyType.nextToken();
					keyWordType = keyType.nextToken();
					
					//If value is wav or mid type 2
					if (keyWordType.equals("wav") || keyWordType.equals("mid"))
					{
						keyTypeValue = 2;
					}
					
					//Else if value is gif or jpg type 3
					else if  (keyWordType.equals("gif") || keyWordType.equals("jpg"))
					{
						keyTypeValue = 3;
					}
					
					//Else default to value 1
					else
					{
						keyTypeValue = 1;
					}
				}
				
				//If there is no . found, reutrn type 1
				else
				{
					keyTypeValue = 1;
				}
				
				//Create key and record with values read
				Key k = new Key(keyWordValue, keyTypeValue);
				Record record = new Record(k, recordData);
				
				//Insert into dictionary
				dictionary.insert(record);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DictionaryException e) {
			e.printStackTrace();
		}
		System.out.println("File Records insert successful");
		
		//While exit is false
		boolean exit = false;
		while (exit != true)
		{
			//Read users command
			StringReader keyboard = new StringReader();
			String line = keyboard.read("Enter next command: ");
			
			//Declare variables
			String commandType;
			String userWord;
			int type;
			String data;
			
			//Tokenize users commands by spaces
			StringTokenizer userCommand = new StringTokenizer(line, " ");
			commandType = userCommand.nextToken();
			
			//If the first token of users command is search
			if (commandType.equals("search"))
			{
				//Checks to see if there are more arguments, if not return error
				if (userCommand.countTokens() < 1)
				{
					System.out.println("Error, too little arguments given! Program ending");
					break;
				}
				//set userWord equal to the next token
				userWord = userCommand.nextToken();
				userWord.replaceAll(" ", "");
				
				//Test dictionary to see which type value is
				Record valueIsType1 = dictionary.find(new Key(userWord, 1));
				Record valueIsType2 = dictionary.find(new Key(userWord, 2));
				Record valueIsType3 = dictionary.find(new Key(userWord, 3));
				
				//If not found, return error
				if (valueIsType1 == null && valueIsType2 == null && valueIsType3 == null)
				{
					System.out.println("Error value is not in the dictionary!");
				}
				else
				{
					//If type is 1, print out the data
					if (valueIsType1 != null)
					{
						System.out.println(valueIsType1.getData());
					}
					
					//If type is 3 display the picture
					if (valueIsType3 != null)
					{
						PictureViewer viewer = new PictureViewer();
						try {
							viewer.show(valueIsType3.getData());
						} catch (MultimediaException e) {
							e.printStackTrace();
						}
					}
					
					//If type is 2 play a sound and print out the data
					if (valueIsType2 != null)
					{
						SoundPlayer sound = new SoundPlayer();
						try {
							sound.play(valueIsType2.getData());
						} catch (MultimediaException e) {
							e.printStackTrace();
						}
					}
					
					//Else print error message
					else
					{
						System.out.println("Error! value is not in the dictionary!");
					}
				}
			}
			
			//If the first token of users command is remove
			else if (commandType.equals("remove"))
			{
				//Checks to see if there are more arguments, if not return error
				if (userCommand.countTokens() < 2)
				{
					System.out.println("Error, too little arguments given! Program ending");
					break;
				}
				//Read next 2 token values
				userWord = userCommand.nextToken();
				
				type =Integer.parseInt(userCommand.nextToken());
				
				Key k = new Key(userWord, type);
				
				//If value is not found return error message
				if (dictionary.find(k) == null)
				{
					System.out.println("Error! value is not in the dictionary!");
				}
				else
				{
					//Else remove value from dictionary
					try {
						dictionary.remove(k);
					} catch (DictionaryException e) {
						e.printStackTrace();
					}
				}
			}
			
			//If the first token of users command is insert
			else if (commandType.equals("insert"))
			{
				//Checks to see if there are more arguments, if not return error
				if (userCommand.countTokens() < 3)
				{
					System.out.println("Error, too little arguments given! Program ending");
					break;
				}
				//Reads next 3 tokens
				userWord = userCommand.nextToken();
				type =Integer.parseInt(userCommand.nextToken());
				data = userCommand.nextToken("\n");
				data = data.trim(); //Gets rid of whitespace
				StringTokenizer keyType = new StringTokenizer(data, ".");
				
				//If there are more than 2 tokens for the second line (. found)
				if (keyType.countTokens() > 1)
				{
					//Go to token after .
					keyType.nextToken();
					String keyWordType = keyType.nextToken();
					
					//If value is wav or mid type 2
					if (keyWordType.equals("wav") || keyWordType.equals("mid"))
					{
						//If value is not sound, terminate
						if (type != 2)
						{
							System.out.println("Error value is not a sound file, program terminating");
							break;
						}
					}
					
					//Else if value is gif or jpg type 3
					else if  (keyWordType.equals("gif") || keyWordType.equals("jpg"))
					{
						//If value is not a picture, terminate
						if (type != 3)
						{
							System.out.println("Error value is not a picture file, program terminating");
							break;
						}
					}
				}
				
				Key k = new Key(userWord, type);
				Record r = new Record(k, data);
				
				//If value is already in dictionary, return error
				if (dictionary.find(k) != null)
				{
					System.out.println("Error! value is already in the dictionary!");
				}
				
				
				//Else insert value into dictionary
				else
				{
					try {
						dictionary.insert(r);
					} catch (DictionaryException e) {
						e.printStackTrace();
					}
				}
			}
			
			//If the first token of users command is next
			else if (commandType.equals("next"))
			{
				//Checks to see if there are more arguments, if not return error
				if (userCommand.countTokens() < 2)
				{
					System.out.println("Error, too little arguments given! Program ending");
					break;
				}
				
				//Reads next 2 tokens
				userWord = userCommand.nextToken();
				
				type =Integer.parseInt(userCommand.nextToken());
				
				Key k = new Key(userWord, type);
				
				//Searches for successor in dictionary
				Record succ = dictionary.successor(k);
				
				//If there is no successor return error
				if (succ == null)
				{
					System.out.println("Error! there is no record in the database with key smaller than input");
				}
				
				//Else return the successor keys word and type
				else
				{
					System.out.println("(" + succ.getKey().getWord() + "," + succ.getKey().getType() + ")");
				}
			}
			
			//If the first token of users command is prev
			else if (commandType.equals("prev"))
			{
				//Checks to see if there are more arguments, if not return error
				if (userCommand.countTokens() < 2)
				{
					System.out.println("Error, too little arguments given! Program ending");
					break;
				}
				
				//Reads the next 2 tokens
				userWord = userCommand.nextToken();
				
				type =Integer.parseInt(userCommand.nextToken());
				
				Key k = new Key(userWord, type);
				
				//Searches for predecessor in dictionary
				Record prec = dictionary.predecessor (k);
				
				//If predecessor is not found return error
				if (prec == null)
				{
					System.out.println("Error! there is no record in the database with key smaller than input");
				}
				
				//Else return the predecessor
				else
				{
					System.out.println("(" + prec.getKey().getWord() + "," + prec.getKey().getType() + ")");
				}
			}
			
			//If the first token of users command is first
			else if (commandType.equals("first"))
			{	
				//Search dictionary for smallest value and return it
				Record r = dictionary.smallest();
				if (r == null)
				{
					System.out.println("Dictionary is empty");
				}
				else
				{
					System.out.println("(" + r.getKey().getWord() + "," + r.getKey().getType() + ")");
				}
			}
			
			//If the first token of users command is last
			else if (commandType.equals("last"))
			{
				//Search dictionary for largest value and return it
				Record r = dictionary.largest();
				if (r == null)
				{
					System.out.println("Dictionary is empty");
				}
				else
				{
					System.out.println("(" + r.getKey().getWord() + "," + r.getKey().getType() + ")");
				}
			}
			
			//If the first token of users command is exit, exit the program
			else if (commandType.equals("exit"))
			{
				System.exit(0);
			}
			
			//Else return an error for an invalid command
			else
			{
				System.out.println("Invalid command!");
			}
		}	
	}
}
