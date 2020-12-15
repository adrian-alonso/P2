# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -classpath /opt/tomcat/lib/servlet-api.jar

# typing 'make' will invoke the first target entry in the makefile
# (the default one in this case)
#
default:
	$(JCC) $(JFLAGS) Sint101P2.java Parser.java EAML_ErrorHandler.java FrontEnd.java Degree.java Subject.java Student.java WarningFile.java ErrorFile.java FatalErrorFile.java


# To start over from scratch, type 'make clean'.
# Removes all .class files, so that the next make rebuilds them
#
clean:
	$(RM) *.class
