package com.crossword.modules;

import static org.junit.Assert.*;

import com.crossword.Case;
import com.crossword.Emplacement;
import org.junit.Test;

public class EmplacementTest {

    @Test
    public void testEmpty() {
        Emplacement e = new Emplacement();
        assertEquals(0, e.size());
    }

    @Test
    public void testAdd() {
        Emplacement e = new Emplacement();
        e.add(new Case(0, 1, '*'));
        assertEquals(1, e.size());
        e.add(new Case(1, 2, '@'));
        assertEquals(2, e.size());
    }

    @Test
    public void testGet() {
        Emplacement e = new Emplacement();
        Case c1 = new Case(0, 1, '*');
        Case c2 = new Case(1, 2, '@');
        e.add(c1);
        e.add(c2);
        assertSame(c1, e.getCase(0));
        assertSame(c2, e.getCase(1));
    }
}

