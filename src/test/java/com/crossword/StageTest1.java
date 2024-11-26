package com.crossword;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for running multiple test classes.
 *
 * This class uses JUnit's Suite runner to execute all tests in the specified test classes.
 * The test classes included are:
 * - CaseTest: Tests related to the Case class.
 * - GrilleTest: Tests related to the Grille class.
 * - EmplacementTest: Tests related to the Emplacement class.
 */
@RunWith(Suite.class)
@SuiteClasses({ CaseTest.class, GrilleTest.class, EmplacementTest.class })

public class StageTest1 {

}
