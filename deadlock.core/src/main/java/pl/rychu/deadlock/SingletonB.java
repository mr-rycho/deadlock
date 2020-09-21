package pl.rychu.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import java.util.List;

@Singleton
@Startup
public class SingletonB {

	private static final Logger log = LoggerFactory.getLogger(SingletonB.class);

	@Inject
	private SingletonA singletonA;

	@Inject
	private ServiceC serviceC;

	@Resource
	private ManagedExecutorService managedExecutorService;

	@PostConstruct
	public void postConstruct() {
		log.info("hello from SingletonB");

		managedExecutorService.execute(() -> serviceC.run());

		log.info("job scheduled; continuing initialization");

		sleep(500); // some other stuff

		List<Integer> data = singletonA.getCachedData();

		log.info("got {} elements in cache", data.size());
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
