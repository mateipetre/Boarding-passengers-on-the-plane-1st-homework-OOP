JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Category.java \
		Family.java \
		PriorityQueueVec.java \
		Ticket.java \
		Creator.java \
		Group.java \
		Pasager.java \
		Single.java \
		Test.java

run: classes
	java Test

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class