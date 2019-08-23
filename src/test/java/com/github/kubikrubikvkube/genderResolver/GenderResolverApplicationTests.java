package com.github.kubikrubikvkube.genderResolver;

import com.github.kubikrubikvkube.genderResolver.name.NameEntity;
import com.github.kubikrubikvkube.genderResolver.name.NameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class GenderResolverApplicationTests {
	@Autowired
	NameRepository nameRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void connectionToH2DBEstablished() {
		Collection<NameEntity> all = nameRepository.findAll();
		assertThat(all.size(), greaterThan(0));
	}

	@Test
	void maleSexCanBeResolved() {
		String sex = nameRepository.findSexByName("Владимир");
		assertNotNull(sex);
		assertThat(sex, equalTo("М"));
	}

	@Test
	void femaleSexCanBeResolved() {
		String sex = nameRepository.findSexByName("Ангелина");
		assertNotNull(sex);
		assertThat(sex, equalTo("Ж"));
	}

	@Test
	void sexCantBeResolvedForWrongName() {
		String sex = nameRepository.findSexByName("такогоименинет");
		assertNull(sex);
	}

	@Test
	void sexResolutionIsCaseInsensitive() {
		String sex = nameRepository.findSexByName("иРиНа");
		assertThat(sex, equalTo("Ж"));
	}
}
