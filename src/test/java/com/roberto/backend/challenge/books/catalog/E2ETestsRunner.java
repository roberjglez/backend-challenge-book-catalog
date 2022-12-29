package com.roberto.backend.challenge.books.catalog;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:e2e_scenarios", plugin = {"pretty", "json:target/cucumber-report.json"})
public class E2ETestsRunner {

}