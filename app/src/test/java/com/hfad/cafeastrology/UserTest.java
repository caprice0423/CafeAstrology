package com.hfad.cafeastrology;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void getUsername() {
        assertEquals("Login.db", user.getUsername());
    }
}