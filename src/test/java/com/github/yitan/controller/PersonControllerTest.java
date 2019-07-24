package com.github.yitan.controller;

import com.github.yitan.domain.Person;
import com.github.yitan.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @Test
    public void should_get_name() {
        // given
        String name = "lkh";
        Person person = new Person("lkh", "lkh", new BigDecimal(100));
        given(personService.find((argThat(argument -> argument.getName().equals(name))))).willReturn(person);
        // when
        String actualName = personController.getName(name);
        // then
        assertThat(actualName).isEqualTo(person.getFirstName() + "," + person.getLastName());
    }
}