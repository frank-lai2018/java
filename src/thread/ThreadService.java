package thread;

public class ThreadService {
	
	private Thread executeThread;
	
	private boolean finished = false;
	
	public void execute(Runnable task) {
		executeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				long currentTimeMillis = System.currentTimeMillis();
				System.out.println("executeThread run... ");
				Thread runner = new Thread(task);
				runner.setDaemon(true);
				
				runner.start();
				try {
					runner.join();
					finished = true;
				} catch (InterruptedException e) {
					System.out.println("executeThread Interrupted... run "+(System.currentTimeMillis()-currentTimeMillis)+"秒");
					e.printStackTrace();
				}
			}
		}
		);
		
		executeThread.start();
	}
	
	public void shutdown(long sec) {
		long currentTimeMillis = System.currentTimeMillis();
		
		while(!finished) {
			if( System.currentTimeMillis() - currentTimeMillis >= sec) {
				executeThread.interrupt();
				System.out.println("executeThread shutdown interrupt... run "+(System.currentTimeMillis()-currentTimeMillis)+"秒");
				break;
			}
			try {
				executeThread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("執行緒被打斷!");
				break;
			}
		}
		finished = false;
	}

}
