package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsA() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "A"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("A"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "A"));
        Role result = store.findById("2");
        assertNull(result);
    }


    @Test
    public void whenReplaceThenRoleIsB() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "A"));
        store.replace("1", new Role("1", "B"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("B"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChanges() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "A"));
        boolean res = store.replace("10", new Role("10", "B"));
        assertFalse(res);
        Role result = store.findById("1");
        assertThat(result.getRole(), is("A"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "A"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

}