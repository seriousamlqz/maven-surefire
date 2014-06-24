package org.apache.maven.surefire.its;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.surefire.its.fixture.OutputValidator;
import org.apache.maven.surefire.its.fixture.SurefireJUnit4IntegrationTestCase;
import org.apache.maven.surefire.its.fixture.SurefireLauncher;
import org.junit.Test;

/**
 * JUnit4 RunListener Integration Test.
 *
 * @author <a href="mailto:qingzhouluo@google.com">Qingzhou Luo</a>
 */
public class JUnit4RerunFailingTestsIT
    extends SurefireJUnit4IntegrationTestCase
{
    private SurefireLauncher unpack()
    {
        return unpack( "/junit4-rerun-failing-tests" );
    }

    @Test
    public void testRerunFailingErrorTestsWithOneRetry()
        throws Exception
    {
        OutputValidator outputValidator =
            unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
                "-DrerunFailingTests=true" ).withFailure().executeTest().assertTestSuiteResults( 5, 1, 1, 0, 0 );
        verifyFailuresOneRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=true" ).addGoal( "-DforkCount=2" ).withFailure().executeTest().assertTestSuiteResults(
            5, 1, 1, 0, 0 );
        verifyFailuresOneRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=true" ).addGoal( "-Dparallel=methods" ).addGoal(
            "-DuseUnlimitedThreads=true" ).withFailure().executeTest().assertTestSuiteResults( 5, 1, 1, 0, 0 );
        verifyFailuresOneRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=true" ).addGoal( "-Dparallel=classes" ).addGoal(
            "-DuseUnlimitedThreads=true" ).withFailure().executeTest().assertTestSuiteResults( 5, 1, 1, 0, 0 );
        verifyFailuresOneRetry( outputValidator );
    }

    @Test
    public void testRerunFailingErrorTestsTwoRetry()
        throws Exception
    {
        // Four flakes, both tests have been re-run twice
        OutputValidator outputValidator =
            unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
                "-DrerunFailingTests=true" ).addGoal(
                "-DrerunFailingTestsCount=2" ).executeTest().assertTestSuiteResults( 5, 0, 0, 0, 4 );

        verifyFailuresTwoRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=true" ).addGoal( "-DrerunFailingTestsCount=2" ).addGoal(
            "-DforkCount=3" ).executeTest().assertTestSuiteResults( 5, 0, 0, 0, 4 );

        verifyFailuresTwoRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=true" ).addGoal( "-DrerunFailingTestsCount=2" ).addGoal(
            "-Dparallel=methods" ).addGoal( "-DuseUnlimitedThreads=true" ).executeTest().assertTestSuiteResults( 5, 0,
                                                                                                                 0, 0,
                                                                                                                 4 );

        verifyFailuresTwoRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=true" ).addGoal( "-DrerunFailingTestsCount=2" ).addGoal(
            "-Dparallel=classes" ).addGoal( "-DuseUnlimitedThreads=true" ).executeTest().assertTestSuiteResults( 5, 0,
                                                                                                                 0, 0,
                                                                                                                 4 );

        verifyFailuresTwoRetry( outputValidator );
    }

    @Test
    public void testRerunFailingErrorTestsFalse()
        throws Exception
    {
        OutputValidator outputValidator =
            unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
                "-DrerunFailingTests=false" ).withFailure().executeTest().assertTestSuiteResults( 5, 1, 1, 0, 0 );

        verifyFailuresNoRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=false" ).addGoal( "-DforkCount=3" ).withFailure().executeTest().assertTestSuiteResults(
            5, 1, 1, 0, 0 );

        verifyFailuresNoRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=false" ).addGoal( "-Dparallel=methods" ).addGoal(
            "-DuseUnlimitedThreads=true" ).withFailure().executeTest().assertTestSuiteResults( 5, 1, 1, 0, 0 );

        verifyFailuresNoRetry( outputValidator );

        outputValidator = unpack().addGoal( "-Dprovider=surefire-junit4" ).setJUnitVersion( "4.7" ).maven().addGoal(
            "-DrerunFailingTests=false" ).addGoal( "-Dparallel=classes" ).addGoal(
            "-DuseUnlimitedThreads=true" ).withFailure().executeTest().assertTestSuiteResults( 5, 1, 1, 0, 0 );

        verifyFailuresNoRetry( outputValidator );
    }

    private void verifyFailuresOneRetry( OutputValidator outputValidator )
    {
        outputValidator.verifyTextInLog( "Failed tests" );
        outputValidator.verifyTextInLog( "Run 1:FlakyFirstTimeTest.testFailingTestOne" );
        outputValidator.verifyTextInLog( "Run 2:FlakyFirstTimeTest.testFailingTestOne" );

        outputValidator.verifyTextInLog( "Run 1:FlakyFirstTimeTest.testErrorTestOne" );
        outputValidator.verifyTextInLog( "Run 2:FlakyFirstTimeTest.testErrorTestOne" );

        outputValidator.verifyTextInLog( "Tests run: 5, Failures: 1, Errors: 1, Skipped: 0" );
    }

    private void verifyFailuresTwoRetry( OutputValidator outputValidator )
    {
        outputValidator.verifyTextInLog( "Flaked tests" );
        outputValidator.verifyTextInLog( "Run 1:FlakyFirstTimeTest.testFailingTestOne" );
        outputValidator.verifyTextInLog( "Run 2:FlakyFirstTimeTest.testFailingTestOne" );
        outputValidator.verifyTextInLog( "Run 3:PASS" );

        outputValidator.verifyTextInLog( "Run 1:FlakyFirstTimeTest.testErrorTestOne" );
        outputValidator.verifyTextInLog( "Run 2:FlakyFirstTimeTest.testErrorTestOne" );

        outputValidator.verifyTextInLog( "Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Flakes: 2" );
    }

    private void verifyFailuresNoRetry( OutputValidator outputValidator )
    {
        outputValidator.verifyTextInLog( "Failed tests" );
        outputValidator.verifyTextInLog( "testFailingTestOne(junit4.FlakyFirstTimeTest)" );
        outputValidator.verifyTextInLog( "ERROR" );
        outputValidator.verifyTextInLog( "testErrorTestOne(junit4.FlakyFirstTimeTest)" );

        outputValidator.verifyTextInLog( "Tests run: 5, Failures: 1, Errors: 1, Skipped: 0" );
    }
}
