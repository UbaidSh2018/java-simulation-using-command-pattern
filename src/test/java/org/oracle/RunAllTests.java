package org.oracle;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.oracle.command.*;
import org.oracle.io.CommandReaderTest;
import org.oracle.io.FileReaderTest;
import org.oracle.receiver.*;
import org.oracle.site.ConstructionSiteTest;
import org.oracle.site.CostTest;
import org.oracle.site.SquareTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdvanceCommandTest.class,
        CommandParserTest.class,
        HelpCommandTest.class,
        LeftCommandTest.class,
        QuitCommandTest.class,
        RightCommandTest.class,

        CommandReaderTest.class,
        FileReaderTest.class,

        BulldozerAdvanceOneSquareTest.class,
        BulldozerAdvanceTest.class,
        BulldozerTurnLeftTest.class,
        BulldozerTurnRightTest.class,
        GridTest.class,

        ConstructionSiteTest.class,
        CostTest.class,
        SquareTest.class,
        SiteSimulatorTest.class
})
public class RunAllTests {

}

