package by.epam.nba.transport.newmain;

import java.io.IOException;

import by.epam.nba.transport.newmain.creator.FileCreator;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String content = "The British Museum has one of the largest libraries in the world. It has a copy of every book that is printed in the English language, so that there are more than six million books there. They receive nearly two thousand books and papers daily. The British Museum Library has a very big collection of printed books and manuscripts, both old and new. You can see beautifully illustrated old manuscripts which they keep in glass cases. You can also find there some of the first English books printed by Caxton. Caxton was a printer who lived in the fifteenth century. He made the first printing-press in England. In the reading-room of the British Museum many famous men have read and studied. Charles Dickens, a very popular English writer and the author of 'David Copperfield', 'Oliver Twist', 'Dombey and Son' and other books, spent a lot of time in the British Museum Library.";
		FileCreator creator = new FileCreator("D:/old/filefile.mvk", content);
		creator.createFile();

	}

}
