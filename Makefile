.phony: install, ci, clean

clean:
	mvn clean

install: 
	mvn install

ci:
	mvn test
