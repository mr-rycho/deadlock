package pl.rychu.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Singleton
public class SingletonA {

	private static final Logger log = LoggerFactory.getLogger(SingletonA.class);

	private static final List<Integer> DATA = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

	@PostConstruct
	public void postConstruct() {
		log.info("hello from SingletonA");
	}

	public List<Integer> getCachedData() {
		return Collections.unmodifiableList(DATA);
	}

}
