package ru.job4j.generics;

public class Role extends Base {

    private final int access;

    public Role(String id, int access) {
        super(id);
        this.access = access;
    }

    public int getAccess() {
        return access;
    }
}
