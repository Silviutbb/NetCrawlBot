package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class PageScanner implements Runnable {
	public String adress;
	public String domain;
	private String content;
	private Thread scanThread;
	private String threadName;

	public PageScanner(int nr) {
		this.adress = null;
		this.content = null;
		this.threadName = "Scan thread" + nr;
		this.domain = null;
		this.scanThread = null;
	}

	public void start() {
		this.scanThread = new Thread(this, this.threadName);
		this.scanThread.start();
	}

	public void stop() {
		this.scanThread = null;
	}

	private void getContentFromWeb() throws IOException {
		URL address = new URL("http://" + this.adress);

		InputStreamReader pageInput = new InputStreamReader(
				address.openStream());
		BufferedReader source = new BufferedReader(pageInput);
		String sourceLine;
		this.content = "";
		while ((sourceLine = source.readLine()) != null)
			this.content += sourceLine + "/n";
	}

	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		while (thisThread == this.scanThread) {
			// starts a cycle
			this.adress = Interfata.buffers.scanInBuffer.pull();
			this.domain = NCBUtils.getDomain(adress);
			try {
				getContentFromWeb();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				NCBUtils.talk("Error while getting web content #2");
			}
			String next;
			int i, j,k;
			boolean inMatch, offMatch;

			for (i = 0; i < Interfata.plugins.getNr_of_plugins(); i++)
				if (!Interfata.plugins.plugin[i].plugin_is_standard) {
					offMatch = false;

					StringTokenizer Tok = new StringTokenizer(content);
					while (Tok.hasMoreTokens()) {
						next = Tok.nextToken();

						if (Interfata.plugins.plugin[i].syntax_is_on_page_number == 0)
							offMatch = true;
						for (k = 0; k < Interfata.plugins.plugin[i].syntax_is_on_page_number; k++)
							if (NCBUtils
									.existsInString(
											next,
											Interfata.plugins.plugin[i].syntax_is_on_page_nr[k]))
								offMatch = true;

						inMatch = false;

						if (Interfata.plugins.plugin[i].syntax_is_on_page_number == 0)
							offMatch = true;
						// in token conditions
						for (j = 0; j < Interfata.plugins.plugin[i].syntax_test_hii_number; j++)
							// searching stage
							if (NCBUtils
									.existsInString(
											next,
											Interfata.plugins.plugin[i].syntax_test_has_in_it_nr[j]))
								inMatch = true;
						for (j = 0; j < Interfata.plugins.plugin[i].syntax_clean_number
								&& inMatch && offMatch; j++)
							// cleaning stage
							next = NCBUtils
									.remover(
											next,
											Interfata.plugins.plugin[i].syntax_clean_nr[j]);
						if (inMatch && offMatch && (next != ""))

							while(!addToBuffrt(i, next))
							try {
								System.out.println("error for "+Interfata.plugins.plugin[i].sql_table + " item "+next);
								Thread.sleep(10);
							} catch (InterruptedException e) {
							}
					}


				}

		}

	}

	private boolean addToBuffrt(int i, String next){
		try{
			
		if (Interfata.plugins.plugin[i].sql_table != "links"){
			Interfata.buffers.crawlBuffer
					.insert(next,
							Interfata.plugins.plugin[i].sql_table,
							this.adress);}
		else {
			if (NCBUtils.existsInString(next, domain)) {
				Interfata.buffers.crawlBuffer
						.insert(next,
								Interfata.plugins.plugin[i].sql_table,
								next);
			} else {
				if (NCBUtils
						.existsInString(next, "www") || NCBUtils.existsInString(next, "www2"))
					Interfata.buffers.crawlBuffer
							.insert(next,
									Interfata.plugins.plugin[i].sql_table,
									next);
				else
					Interfata.buffers.crawlBuffer
							.insert(domain +"/"+ next,
									Interfata.plugins.plugin[i].sql_table,
									domain + next);
			}
		}
		}catch(Exception e){
			return false;
		}
		return true;
	}

}
