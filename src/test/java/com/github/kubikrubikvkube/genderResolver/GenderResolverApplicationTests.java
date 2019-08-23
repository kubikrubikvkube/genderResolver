package com.github.kubikrubikvkube.genderResolver;

import com.github.kubikrubikvkube.genderResolver.name.NameEntity;
import com.github.kubikrubikvkube.genderResolver.name.NameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
class GenderResolverApplicationTests {
	@Autowired
	NameRepository nameRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void connectionToH2DBEstablished() {
		Iterable<NameEntity> all = nameRepository.findAll();
		int counter = 0;
		for (NameEntity entity : all) {
			counter += 1;
		}
		assertThat(counter, greaterThan(0));
	}

}
