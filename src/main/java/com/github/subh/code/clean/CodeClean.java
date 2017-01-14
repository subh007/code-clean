package com.github.subh.code.clean;

import java.util.Arrays;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.sonatype.inject.Parameters;

/**
 * This Plugin will clean the whitespace and the
 * tab character from the source code.
 * @author subhash kumar singh
 *
 */
@Mojo(name = "code-clean")
public class CodeClean extends AbstractMojo
{

	// to invoke this parameter
	// mvn com.github.subh:code-clean:0.0.1-SNAPSHOT:code-clean -DcleanDebug=true -X
    @Parameter(property="cleanDebug")
    private boolean cleanDebug;

    public void removeTabWithSpace(List<String> fileExtension) {
        CodeCleanUtil.replaceCharacter("\t", "    ", fileExtension, cleanDebug);
    }

    public void execute() throws MojoExecutionException {
        Log logger = getLog();
        logger.info("== invoking code cleaner ==");

        // file to process
        List<String> filesToProcess = Arrays.asList("java", "xml", "json", "pom");

        // replace all the tab character with the space character
        removeTabWithSpace(filesToProcess);
    }
}
