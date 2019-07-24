package com.github.yitan.service;

import com.github.yitan.domain.Person;
import com.github.yitan.domain.PersonRequest;
import com.github.yitan.utils.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TimeUnit.class)
@SuppressStaticInitializationFor("SalaryCalculator")
public class PersonServiceTest {

    private PersonService personService;

    @Before
    public void setUp() {
        personService = new PersonService();
    }

    @Test
    public void should_find_valid_person() {
        // given
        PersonRequest personRequest = new PersonRequest("James");
        suppress(method(TimeUnit.class, "sleep"));

        // when
        Person actualPerson = personService.find(personRequest);

        // then
        assertThat(actualPerson.getFirstName()).isEqualTo("Merson");
        assertThat(actualPerson.getLastName()).isEqualTo("James");
        assertThat(actualPerson.getSalary()).isEqualTo(new BigDecimal(20));
    }

    @Test
    public void should_find_none_person() {
        // given
        PersonRequest personRequest = new PersonRequest("");

        // when
        Person actualPerson = personService.find(personRequest);

        // then
        assertThat(actualPerson.getFirstName()).isEqualTo("None");
        assertThat(actualPerson.getLastName()).isEqualTo("None");
        assertThat(actualPerson.getSalary()).isEqualTo(BigDecimal.ZERO);
    }
}