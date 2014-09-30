package main;

public class WorkMaster implements Runnable {
	private Crawler[] spiders;
	private PageScanner[] scanners;
	private Thread workMasterThread;
	public boolean shouldSpidersWork;
	public boolean shouldScannersWork;
	private boolean workfuse;

	public void start() {
		this.workMasterThread = new Thread(this, "WorkMasterThread");
		this.workMasterThread.start();
		NCBUtils.talk("Started Work Master");
	}

	public void stop() {
		this.workMasterThread = null;
	}

	public WorkMaster() {
		spiders = new Crawler[Interfata.settings.ncb_crawl_threads];
		for (int i = 1; i <= Interfata.settings.ncb_crawl_threads; i++)
			spiders[i - 1] = new Crawler(i);
		scanners = new PageScanner[Interfata.settings.ncb_scan_threads];
		for (int i = 1; i <= Interfata.settings.ncb_scan_threads; i++)
			scanners[i - 1] = new PageScanner(i);
		this.shouldScannersWork = false;
		this.shouldSpidersWork = false;
		this.workfuse = true;

	}

	public void startCrawlers() {
		NCBUtils.talk("Started crawler!");
		for (int i = 0; i < Interfata.settings.ncb_crawl_threads; i++)
			spiders[i].start();
	}

	public void startScanners() {
		for (int i = 0; i < Interfata.settings.ncb_scan_threads; i++)
			scanners[i].start();
	}

	public void stopCrawlers() {
		for (int i = 0; i < Interfata.settings.ncb_crawl_threads; i++)
			spiders[i].stop();
	}

	public void stopScanners() {
		for (int i = 0; i < Interfata.settings.ncb_scan_threads; i++)
			scanners[i].stop();
	}

	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		while (thisThread == this.workMasterThread) {
			if (workfuse) {
				//if (this.shouldScannersWork == this.scannersWorking)
					//if (this.scannersWorking)
						//stopScanners();
					//else
						//startScanners();

				/*if (this.shouldSpidersWork == this.spidersWorking)
					if (this.spidersWorking)
						stopCrawlers();
					else
						startCrawlers();*/
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					NCBUtils.talk("Error sleeping in WorkMaster.run()");
				}
			}
			else{
				if(Interfata.database.isOkToStartCrawling()){
					Interfata.buffers.start();
					this.workfuse=true;
				}
				else{
					// TODO ask for seeds!!!
				}
			}
		}
		

	}

}
