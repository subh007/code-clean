# code-clean
mvn plugin for cleaning the code. This plugin will remove the tab
character and trailing white space from all the java, xml, json and
pom files.

## Executing plugin

for only java files (default)
```
mvn com.github.subh:code-clean:0.0.1-SNAPSHOT:code-clean -X
```

for selected file type

```
mvn com.github.subh:code-clean:0.0.1-SNAPSHOT:code-clean -Dextension=java,xml,...
```

If you want to print the files selected for processing pass the
option `-DcleanDebug=true`.
