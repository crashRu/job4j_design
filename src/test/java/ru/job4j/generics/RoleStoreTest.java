package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest{
    @Test
    public void whenAddAndFindThenRoleAccess() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        Role result = store.findById("1");
        assertThat(result.getAccess(), is(1));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleZero() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        store.add(new Role("1", 0));
        Role result = store.findById("1");
        assertThat(result.getAccess(), is(1));
    }

    @Test
    public void whenReplaceThenAccessIsTwo() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        store.replace("1", new Role("1", 2));
        Role result = store.findById("1");
        assertThat(result.getAccess(), is(2));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeAccess() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 0));
        store.replace("10", new Role("10", 10));
        Role result = store.findById("1");
        assertThat(result.getAccess(), is(0));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 0));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleAccessIsOne() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", 1));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getAccess(), is(1));
    }
}