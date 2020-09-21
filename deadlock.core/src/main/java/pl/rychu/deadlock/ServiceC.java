package pl.rychu.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class ServiceC {

	private static final Logger log = LoggerFactory.getLogger(ServiceC.class);

	private static final Random RANDOM = new Random();

	@Inject
	private SingletonA singletonA;

	@PostConstruct
	public void postConstruct() {
		log.info("hello from ServiceC");
	}

	public void run() {
		List<Integer> data = singletonA.getCachedData();
		int index = RANDOM.nextInt(data.size());
		log.info("next number: {}", data.get(index));
	}

}
