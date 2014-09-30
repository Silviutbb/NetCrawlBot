package main;

public class BufferMain implements Runnable {

	CrawlBuffer crawlBuffer;
	CrawlInBuffer crawlInBuffer;
	ScanInBuffer scanInBuffer;
	Thread thread;

	public void start() {
		this.thread = new Thread(this, "BufferMainThread");
		this.thread.start();
	}

	public void stop() {
		this.thread = null;
	}

	public BufferMain(boolean verbose) {
		
		NCBUtils.talk("Buffers Loading");
		crawlBuffer = new CrawlBuffer(Interfata.settings.ncb_buffer);
		crawlInBuffer = new CrawlInBuffer(Interfata.settings.ncb_buffer/10);
		//scanInBuffer = new ScanInBuffer(Interfata.settings.ncb_buffer/10);
		NCBUtils.talk("Buffers loaded");
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (this.thread == currentThread) {
			//int counter = 0;

			/*if (this.crawlBuffer.quant >= Interfata.settings.ncb_buffer * 80 / 100) {
				NCBUtils.talk("need to export buffer");
				if (!this.crawlBuffer.flushBuffer()) {
					NCBUtils.talk("Error flushing Buffer ");
				}
			}*/
			/*if (this.crawlInBuffer.quant <= Interfata.settings.ncb_buffer * 80 / 1000 ) {
				if (!this.crawlInBuffer.fill(Interfata.settings.ncb_buffer * 80 / 1000- this.crawlInBuffer.quant))
					NCBUtils.talk("Error filling crawlInBuffer");
				NCBUtils.talk("Refilled crawl in buffer");
			}*/
			/*if(counter<0)
				counter = 0;
			if(counter == 0)*/
			/*if (this.scanInBuffer.quant <= Interfata.settings.ncb_buffer * 80 / 1000 ) {
				if (!this.scanInBuffer.fill(Interfata.settings.ncb_buffer/10
						- this.scanInBuffer.quant - 1))
					{NCBUtils.talk("Error filling scanInBuffer"); 
					counter = 600;
					}
					else 
						counter=0;
				if(Interfata.buffers.scanInBuffer.quant>0)
					NCBUtils.talk("Refilled scan in buffer");
				
			}*/
			//counter --;
						
			//temporary
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
