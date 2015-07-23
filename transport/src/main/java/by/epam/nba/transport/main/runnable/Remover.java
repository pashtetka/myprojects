package by.epam.nba.transport.main.runnable;

import by.epam.nba.transport.main.ItemList;
import by.epam.nba.transport.main.entity.Item;
import by.epam.nba.transport.main.entity.ThreadObject;

public class Remover implements Runnable {

	private ItemList itemList;
	private long millisecond;
	private ThreadObject object;

	public Remover(ItemList itemList, long millisecond, ThreadObject object) {
		this.itemList = itemList;
		this.millisecond = millisecond;
		this.object = object;
	}

	@Override
	public void run() {
		while (true) {
			long l = itemList.getItems().size();

			for (int i = 0; i < l; i++) {
				Item item = itemList.getItems().get(i);
				long time = System.currentTimeMillis() - item.getCurrentTime();
				if (time > millisecond) {
					itemList.remove(item);
					System.out.println("REMOVE: " + item.getPath()
							+ ". New size: " + itemList.getItems().size());
					i--;
					l = itemList.getItems().size();
				}
			}
			if(!object.isWriterIsAlive() && itemList.getItems().isEmpty()){
				sleep(millisecond + 100);
				if(itemList.getItems().isEmpty()){
					break;
				}
			}
		}

	}
	
	protected void sleep(long millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
