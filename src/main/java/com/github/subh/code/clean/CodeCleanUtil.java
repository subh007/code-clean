package com.github.subh.code.clean;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.codehaus.plexus.util.StringUtils;

public class CodeCleanUtil {
    public static Collection<File> findFiles(String baseDir, List<String> extension) {
        return extension.stream().map(ext -> FileUtils.listFiles(new File(baseDir),
                new WildcardFileFilter("*." + ext),
                TrueFileFilter.INSTANCE))
        .flatMap(files -> files.stream())
        .collect(Collectors.toList());
    }

    @SuppressWarnings("deprecation")
    private static void replaceAndRemoveTrailingSpace(String source, String target, File file) throws IOException {
            List<String> readLines = FileUtils.readLines(file, "UTF-8");
            List<String> cleanLines = readLines.stream().map(str -> StringUtils.stripEnd(str, null))
            .map(str -> str.replaceAll(source, target)).collect(Collectors.toList());

            FileUtils.writeLines(file, "UTF-8", cleanLines, false);
    }

    @SuppressWarnings("deprecation")
    public static void replaceCharacter(String source, String target, List<String> fileExtension, boolean debug) {
        Collection<File> matchedFiles = findFiles(System.getProperty("user.dir"), fileExtension);

        if(debug) {
            matchedFiles.stream().forEach(System.out::println);
        }

        // replace character
        matchedFiles.stream().forEach(file -> {
            try {
                replaceAndRemoveTrailingSpace(source, target, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
