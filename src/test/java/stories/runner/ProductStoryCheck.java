package stories.runner;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import com.thoughtworks.paranamer.NullParanamer;
import org.jbehave.core.configuration.Configuration;

import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.AbsolutePathCalculator;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;

import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.*;
import org.junit.runner.RunWith;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.io.CodeLocations.codeLocationFromURL;
import static org.jbehave.core.reporters.Format.*;

@RunWith(JUnitReportingRunner.class)
public class ProductStoryCheck extends JUnitStories {

    private Configuration configuration;

    public ProductStoryCheck(){

        super();
        configuration =  new MostUsefulConfiguration();


    }


    @Override
    public Configuration configuration() {




        return this.configuration;

    }

    @Override
    protected List<String> storyPaths() {





        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "*.story", "");

    }



}
