.phony: install, ci, clean, ci-integration

clean:
	mvn clean

install: 
	mvn install

ci:
	mvn test

ci-integration:	
	export TESTCONTAINERS_HOST_OVERRIDE=localhost
	mvn -Dlrs.integration.tests=true test
