# Compile
COMPILECP = -classpath json-simple-1.1.1.jar
JC = javac
JFLAGS = -g
# Run
RUNCP = -cp .:json-simple-1.1.1.jar
J = java

## Run Psalms
default: Psalms

# Run the Psalms file
Psalms: Psalms.class
	$(J) $(RUNCP) Psalms

# Compile Psalms
Psalms.class: Psalms.java
	$(JC) $(JFLAGS) $(COMPILECP) Psalms.java

# Remove class files
clean:
	$(RM) *.class