.phony: install, ci, clean, ci-integration

clean:
	mvn clean

install: 
	mvn install

ci:
	mvn test

ci-integration:	
	mvn -Dlrs.integration.tests=true test
